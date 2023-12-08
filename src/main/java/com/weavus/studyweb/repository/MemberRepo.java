package com.weavus.studyweb.repository;

import com.weavus.studyweb.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MemberRepo extends JpaRepository<Member, String> {
    @Query(value = "select * from member where id=? and password = ?", nativeQuery = true)
    Member findByIdAndPassword(String id, String password);
}
