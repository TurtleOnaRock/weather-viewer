package com.turtleOnARock.weatherViewer.servicies;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtils {

    public static final int SALT = 10;

    public static String hashPassword(String password){
        return BCrypt.hashpw(password, BCrypt.gensalt(SALT));
    }

    public static Boolean checkPassword(String storedHashedPassword, String inputPassword){
        return BCrypt.checkpw(inputPassword, storedHashedPassword);
    }
}
