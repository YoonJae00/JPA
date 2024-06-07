package com.ohgiraffers.mapping.section01.entity;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class MemberRegistService {

    @Autowired
    private MemberRepository memberRepository;

    @Transactional
    public void registMember(MemberRegistDTO memberRegistDTO) {
        Member member = new Member(
                memberRegistDTO.getMemberId(),
                memberRegistDTO.getMemberPw(),
                memberRegistDTO.getMemberName(),
                memberRegistDTO.getPhone(),
                memberRegistDTO.getAddress(),
                memberRegistDTO.getEnrollDate(),
                memberRegistDTO.getMemberRole(),
                memberRegistDTO.getStatus()
        );

        memberRepository.save(member);
    }

    @Transactional
    public String registMemberAndFindName(MemberRegistDTO memberRegist) {

        registMember(memberRegist);

        return memberRepository.findName(memberRegist.getMemberId());
    }
}
