package com.bupjangsa.domain.board.entity;


import com.bupjangsa.domain.common.BaseEntity;
import com.bupjangsa.domain.board.BoardType;
import jakarta.persistence.*;
import lombok.*;


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_board")
public class Board extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @Column(nullable = false)
    private BoardType boardType;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String createdBy;

    private String updatedBy;


}
