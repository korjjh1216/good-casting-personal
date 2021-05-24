package shop.goodcasting.api.common.domain;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Data
public class PageResultDTO<D, E> {
    private List<D> dtoList;

    public PageResultDTO(Page<E> result, Function<E, D> fn) {
        dtoList = result.stream().map(fn).collect(Collectors.toList());
    }
}
