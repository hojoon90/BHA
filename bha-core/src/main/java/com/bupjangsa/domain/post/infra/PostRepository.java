package com.bupjangsa.domain.post.infra;

import com.bupjangsa.type.BoardType;
import com.bupjangsa.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long>, SearchPostRepository {

    Optional<Post> findByPostNoAndBoardType(Long postNo, BoardType boardType);

    @Query("select b.postNo from Post b " +
            "where b.boardType = :boardType " +
            "order by b.postNo desc " +
            "limit 1 ")
    Optional<Long> findPostNoByBoardTypeOrderByPostNoDesc(@Param("boardType") BoardType boardType);
}
