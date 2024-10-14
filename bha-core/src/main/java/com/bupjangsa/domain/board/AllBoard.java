package com.bupjangsa.domain.board;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Date;


@Data
@Entity
@Table(name = "ALL_BOARD")
public class AllBoard {

    @Id
    @Column(name = "NO", nullable = false)
    private int no;

    @Column(name = "BOARD_TYPE", nullable = false)
    private String boardType;

    @Column(name = "TITLE", nullable = false, length = 14)
    private String title;

    @Column(name = "CONTENT", nullable = false)
    private String content;

    @Column(name = "DELETE_YN")
    private String deleteYn;

    @Column(name = "REG_DATE", nullable = false)
    private Date regDate;

    @Column(name = "REG_USER_ID", nullable = false)
    private String regUserId;

    @Column(name = "ALT_DATE")
    private Date altDate;

    @Column(name = "ALT_USER_ID")
    private String altUserId;


}
