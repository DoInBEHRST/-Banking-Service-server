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
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Table(name = "HRST_MEMM_MEM_MNGMT")
public class Member extends DateTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long MBNO;

    @Column
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

    @Column
    private Long PNT;

    @Column
    private Long LST_MDF_MBNO;

    public void checkUsable() {
        if (STCD.equals(MemberStcd.STCD_NOT_USE)) {
            throw new IllegalStateException("존재하지 않는 아이디 입니다.");
        }
    }
}
