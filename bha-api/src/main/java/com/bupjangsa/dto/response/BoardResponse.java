package com.bupjangsa.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

public class BoardResponse {

    private BoardResponse(){
        /*Do Nothing*/
    }

    @Getter
    @Builder
    public static class PostInfo{
        private Long postId;
        private String title;
        private String contents;
        private String createdBy;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }

}
