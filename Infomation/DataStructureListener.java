package Infomation;

import Infomation.Data;
import Infomation.DataStructure;

/**
 * Created by mark on 15/05/15.
 */
public interface DataStructureListener<I extends Data> {
    public void itemAdded(I item);
    public void itemChanged(I original, I changed);
}
