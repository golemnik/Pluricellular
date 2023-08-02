package com.golem.netCell.innerMechanics;

import com.golem.core.schemas.providedRealisations.CellPrinter;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Crypt {

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
