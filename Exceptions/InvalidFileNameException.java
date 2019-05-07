package Exceptions;

import java.io.File;

/**
 * Created by mark on 4/20/15.
 */
public class InvalidFileNameException extends RuntimeException {
    private File file;
    public InvalidFileNameException(String s, File file) {
        super(s);
        this.file = file;
    }


    public File getFile() {
        return file;
    }
}
