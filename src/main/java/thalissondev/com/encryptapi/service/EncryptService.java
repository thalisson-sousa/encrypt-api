package thalissondev.com.encryptapi.service;

import org.jasypt.util.text.AES256TextEncryptor;
import org.springframework.stereotype.Service;

@Service
public class EncryptService {

    private AES256TextEncryptor textEncryptor;

    //Injeção de Dependencia na Mão
    public EncryptService(AES256TextEncryptor textEncryptor) {
        this.textEncryptor = textEncryptor;
    }

    public String encryptData(String data) {
        return textEncryptor.encrypt(data);
    }

    public String decryptData(String encryptedData) {
        return textEncryptor.decrypt(encryptedData);
    }
}
