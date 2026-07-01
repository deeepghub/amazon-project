package com.amazon.repo;

import com.amazon.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, String> {

    boolean existsByUsername(String username);
    Users findByUsername(String username);
}
