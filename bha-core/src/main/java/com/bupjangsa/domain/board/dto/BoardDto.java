package com.bupjangsa.domain.board.dto;

import com.bupjangsa.domain.board.type.BoardType;
import com.bupjangsa.domain.board.entity.Board;
import com.bupjangsa.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

public class BoardDto {

    private BoardDto(){/*Do Nothing*/}

    @Getter
    @Builder
    public static class Register{

        private String title;
        private String contents;
        private BoardType boardType;
        private Long userId;


        public Board toEntity(User user){
            return Board.builder()
                    .title(title)
                    .contents(contents)
                    .boardType(boardType)
                    .createdBy(user)
                    .build();
        }

    }

    @Getter
    @Builder
    public static class Update{

        private Long postNo;
        private String title;
        private String contents;
        private BoardType boardType;
        private Long userId;

    }

    @Getter
    @Builder
    public static class Delete{

        private Long postNo;
        private BoardType boardType;
        private Long userId;
    }

    @Getter
    @Builder
    public static class PostInfo {

        private Long postNo;
        private String title;
        private String contents;
        private String createdBy;
        private LocalDateTime createdAt;

        public static PostInfo from(Board entity) {
            return PostInfo.builder()
                    .postNo(entity.getPostNo())
                    .title(entity.getTitle())
                    .contents(entity.getContents())
                    .createdBy(entity.getCreatedBy().getAccountId())
                    .createdAt(entity.getCreatedAt())
                    .build();
        }

    }

}
