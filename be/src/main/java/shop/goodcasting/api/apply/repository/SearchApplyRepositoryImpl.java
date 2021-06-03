package shop.goodcasting.api.apply.repository;

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
import shop.goodcasting.api.apply.domain.Apply;
import shop.goodcasting.api.apply.domain.ApplyPageRequestDTO;
import shop.goodcasting.api.apply.domain.QApply;
import shop.goodcasting.api.user.actor.domain.QActor;
import shop.goodcasting.api.user.producer.domain.QProducer;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public class SearchApplyRepositoryImpl extends QuerydslRepositorySupport implements SearchApplyRepository {

    public SearchApplyRepositoryImpl() {
        super(Apply.class);
    }

    @Override
    @Transactional
    public Page<Object[]> applicantList(ApplyPageRequestDTO pageRequest, Pageable pageable) {

        log.info("-------------------Search Profile Page Enter------------------------------------");

        QApply apply = QApply.apply;
        QProducer producer = QProducer.producer;
        QActor actor =QActor.actor;

        JPQLQuery<Apply> jpqlQuery = from(apply);
        jpqlQuery.innerJoin(producer).on(apply.hire.producer.eq(producer));
        jpqlQuery.innerJoin(actor).on(apply.profile.actor.eq(actor));

        JPQLQuery<Tuple> tuple = jpqlQuery.select(apply,producer, actor);

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        BooleanExpression expression1 = apply.hire.producer.producerId.eq(pageRequest.getProducerId());

        booleanBuilder.and(expression1);

        tuple.where(booleanBuilder);

        log.info(tuple);

        Sort sort = pageable.getSort();

        sort.stream().forEach(order -> {
            Order direction = order.isAscending() ? Order.ASC : Order.DESC;
            String prop = order.getProperty();

            PathBuilder orderByExpression = new PathBuilder(Apply.class, "apply");
            tuple.orderBy(new OrderSpecifier(direction, orderByExpression.get(prop)));
        });
        tuple.groupBy(apply);

        tuple.offset(pageable.getOffset());
        tuple.limit(pageable.getPageSize());

        List<Tuple> result = tuple.fetch();

        result.forEach(tuple1 -> {
            log.info("searchPage() tuple: " + tuple1);
        });

        long count = tuple.fetchCount();

        log.info("COUNT: " + count);

        return new PageImpl<>(result.stream()
                .map(t -> t.toArray()).collect(Collectors.toList()),
                pageable,
                count);
    }
}
