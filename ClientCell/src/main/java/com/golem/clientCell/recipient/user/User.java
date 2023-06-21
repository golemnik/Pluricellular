package com.golem.clientCell.recipient.user;

import com.golem.core.schemas.providedRealisations.CellPrinter;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User {
    private final String login;
    private final String password;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public static String encrypt (String password) {
        try {
            StringBuilder encryptedPassword;
            MessageDigest md = MessageDigest.getInstance("SHA-384");
            byte[] messageDigest = md.digest(password.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            encryptedPassword = new StringBuilder(no.toString(16));
            while (encryptedPassword.length() < 32) {
                encryptedPassword.insert(0, "0");
            }
            return encryptedPassword.toString();
        }
        catch (NoSuchAlgorithmException e) {
            CellPrinter.setMessage(CellPrinter.Colorist.RED(e.getMessage()));
            return password;
        }
    }
}
