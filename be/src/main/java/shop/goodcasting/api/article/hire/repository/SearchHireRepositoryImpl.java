package shop.goodcasting.api.article.hire.repository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import shop.goodcasting.api.article.hire.domain.Hire;
import shop.goodcasting.api.article.hire.domain.QHire;
import shop.goodcasting.api.common.domain.PageRequestDTO;
import shop.goodcasting.api.file.domain.QFileVO;
import shop.goodcasting.api.user.producer.domain.QProducer;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public class SearchHireRepositoryImpl extends QuerydslRepositorySupport implements SearchHireRepository {

    public SearchHireRepositoryImpl() {
        super(Hire.class);
    }

    @Override
    @Transactional
    public Page<Object[]> searchPage(PageRequestDTO pageRequest, Pageable pageable) {
        log.info("----------------------Search Hire Page Enter------------------------------");

        String type = pageRequest.getType();
        QHire hire = QHire.hire;

        QProducer producer = QProducer.producer;

        JPQLQuery<Hire> jpqlQuery = from(hire);
        jpqlQuery.leftJoin(producer).on(hire.producer.eq(producer));


        JPQLQuery<Tuple> tuple = jpqlQuery.select(hire, producer);

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        BooleanExpression expression = hire.hireId.gt(0L);

        booleanBuilder.and(expression);

        if (type != null) {
            String[] typeArr = type.split("");

            BooleanBuilder conditionBuilder = new BooleanBuilder();

            for (String t : typeArr) {
                log.info("search type loop enter");

                switch (t) {
                    case "g":
                        log.info("search guarantee, type: " + t);
                        conditionBuilder.and(hire.guarantee.between(pageRequest.getGfrom(), pageRequest.getGto()));
                        break;
                    case "f":
                        log.info("search filming, type: " + t);
                        conditionBuilder.and(hire.filming.between(pageRequest.getFfrom(), pageRequest.getFto()));
                        break;
                    case "t":
                        log.info("search title, type: " + t);
                        conditionBuilder.or(hire.title.contains(pageRequest.getTkeyword()));
                        break;
                    case "p":
                        log.info("search project, type: " + t);
                        conditionBuilder.or(hire.project.contains(pageRequest.getPkeyword()));
                        break;
                    case "c":
                        log.info("search contents, type: " + t);
                        conditionBuilder.or(hire.contents.contains(pageRequest.getConKeyword()));
                        break;
                    case "C":
                        log.info("search cast, type: " + t);
                        conditionBuilder.or(hire.cast.contains(pageRequest.getCastKeyword()));
                        break;
                    default : break;
                }
            }
            booleanBuilder.and(conditionBuilder);
        }

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

        result.forEach(t -> {
            log.info("search hire page tuple: " + t);
        });

        long count = tuple.fetchCount();

        log.info("COUNT: " + count);

        return new PageImpl<>(result.stream()
                .map(t -> t.toArray()).collect(Collectors.toList()), pageable, count);
    }
}