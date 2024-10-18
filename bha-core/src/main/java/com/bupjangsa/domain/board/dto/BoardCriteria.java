package com.bupjangsa.domain.board.dto;

import com.bupjangsa.type.BoardType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BoardCriteria {

    @Getter
    @Builder
    public static class SearchList {

        private final BoardType boardType;

    }

}
