package shop.goodcasting.api.apply.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.goodcasting.api.apply.domain.Apply;
import shop.goodcasting.api.apply.domain.ApplyDTO;
import shop.goodcasting.api.apply.repository.ApplyRepository;

import javax.transaction.Transactional;
import java.util.List;
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
}
