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
import shop.goodcasting.api.article.profile.domain.QProfile;
import shop.goodcasting.api.common.domain.PageRequestDTO;
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
    public Page<Object[]> searchPage(PageRequestDTO pageRequest, Pageable pageable) {

        log.info("-------------------Search Profile Page Enter------------------------------------");

        String type = pageRequest.getType();
        QProfile profile = QProfile.profile;
        QFileVO file = QFileVO.fileVO;
        QActor actor = QActor.actor;

        JPQLQuery<Profile> jpqlQuery = from(profile);
        jpqlQuery.leftJoin(actor).on(profile.actor.eq(actor));
        jpqlQuery.leftJoin(file).on(file.profile.eq(profile));

        JPQLQuery<Tuple> tuple = jpqlQuery.select(profile, actor , file );

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        BooleanExpression expression = profile.profileId.gt(0L);

        booleanBuilder.and(expression);

        if (type != null) {
            String[] typeArr = type.split("");

            BooleanBuilder conditionBuilder = new BooleanBuilder();

            for (String t: typeArr) {
                log.info("search type loop enter");
                switch (t) {
                    case "r":
                        log.info("search type: " + t);
                        conditionBuilder.and(profile.resemble.contains(pageRequest.getRkeyword()));
                        break;
                    case "a":
                        log.info("search type: " + t);
                        conditionBuilder.and(actor.age.between(pageRequest.getAfrom(), pageRequest.getAto()));
                        break;
                    case "g":
                        log.info("search type: " + t);
                        conditionBuilder.and(actor.gender.contains(pageRequest.getGkeyword()));
                        break;
                    case "h":
                        log.info("search type: " + t);
                        conditionBuilder.and(actor.height.between(pageRequest.getHfrom(), pageRequest.getHto()));
                        break;
                    case "w":
                        log.info("search type: " + t);
                        conditionBuilder.and(actor.weight.between(pageRequest.getWfrom(), pageRequest.getWto()));
                        break;
                }
            }
            booleanBuilder.and(conditionBuilder);
        }

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

        return new PageImpl<Object[]>(result.stream()
                .map(t -> t.toArray()).collect(Collectors.toList()),
                pageable,
                count);
    }
}