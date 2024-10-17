package com.bupjangsa.dto.response;

import com.bupjangsa.domain.board.dto.BoardDto;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class BoardResponse {

    private BoardResponse(){
        /*Do Nothing*/
    }

    @Getter
    @Builder
    public static class PostDetail {
        private Long postNo;
        private String title;
        private String contents;
        private String createdBy;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        public static PostDetail from(BoardDto.PostInfo dto){
            return PostDetail.builder()
                    .postNo(dto.getPostNo())
                    .title(dto.getTitle())
                    .contents(dto.getContents())
                    .createdBy(dto.getCreatedBy())
                    .createdAt(dto.getCreatedAt())
                    .build();
        }
    }

    @Getter
    @Builder
    public static class PostPage{
        private long count;
        private int totalPages;
        private long pageSize;
        private List<PostDetail> postDetails;

        public static PostPage of(long count, int totalPages, long pageSize, List<BoardDto.PostInfo> postDetails) {
            final List<PostDetail> collect = postDetails.stream().map(PostDetail::from).toList();
            return PostPage.builder()
                    .count(count)
                    .totalPages(totalPages)
                    .pageSize(pageSize)
                    .postDetails(collect)
                    .build();
        }
    }

}
