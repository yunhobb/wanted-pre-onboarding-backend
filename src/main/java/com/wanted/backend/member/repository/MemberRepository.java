package com.wanted.backend.member.repository;

import com.wanted.backend.member.entity.Member;
import java.util.Optional;

public interface MemberRepository {

    Optional<Member> findById(Long id);

    Member save(Member member);

    Optional<Member> findByEmail(String email);
}
