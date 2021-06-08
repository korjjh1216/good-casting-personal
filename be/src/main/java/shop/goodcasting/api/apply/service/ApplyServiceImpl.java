package shop.goodcasting.api.apply.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import shop.goodcasting.api.apply.domain.*;
import shop.goodcasting.api.apply.repository.ApplyRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ApplyServiceImpl implements ApplyService{
    private final ApplyRepository applyRepo;

    @Override
    @Transactional
    public ApplyDTO apply(ApplyDTO applyDTO) {

        Long profileId = applyDTO.getProfile().getProfileId();
        Long hireId = applyDTO.getHire().getHireId();

        List<Long> applyList = applyRepo.duplicateCheck(profileId, hireId);

        if(applyList.size() >= 1 ) {
            throw new RuntimeException("duplicate apply");
        } else {
            applyRepo.save(dto2EntityAll(applyDTO));
        }
        return null;
    }

    @Override
    @Transactional
    public List<ApplyDTO> findAllByHireId(Long hireId) {
        return applyRepo.findAllByHireId(hireId).stream().map(message -> entity2DtoAll(message))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ApplyPageResultDTO<ApplyListDTO, Object[]> getApplicantList(ApplyPageRequestDTO pageRequest) {
        Page<Object[]> result;
        Function<Object[], ApplyListDTO> fn;
        result = applyRepo.applicantList(pageRequest,pageRequest
                .getPageable(Sort.by(pageRequest.getSort()).descending()));

        fn = (entity -> entity2DtoAll2((Apply) entity[0]));
        return new ApplyPageResultDTO<>(result, fn, pageRequest);
    }

    @Override
    @Transactional
    public ApplyPageResultDTO<ApplyListDTO, Object[]> getApplyList(ApplyPageRequestDTO pageRequest) {
        Page<Object[]> result;
        Function<Object[], ApplyListDTO> fn;
        result = applyRepo.applyList(pageRequest,pageRequest
                .getPageable(Sort.by(pageRequest.getSort()).descending()));

        fn = (entity -> entity2DtoAll2((Apply) entity[0]));
        return new ApplyPageResultDTO<>(result, fn, pageRequest);
    }

    @Transactional
    public void deleteApply(Long applyId) {
        applyRepo.deleteById(applyId);
    }

}
