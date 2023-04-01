package com.bankingservice.server.repository;

import com.bankingservice.server.entity.Member;

public class InmemoryMemberRepository implements MemberRepository {

    @Override
    public Member save(Member member) {
        return null;
    }

    @Override
    public Member findByID(String id) {
        return null;
    }

    @Override
    public Member findByIDAndPW(String id, String pw) {
        return null;
    }

    @Override
    public Member findByMBNO(Long MBNO) {
        return null;
    }

//    private final Map<String, UserInfoDTO> data = new HashMap<>();
//
//
//    @Override
//    public UserInfoDTO save(UserInfoDTO member) {
//
//        data.put(member.getId(), member);
//
//        return member;
//    }
//
//    @Override
//    public UserInfoDTO findById(String id) {
//        return data.get(id);
//    }
//
//    @Override
//    public UserInfoDTO findByIdAndPw(String id, String pw) {
//
//        UserInfoDTO member = data.get(id);
//        if (member == null) {
//            return null;
//        }
//
//        if (!member.getPw().equals(pw)) {
//            return null;
//        }
//
//        return member;
//    }
}
