package thalissondev.com.encryptapi.config;

import org.jasypt.util.text.AES256TextEncryptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class jasyptConfig {
    @Value("${encrypt.key}")
    private String secretKey;

    @Bean
    public AES256TextEncryptor textEncryptor() {
        AES256TextEncryptor textEncryptor = new AES256TextEncryptor();
        textEncryptor.setPassword(secretKey);
        return textEncryptor;
    }
}
