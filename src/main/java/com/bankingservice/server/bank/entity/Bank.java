package com.bankingservice.server.bank.entity;

import com.bankingservice.server.common.constants.DataStcd;
import com.bankingservice.server.utill.DateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Table(name = "HRST_BNKM_BNK_MNGMT")
public class Bank extends DateTime {

    @Id
    @Column(name = "BNK_CD")
    // 원래 이름 그대로 사용했는데 JPA Repository 에서 인식을 못해서 
    // 이런식으로 바꿔줌
    private Long bnkCd;

    @Column
    private String BNK_NM;

    @Column
    private DataStcd STCD;

    @Column
    private Long LST_MDF_MBNO;
}