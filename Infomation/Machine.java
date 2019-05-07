package Infomation;

import Communication.Comms;

/**
 * Created by mark on 15/05/15.
 */
public interface Machine {
    public DataStructure getDataStructure(DataType dataType);
    public Comms getComms();
    public void exit();

    void setUser(User user);

}
