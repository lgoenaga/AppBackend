package com.iudigital.appbackend.repository;

import com.iudigital.appbackend.model.User;
import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    User getUserById(Long id);

    boolean existsById(@Nonnull Long id);
}
