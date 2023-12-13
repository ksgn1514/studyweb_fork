package com.weavus.studyweb.repository;


import com.weavus.studyweb.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserid(String username);
}
