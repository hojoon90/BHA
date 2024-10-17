package com.bupjangsa.dto.request;

import com.bupjangsa.domain.board.BoardType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.PageRequest;

public class BoardRequest {

    private BoardRequest(){
        /*Do Nothing*/
    }

    @Getter
    @Builder
    public static class PostRegisterRequest {

        private String title;
        private String content;
        private BoardType boardType;

    }

    @Getter
    @Builder
    public static class PostUpdateRequest {

        private Long postNo;
        private String title;
        private String content;
        private BoardType boardType;

    }

    @Getter
    @Builder
    public static class PostDeleteRequest {

        private Long postNo;
        private BoardType boardType;

    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class PageablePostSearchRequest {

        @Schema(description = "페이지 커서", example = "0")
        @NotNull
        @Min(0)
        private final Integer offset;

        @Schema(description = "페이지 당 조회 개수", example = "10")
        @NotNull
        @Min(1)
        private final Integer limit;

        @Schema(description = "게시판 타입")
        private final String boardType;

        public PageRequest getPageRequest() {
            return PageRequest.of(this.getOffset(), this.getLimit());
        }
    }

}
