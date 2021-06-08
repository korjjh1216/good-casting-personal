package shop.goodcasting.api.article.profile.repository;

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
import shop.goodcasting.api.article.profile.domain.Profile;
import shop.goodcasting.api.article.profile.domain.ProfilePageRequestDTO;
import shop.goodcasting.api.article.profile.domain.QProfile;
import shop.goodcasting.api.file.domain.QFileVO;
import shop.goodcasting.api.user.actor.domain.QActor;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public class SearchProfileRepositoryImpl extends QuerydslRepositorySupport implements SearchProfileRepository {

    public SearchProfileRepositoryImpl() {
        super(Profile.class);
    }

    @Override
    @Transactional
    public Page<Object[]> searchPage(ProfilePageRequestDTO pageRequest, Pageable pageable) {

        log.info("-------------------Search Profile Page Enter------------------------------------");

        QProfile profile = QProfile.profile;
        QFileVO file = QFileVO.fileVO;
        QActor actor = QActor.actor;

        JPQLQuery<Profile> jpqlQuery = from(profile);
        jpqlQuery.leftJoin(actor).on(profile.actor.eq(actor));
        jpqlQuery.leftJoin(file).on(file.profile.eq(profile));

        JPQLQuery<Tuple> tuple = jpqlQuery.select(profile, actor, file);

        BooleanBuilder totalBuilder = new BooleanBuilder();
        BooleanExpression expression = profile.profileId.gt(0L);
        BooleanExpression expression1 = profile.first.eq(true);
        BooleanExpression expression2 = profile.privacy.eq(true);

        totalBuilder.and(expression);
        totalBuilder.and(expression1);
        totalBuilder.and(expression2);

        if (pageRequest.getResembleKey() != null) {
            BooleanBuilder resembleBuilder = makeResembleKey(profile, pageRequest.getResembleKey());
            totalBuilder.and(resembleBuilder);
        }

        if (pageRequest.getGenderKey() != null && pageRequest.getGenderKey().trim().length() != 0) {
            BooleanBuilder genderBuilder = makeGenderKey(profile, pageRequest.getGenderKey());
            totalBuilder.and(genderBuilder);
        }

        if (pageRequest.getAge() != null) {
            BooleanBuilder ageBuilder = makeAgeCondition(profile, pageRequest.getAge().getFrom(), pageRequest.getAge().getTo());
            totalBuilder.and(ageBuilder);
        }

        if (pageRequest.getHeight() != null) {
            BooleanBuilder heightBuilder = makeHeightCondition(profile, pageRequest.getHeight().getFrom(), pageRequest.getHeight().getTo());
            totalBuilder.and(heightBuilder);
        }

        if (pageRequest.getWeight() != null) {
            BooleanBuilder weightBuilder = makeWeightBuilder(profile, pageRequest.getWeight().getFrom(), pageRequest.getWeight().getTo());
            totalBuilder.and(weightBuilder);
        }

        tuple.where(totalBuilder);

        log.info("-----------tuple test----------------");
        log.info(tuple);

        Sort sort = pageable.getSort();

        sort.stream().forEach(order -> {
            Order direction = order.isAscending() ? Order.ASC : Order.DESC;
            String prop = order.getProperty();

            PathBuilder orderByExpression = new PathBuilder(Profile.class, "profile");
            tuple.orderBy(new OrderSpecifier(direction, orderByExpression.get(prop)));
        });
        tuple.groupBy(profile);

        tuple.offset(pageable.getOffset());
        tuple.limit(pageable.getPageSize());

        List<Tuple> result = tuple.fetch();

        result.forEach(tuple1 -> {
            log.info("searchPage() tuple: " + tuple1);
        });

        long count = tuple.fetchCount();

        log.info("COUNT: " + count);

        return new PageImpl<Object[]>(result.stream()
                .map(t -> t.toArray()).collect(Collectors.toList()),
                pageable,
                count);
    }

    private BooleanBuilder makeGenderKey(QProfile profile, String genderKey) {
        BooleanBuilder conditions = new BooleanBuilder();
        conditions.and(profile.actor.gender.eq(genderKey));
        return conditions;
    }

    private BooleanBuilder makeWeightBuilder(QProfile profile, Integer from, Integer to) {
        BooleanBuilder conditions = new BooleanBuilder();
        conditions.and(profile.actor.weight.between(from, to));

        return conditions;
    }

    private BooleanBuilder makeHeightCondition(QProfile profile, Integer from, Integer to) {
        BooleanBuilder conditions = new BooleanBuilder();
        conditions.and(profile.actor.height.between(from, to));

        return conditions;
    }

    private BooleanBuilder makeAgeCondition(QProfile profile, Integer from, Integer to) {

        if (from == null || to == null) {
            return null;
        }

        BooleanBuilder conditions = new BooleanBuilder();
        conditions.and(profile.actor.age.between(from, to));

        return conditions;
    }

    private BooleanBuilder makeResembleKey(QProfile profile, String keyword) {
        if (keyword == null || keyword.trim().length() == 0) {
            return null;
        }

        BooleanBuilder conditions = new BooleanBuilder();

        conditions.and(profile.resemble.contains(keyword));

        return conditions;
    }


    @Override
    @Transactional
    public Page<Object[]> myProfilePage(ProfilePageRequestDTO pageRequest, Pageable pageable) {

        log.info("-------------------Search Profile Page Enter------------------------------------");

        QProfile profile = QProfile.profile;
        QFileVO file = QFileVO.fileVO;
        QActor actor = QActor.actor;

        JPQLQuery<Profile> jpqlQuery = from(profile);
        jpqlQuery.leftJoin(actor).on(profile.actor.eq(actor));
        jpqlQuery.leftJoin(file).on(file.profile.eq(profile));

        JPQLQuery<Tuple> tuple = jpqlQuery.select(profile, actor, file);

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        BooleanExpression expression = profile.profileId.gt(0L);
        BooleanExpression expression1 = file.first.eq(true);
        BooleanExpression expression2 = profile.actor.actorId.eq(pageRequest.getActorId());

        booleanBuilder.and(expression);
        booleanBuilder.and(expression1);
        booleanBuilder.and(expression2);

        tuple.where(booleanBuilder);

        Sort sort = pageable.getSort();

        sort.stream().forEach(order -> {
            Order direction = order.isAscending() ? Order.ASC : Order.DESC;
            String prop = order.getProperty();

            PathBuilder orderByExpression = new PathBuilder(Profile.class, "profile");
            tuple.orderBy(new OrderSpecifier(direction, orderByExpression.get(prop)));
        });
        tuple.groupBy(profile);

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
