package Infomation;

import Exceptions.DuplicateDataException;
import Exceptions.DuplicateIDException;
import Exceptions.InvalidIDException;

import java.util.List;

/**
 * Created by mark on 15/05/15.
 */
public interface DataStructure<I extends Data> {
    public boolean addEntry(I i) throws DuplicateIDException, DuplicateDataException;

    public I getEntry(int id) throws InvalidIDException;

    public I getData(Data d);

    public List<I> search(I matcher);

    public void addDataStructureListener(DataStructureListener dataStructureListener);

}
