package shop.goodcasting.api.apply.repository;

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
import shop.goodcasting.api.apply.domain.Apply;
import shop.goodcasting.api.apply.domain.ApplyPageRequestDTO;
import shop.goodcasting.api.apply.domain.QApply;
import shop.goodcasting.api.user.actor.domain.QActor;
import shop.goodcasting.api.user.producer.domain.QProducer;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

public class SearchApplyRepositoryImpl extends QuerydslRepositorySupport implements SearchApplyRepository {

    public SearchApplyRepositoryImpl() {
        super(Apply.class);
    }

    @Override
    @Transactional
    public Page<Object[]> applicantList(ApplyPageRequestDTO pageRequest, Pageable pageable) {

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

        long count = tuple.fetchCount();

        return new PageImpl<>(result.stream()
                .map(t -> t.toArray()).collect(Collectors.toList()),
                pageable,
                count);
    }

    @Override
    @Transactional
    public Page<Object[]> applyList(ApplyPageRequestDTO pageRequest, Pageable pageable) {

        QApply apply = QApply.apply;
        QProducer producer = QProducer.producer;
        QActor actor =QActor.actor;

        JPQLQuery<Apply> jpqlQuery = from(apply);
        jpqlQuery.innerJoin(producer).on(apply.hire.producer.eq(producer));
        jpqlQuery.innerJoin(actor).on(apply.profile.actor.eq(actor));

        JPQLQuery<Tuple> tuple = jpqlQuery.select(apply,producer, actor);

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        BooleanExpression expression1 = apply.profile.actor.actorId.eq(pageRequest.getActorId());

        booleanBuilder.and(expression1);

        tuple.where(booleanBuilder);

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

        long count = tuple.fetchCount();

        return new PageImpl<>(result.stream()
                .map(t -> t.toArray()).collect(Collectors.toList()),
                pageable,
                count);
    }
}