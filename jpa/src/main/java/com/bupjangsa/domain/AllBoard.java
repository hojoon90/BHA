package com.bupjangsa.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


@Data
@Entity
public class AllBoard {

    @Id
    private int no;
    private String boardType;
    private String title;
    private String content;
    private String deleteYn;
    private Date regDate;
    private String regUserId;
    private Date altDate;
    private String altUserId;


}
