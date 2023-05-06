package com.synergies.synergy.auth.util;

import org.mindrot.jbcrypt.BCrypt;

public class SaltUtil {
    public static String encodePassword(String password, String salt){
        return BCrypt.hashpw(password, salt);
    }

    public static String generateSalt(){
        return BCrypt.gensalt();
    }
}
