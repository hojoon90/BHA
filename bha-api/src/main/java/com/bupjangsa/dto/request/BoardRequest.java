package com.bupjangsa.dto.request;

import com.bupjangsa.domain.board.BoardType;
import lombok.Builder;
import lombok.Getter;

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

        private Long id;
        private String title;
        private String content;
        private BoardType boardType;

    }

    @Getter
    @Builder
    public static class PostDeleteRequest {

        private Long id;
        private BoardType boardType;

    }

}
