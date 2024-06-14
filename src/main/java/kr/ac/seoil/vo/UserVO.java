package kr.ac.seoil.vo;

import lombok.Data;

import java.util.Date;


@Data
public class UserVO{
    private Long seqUser;
    private String userId;
    private String userNm;
    private String userPw;
    private String useYn;
    private Date regDt;
    private Date modDt;
    private String modId;

    public UserVO() {this.regDt = new Date();}
}

