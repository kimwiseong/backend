package com.dongguk.ossdev.backend.repository;

import com.dongguk.ossdev.backend.domain.User;
import com.dongguk.ossdev.backend.type.LoginProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findBySocialIdAndProvider(String socialId, LoginProvider provider);

    @Query("SELECT u.id AS id, u.role AS role FROM User u WHERE u.id = :userId AND u.isLogin = true AND u.refreshToken is not null")
    Optional<UserLoginForm> findByIdAndRefreshToken(@Param("userId") Long userId);
    interface UserLoginForm {
        Long getId();
        String getRole();
    }
}
