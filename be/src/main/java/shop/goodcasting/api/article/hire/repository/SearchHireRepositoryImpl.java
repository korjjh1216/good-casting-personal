package shop.goodcasting.api.article.hire.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import shop.goodcasting.api.article.hire.domain.Hire;
import shop.goodcasting.api.article.hire.domain.HirePageRequestDTO;
import shop.goodcasting.api.article.hire.domain.QHire;
import shop.goodcasting.api.user.producer.domain.QProducer;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class SearchHireRepositoryImpl extends QuerydslRepositorySupport implements SearchHireRepository {

    public SearchHireRepositoryImpl() {
        super(Hire.class);
    }

    @Override
    @Transactional
    public Page<Object[]> searchPage(HirePageRequestDTO pageRequest, Pageable pageable) {

        QHire hire = QHire.hire;
        QProducer producer = QProducer.producer;

        JPQLQuery<Hire> jpqlQuery = from(hire);
        jpqlQuery.leftJoin(producer).on(hire.producer.eq(producer));

        JPQLQuery<Tuple> tuple = jpqlQuery.select(hire, producer);

        //hireId >0
        BooleanBuilder totalBuilder = new BooleanBuilder();
        BooleanExpression expression = hire.hireId.gt(0L);
        totalBuilder.and(expression);

        if (pageRequest.getSearchKey() != null && pageRequest.getSearchKey().trim().length() != 0) {
            BooleanBuilder keywordBuilder = makeKeyword(hire, pageRequest.getSearchKey());
            totalBuilder.and(keywordBuilder);
        }


        if (pageRequest.getPeriod() != null) {
            BooleanBuilder periodBuilder = makePeriodRange(hire, pageRequest.getPeriod().getFrom(), pageRequest.getPeriod().getTo());
            totalBuilder.and(periodBuilder);
        }


        if (pageRequest.getPay() != null) {
            BooleanBuilder payBuilder = makePayRange(hire, pageRequest.getPay().getStart(), pageRequest.getPay().getEnd());
            totalBuilder.and(payBuilder);
        }

        tuple.where(totalBuilder);

        Sort sort = pageable.getSort();
        sort.stream().forEach(order -> {
            Order direction = order.isAscending() ? Order.ASC : Order.DESC;
            String prop = order.getProperty();

            PathBuilder orderByExpression = new PathBuilder(Hire.class, "hire");
            tuple.orderBy(new OrderSpecifier(direction, orderByExpression.get(prop)));
        });

        tuple.groupBy(hire);
        tuple.offset(pageable.getOffset());
        tuple.limit(pageable.getPageSize());

        List<Tuple> result = tuple.fetch();

        long count = tuple.fetchCount();

        return new PageImpl<>(result.stream()
                .map(t -> t.toArray()).collect(Collectors.toList()), pageable, count);
    }

    private BooleanBuilder makePayRange(QHire hire, Integer start, Integer end) {
        BooleanBuilder conditions = new BooleanBuilder();
        conditions.and(hire.guarantee.between(start, end));

        return conditions;
    }

    private BooleanBuilder makePeriodRange(QHire hire, LocalDate from, LocalDate to) {
        BooleanBuilder conditions = new BooleanBuilder();
        conditions.and(hire.filming.between(from, to));

        return conditions;
    }

    private BooleanBuilder makeKeyword(QHire hire, String keyword) {
        BooleanBuilder conditions = new BooleanBuilder();
        conditions.or(hire.title.contains(keyword));
        conditions.or(hire.project.contains(keyword));
        conditions.or(hire.contents.contains(keyword));
        conditions.or(hire.cast.contains(keyword));

        return conditions;
    }

    @Override
    public Page<Object[]> myHirePage(HirePageRequestDTO pageRequest, Pageable pageable) {

        QHire hire = QHire.hire;
        QProducer producer = QProducer.producer;

        JPQLQuery<Hire> jpqlQuery = from(hire);
        jpqlQuery.leftJoin(producer).on(hire.producer.eq(producer));

        JPQLQuery<Tuple> tuple = jpqlQuery.select(hire, producer);

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        BooleanExpression expression = hire.producer.producerId.eq(pageRequest.getProducerId());

        booleanBuilder.and(expression);

        tuple.where(booleanBuilder);

        Sort sort = pageable.getSort();

        sort.stream().forEach(order -> {
            Order direction = order.isAscending() ? Order.ASC : Order.DESC;
            String prop = order.getProperty();

            PathBuilder orderByExpression = new PathBuilder(Hire.class, "hire");
            tuple.orderBy(new OrderSpecifier(direction, orderByExpression.get(prop)));
        });

        tuple.groupBy(hire);

        tuple.offset(pageable.getOffset());
        tuple.limit(pageable.getPageSize());

        List<Tuple> result = tuple.fetch();


        long count = tuple.fetchCount();

        return new PageImpl<>(result.stream()
                .map(t -> t.toArray()).collect(Collectors.toList()),
                pageable,
                count);
    }
}
