package com.bupjangsa.service;

import com.bupjangsa.domain.board.entity.Board;
import com.bupjangsa.domain.board.infra.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public void postArticle(Board board){
        boardRepository.save(board);
    }


    @Transactional
    public void putArticle(Board board){
        boardRepository.findById(board.getPostId());
    }

    @Transactional
    public void deleteArticle(Board board){
        boardRepository.delete(board);
    }

    //단건 조회
    public Board selectArticle(String boardType, int boardNo){
        return boardRepository.selectArticle(boardType, boardNo);
    }

    //게시물 목록 조회
    public List<Board> selectArticleList(String boardType, int pageNo, int pageSize){
        // 리스트 페이징. TODO 게시물이 많아질때 처리 고민 필요.
        List<Board> boardList = boardRepository.selectArticleList(boardType);

        return boardList.stream()
                .skip(pageSize * (pageNo -1))
                .limit(pageSize)
                .collect(Collectors.toList());
    }

}
