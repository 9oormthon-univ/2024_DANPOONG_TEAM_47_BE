package univ.goormthon.kongju;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class KongjuApplication {

    public static void main(String[] args) {
        SpringApplication.run(KongjuApplication.class, args);
    }

}
