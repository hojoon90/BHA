package com.bupjangsa.domain.board.infra;

import com.bupjangsa.type.BoardType;
import com.bupjangsa.domain.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long>, SearchBoardRepository {

    Optional<Board> findByPostNoAndBoardType(Long postNo, BoardType boardType);

    @Query("select b.postNo from Board b " +
            "where b.boardType = :boardType " +
            "order by b.postNo desc " +
            "limit 1 ")
    Optional<Long> findPostNoByBoardTypeOrderByPostNoDesc(@Param("boardType") BoardType boardType);
}
