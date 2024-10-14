package com.bupjangsa.domain.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "USER")
public class UserInfo {

    @Id
    @Column(name = "NO", nullable = false)
    private int no;

    @Column(name = "USER_ID", nullable = false)
    private String userId;

    @Column(name = "SECRET_KEY", nullable = false)
    private String secretKey;

    private String confirmSecretKey;

    @Column(name = "AUTHORITY", nullable = false)
    private String authority;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "REG_DATE", nullable = false)
    private Date regDate;

    @Column(name = "ALT_DATE")
    private Date altDate;

    //TODO 삭제 여부 추가

}
