package Infomation;

import java.io.Serializable;

/**
 * Created by mark on 22/03/15.
 */
public abstract class Data implements Serializable {
    public long serialVersionUID = 345;
    public abstract int getId();
    public abstract void setId(int id);
    public abstract boolean isData(Data i);
    public abstract DataType getDataType();

    public abstract boolean matches(Data i);
    public boolean equals(Object o) {
        if (o instanceof Data) {
            return isData((Data)o);
        }
        return false;
    }
}
