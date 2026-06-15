package com.example.demo.Service;

import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.Model.Member;
import com.example.demo.Repository.MemberRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private int idCount = 0;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public Member getMemberById(Integer id) {
        Member member = memberRepository.findById(id);
        if (member == null) {
            throw new ResourceNotFoundException("해당 ID의 사용자를 찾을 수 없습니다.");
        }
        return member;
    }

    public Member createMember(Member member) {
        idCount++;
        member.setId(idCount);
        return memberRepository.save(member);
    }

    public Member updateMember(Integer id, Member updateData) {
        Member member = memberRepository.findById(id);
        if (member != null) {
            member.setName(updateData.getName());
            member.setEmail(updateData.getEmail());
            return memberRepository.save(member);
        }
        return null;
    }

    public boolean deleteMember(Integer id) {
        if (memberRepository.existsById(id)) {
            memberRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
