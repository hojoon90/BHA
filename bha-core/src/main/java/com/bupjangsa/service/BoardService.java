package com.bupjangsa.service;

import com.bupjangsa.domain.board.type.BoardType;
import com.bupjangsa.domain.board.dto.BoardCriteria;
import com.bupjangsa.domain.board.entity.Board;
import com.bupjangsa.domain.board.infra.BoardRepository;
import com.bupjangsa.domain.user.entity.User;
import com.bupjangsa.domain.user.infra.UserRepository;
import com.bupjangsa.exception.UserDataException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

import static com.bupjangsa.domain.board.dto.BoardDto.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @Transactional
    public void postArticle(Register boardDto){

        User user = userRepository.findById(boardDto.getUserId())
                .orElseThrow(() -> new UserDataException(""));

        //todo 게시판 별 번호 처리
        Board entity = boardDto.toEntity(user);
        boardRepository.save(entity);
    }


    @Transactional
    public void updateArticle(Update boardDto){

        User user = userRepository.findById(boardDto.getUserId())
                .orElseThrow(() -> new UserDataException(""));

        Board board = boardRepository.findByPostNoAndBoardType(boardDto.getPostNo(), boardDto.getBoardType())
                .orElseThrow(() -> new RuntimeException(""));

        board.updateBoardData(boardDto.getTitle(), boardDto.getContents(), user);
    }

    @Transactional
    public void deleteArticle(Delete boardDto){

        User user = userRepository.findById(boardDto.getUserId())
                .orElseThrow(() -> new UserDataException(""));

        Board board = boardRepository.findByPostNoAndBoardType(boardDto.getPostNo(), boardDto.getBoardType())
                .orElseThrow(() -> new RuntimeException(""));

        //등록자가 아닐 경우 예외처리
        if(!board.getCreatedBy().getAccountId().equals(user.getAccountId())){
            throw new RuntimeException("");
        }

        boardRepository.delete(board);
    }

    //단건 조회
    public PostInfo selectArticle(BoardType boardType, Long postNo){
        return boardRepository.findByPostNoAndBoardType(postNo, boardType)
                .map(PostInfo::from)
                .orElseThrow(() -> new RuntimeException(""));
    }

    //게시물 목록 조회
    public Page<PostInfo> selectArticleList(BoardCriteria.SearchList criteria, Pageable pageable){

        Page<Board> boardPage = boardRepository.selectArticlePage(criteria, pageable);

        final var boardInfoList = boardPage.getContent().stream()
                .map(PostInfo::from)
                .collect(Collectors.toList());

        return new PageImpl<>(boardInfoList, pageable, boardPage.getTotalElements());
    }

}
