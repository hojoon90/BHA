package com.bupjangsa.domain.board.infra;

import com.bupjangsa.domain.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long>, SearchBoardRepository {
}
