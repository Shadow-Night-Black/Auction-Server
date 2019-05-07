package Infomation;


import Exceptions.PasswordHashFailure;
import Exceptions.SaltGenerationFailure;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User extends Data implements Serializable {
    private final static int ITERATIONS = 20000;
    private final static int KEYLENGTH = 160;
    private final static String ALGORITHM = "PBKDF2WithHmacSHA1";
    private final static String SALT = "SHA1PRNG";

	private String firstName, familyName, email;
	private int id;

	private byte[] password;
    private byte[] salt;

    public User(String firstName, String familyName,  String email, char[] password) {
        this.firstName = firstName;
        this.familyName = familyName;
        this.email = email;
        this.salt = generateSalt();
        this.password = hash(password, salt);
    }

    private static byte[] generateSalt() {
        try {
            SecureRandom random = SecureRandom.getInstance(SALT);
            byte[] salt = new byte[8];
            random.nextBytes(salt);
            return salt;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        throw new SaltGenerationFailure("Salt failed to be generated!");
    }

    @Override
    public boolean isData(Data i) {
        if (i instanceof User) {
            User user = (User) i;
            return user.getEmail().equals(email) ||
                    user.getId() == id;

        } else if (i instanceof Login) {
            return authenticate((Login) i);
        }
        return false;
    }

    @Override
    public DataType getDataType() {
        return DataType.User;
    }

    @Override
    public boolean matches(Data i) {
        if (i == null) {
            return true;
        }else if (i instanceof User) {
            User user = (User) i;
            if (user.getId() == id ||
                    user.getFirstName().equals(firstName) ||
                    user.getFamilyName().equals(familyName) ||
                    user.getEmail() == email) {
                return true;
            }
        }
        return false;
    }


    private boolean checkPassword(char[] password) {
        return Arrays.equals(this.password, (hash(password, salt)));
    }

    private static byte[] hash(char[] password, byte[] salt)  {
        try {
            KeySpec spec = new PBEKeySpec(password, salt, ITERATIONS, KEYLENGTH);
            SecretKeyFactory f = SecretKeyFactory.getInstance(ALGORITHM);
            byte[] result = f.generateSecret(spec).getEncoded();

            clearPassword(password);
            return result;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        clearPassword(password);
        throw new PasswordHashFailure("Password failed to hash!");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public String getEmail() {
        return email;
    }

    public String toString() {
        return
                "Name: " + firstName + familyName +
                "\n  Email:  " + email;
    }

    public boolean authenticate(Login login) {
        return (login.getEmail().equals(email) && checkPassword(login.getPassword()));
    }

    public static boolean isValidEmail(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        Pattern p = Pattern.compile(ePattern);
        Matcher m = p.matcher(email);
        return m.matches();

    }

    public static void clearPassword(char[] password) {
        for (int i = 0; i < password.length; i++) {
            password[i] = 0;
        }
    }

    public byte[] getPassword() {
        return password;
    }

}


