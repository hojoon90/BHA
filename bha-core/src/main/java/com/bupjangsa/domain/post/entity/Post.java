package com.bupjangsa.domain.post.entity;


import com.bupjangsa.domain.common.BaseEntity;
import com.bupjangsa.type.BoardType;
import com.bupjangsa.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;


@Getter
@Builder
@Entity
@Table(name = "t_post")
@SQLDelete(sql = "UPDATE t_post SET deleted = true where post_id = ?")
@Where(clause = "deleted = false")  //삭제가 아닌 유저만 조회하도록 조건처리
@NoArgsConstructor
@AllArgsConstructor
public class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @Column(nullable = false)
    @Convert(converter = BoardType.Converter.class)
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

    public void updatePostData(String title, String contetns, User user){
        this.title = title;
        this.contents = contetns;
        this.lastModifiedBy = user;
    }


}
