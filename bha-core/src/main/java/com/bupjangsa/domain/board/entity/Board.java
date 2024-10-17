package com.bupjangsa.domain.board.entity;


import com.bupjangsa.domain.common.BaseEntity;
import com.bupjangsa.domain.board.type.BoardType;
import com.bupjangsa.domain.user.entity.User;
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
    @Enumerated(EnumType.STRING)
    private BoardType boardType;

    @Column(nullable = false)
    private Long postNo;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    @Lob
    private String contents;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;

    @ManyToOne
    @JoinColumn(name = "last_modified_by")
    private User lastModifiedBy;

    public void updateBoardData(String title, String contetns, User user){
        this.title = title;
        this.contents = contetns;
        this.lastModifiedBy = user;
    }


}
