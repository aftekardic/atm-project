package com.atm.backend.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.atm.backend.data.entity.TokenEntity;

@Repository
@EnableJpaRepositories
public interface TokenRepository extends JpaRepository<TokenEntity, Long> {
    TokenEntity findByToken(String token);

    TokenEntity findByUserId(Long user_id);

}
