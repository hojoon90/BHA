package com.bupjangsa.domain.board.dto;

import com.bupjangsa.domain.board.type.BoardType;
import lombok.Builder;
import lombok.Getter;

public class BoardCriteria {

    private BoardCriteria() {/*Do Nothing*/}
    @Getter
    @Builder
    public static class SearchList {

        private final BoardType boardType;

    }

}
