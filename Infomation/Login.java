package Infomation;

/**
 * Created by mark on 14/05/15.
 */
public class Login extends Data {
    private String email;
    private char[] password;

    public Login(String email, char[] password) {
        this.email = email;
        this.password = password;
    }

    public char[] getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public int getId() {
        return 0;
    }

    public void setId(int id) {
        throw new UnsupportedOperationException();
    }
    @Override
    public boolean isData(Data i) {
        if (i instanceof User) {
            return ((User) i).authenticate(this);
        }
        return false;

    }

    @Override
    public DataType getDataType() {
        return DataType.User;
    }

    @Override
    public boolean matches(Data i) {
        return false;
    }
}
