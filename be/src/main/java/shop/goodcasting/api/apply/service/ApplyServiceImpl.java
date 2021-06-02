package shop.goodcasting.api.apply.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import shop.goodcasting.api.apply.domain.Apply;
import shop.goodcasting.api.apply.domain.ApplyDTO;
import shop.goodcasting.api.apply.domain.ApplyListDTO;
import shop.goodcasting.api.apply.repository.ApplyRepository;
import shop.goodcasting.api.article.profile.domain.ProfileListDTO;
import shop.goodcasting.api.common.domain.PageRequestDTO;
import shop.goodcasting.api.common.domain.PageResultDTO;
import shop.goodcasting.api.user.actor.domain.Actor;

import javax.transaction.Transactional;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ApplyServiceImpl implements ApplyService{
    private final ApplyRepository applyRepo;

    @Override
    public ApplyDTO apply(ApplyDTO applyDTO) {
        Apply apply = dto2EntityAll(applyDTO);
        applyRepo.save(apply);
        return null;
    }

    @Override
    @Transactional
    public List<ApplyDTO> findAllByHireId(Long hireId) {
        return applyRepo.findAllByHireId(hireId).stream().map(message -> {
            return entity2DtoAll(message);
        }).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public PageResultDTO<ApplyListDTO, Object[]> getApplicantList(PageRequestDTO pageRequest) {
        Page<Object[]> result;
        Function<Object[], ApplyListDTO> fn;
        result = applyRepo.applicantList(pageRequest,pageRequest
                .getPageable(Sort.by(pageRequest.getSort()).descending()));

        fn = (entity -> entity2DtoAll3((Apply) entity[0]));
        return new PageResultDTO<>(result, fn);
    }

}
