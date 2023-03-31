package com.bankingservice.server.entity;

import com.bankingservice.server.constants.MemberStcd;
import com.bankingservice.server.utill.DateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicInsert;

@Entity
@Getter
@Setter
@SuperBuilder
@DynamicInsert
@Table(name = "HRST_MEMM_MEM_MNGMT")
public class Member extends DateTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long MBNO;

    @Column(columnDefinition = "varchar(10) default `01`")
    private MemberStcd STCD;

    @Column(unique = true, nullable = false)
    private String ID;

    @Column
    private String PW;

    @Column
    private String REG_NUM;

    @Column
    private String NM;

    @Column
    private Long PRT_MBNO;

    @Column(columnDefinition = "INT default 0")
    private Long PNT;

    @Column
    private Long LST_MDF_MBNO;


}
