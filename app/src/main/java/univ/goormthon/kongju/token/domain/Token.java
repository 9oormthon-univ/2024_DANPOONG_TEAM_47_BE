package univ.goormthon.kongju.token.domain;

import lombok.Getter;

@Getter
public class Token {

    private String email;
    private String refreshToken;


    public Token(String email, String refreshToken) {
        this.email = email;
        this.refreshToken = refreshToken;
    }
}
