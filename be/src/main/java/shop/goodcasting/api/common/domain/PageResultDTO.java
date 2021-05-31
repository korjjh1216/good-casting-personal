package shop.goodcasting.api.common.domain;

import lombok.Data;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Log4j2
@ToString
@Data
public class PageResultDTO<D, E> {
    private List<D> dtoList;

    //총 페이지 번호
    private int totalPage;

    //현재 페이지 번호
    private int page;
    //목록 사이즈
    private int size;

    //시작 페이지 번호, 끝 페이지 번호
    private int start, end;

    //이전, 다음
    private boolean prev, next;

    private long totalElement;

    //페이지 번호  목록
    private List<Integer> pageList;

    public PageResultDTO(Page<E> result, Function<E, D> fn) {
        dtoList = result.stream().map(fn).collect(Collectors.toList());

        totalPage = result.getTotalPages();
        totalElement = result.getTotalElements();

        makePageList(result.getPageable());
    }

    private void makePageList(Pageable pageable){

        this.page = pageable.getPageNumber() + 1; // 0부터 시작하므로 1을 추가
        this.size = pageable.getPageSize();

        //temp end page
        int tempEnd = (int)(Math.ceil(page/10.0)) * 10;

        log.info("##############temp end: ##################" + tempEnd);

        start = tempEnd - 9;

        prev = start > 1;

        end = totalPage > tempEnd ? tempEnd: totalPage;

        next = totalPage > tempEnd;

        pageList = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());

    }
}