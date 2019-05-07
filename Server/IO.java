package Server;

import Exceptions.InvalidFileNameException;

import java.io.*;

/**
 * Created by mark on 4/4/15.
 */
public class IO {
    public static void checkFile(File file) throws FileNotFoundException {
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        } else if (!file.exists()) {
            throw new FileNotFoundException();
        } else if (!file.isFile()) {
            throw new InvalidFileNameException("File: " + file.toString() + " is not a file!", file);
        }
    }

    public static void checkDirectory(File directory) {
        if (!directory.exists()) {
            directory.mkdirs();
        }else if (!directory.isDirectory()) {
            throw new InvalidFileNameException("File: " + directory.toString() + " is not a directory!", directory);
        }
    }

    public static <E extends Serializable> E loadData(File file) {
        try {
            checkFile(file);
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
            return (E) in.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <E extends Serializable> void saveData(File file, E data) {
        try {
            checkFile(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
            out.writeObject(data);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
