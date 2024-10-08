package com.onetool.server.global.redis.repository;

import com.onetool.server.global.redis.domain.Token;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends CrudRepository<Token, String> {
}
