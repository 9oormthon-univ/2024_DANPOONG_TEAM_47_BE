package univ.goormthon.kongju.entity;


import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@RedisHash(value = "token", timeToLive = 60 * 60 * 24 * 7) // 60 * 60 * 24 * 7(초) = 7일
public class TokenEntity extends BaseTimeEntity {

    @Id
    private String email;

    @Indexed
    private String refreshToken;

    public TokenEntity() {
    }

    @Builder
    public TokenEntity(String email, String refreshToken) {
        this.email = email;
        this.refreshToken = refreshToken;
    }

    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
