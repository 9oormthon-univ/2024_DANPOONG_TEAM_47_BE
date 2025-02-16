package univ.goormthon.kongju.jwt;

import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

@EnableRedisRepositories
public interface TokenRepository extends CrudRepository<TokenEntity, String>{

    boolean existsTokenEntityByRefreshToken(String token);

    Optional<TokenEntity> findByRefreshToken(String token);
}
