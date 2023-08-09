package com.wanted.backend.member.repository;

import com.wanted.backend.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

}
