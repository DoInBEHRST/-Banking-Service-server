package com.bankingservice.server.entity;

import com.bankingservice.server.constants.MemberStcd;
import com.bankingservice.server.utill.DateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "HRST_MEMM_MEM_MNGMT")
public class Member extends DateTime {

    @Id
    private Long MBNO;

    @Column(length = 10)
    private MemberStcd STCD;

    @Column(unique = true, nullable = false)
    private String ID;

    private String PW;
    private String REG_NUM;
    private String NM;
    private Long PRT_MBNO;
    private Long PNT;
    private Long LST_MDF_MBNO;


}
