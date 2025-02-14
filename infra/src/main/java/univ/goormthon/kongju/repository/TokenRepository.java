package univ.goormthon.kongju.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import univ.goormthon.kongju.entity.TokenEntity;

@Repository
public interface TokenRepository extends CrudRepository<TokenEntity, Long>{
}
