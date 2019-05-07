package Exceptions;

import Infomation.User;

/**
 * Created by mark on 12/05/15.
 */
public class PasswordHashFailure extends RuntimeException {
    public PasswordHashFailure(String s) {
        super(s);
    }
}
