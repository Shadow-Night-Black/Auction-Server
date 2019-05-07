package Client;

import Communication.Comms;
import Communication.Messages.RequestMessage;
import Communication.Messages.SearchMessage;
import Exceptions.InvalidDataTypeException;
import Infomation.Data;
import Exceptions.DuplicateIDException;
import Exceptions.InvalidIDException;
import Infomation.DataStructureListener;
import Infomation.DataType;
import Infomation.DataStructure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by mark on 15/05/15.
 */
public class Cache<I extends Data> implements DataStructure<I>, Iterable<I> {
    private DataType dataType;
    private HashMap<Integer, I> entries;
    private Comms comms;
    private List<DataStructureListener> dataStructureListeners;

    public Cache(Comms comms, DataType dataType) {
        this.comms = comms;
        this.dataType = dataType;
        entries = new HashMap<>();
        dataStructureListeners = new ArrayList<>();
    }

    @Override
    public boolean addEntry(I i) throws DuplicateIDException {
        if (this.dataType == i.getDataType()) {
            if (entries.containsKey(i.getId())) {
                itemChanged(entries.get(i.getId()), i);
            }else {
                itemAdded(i);
            }
            entries.put(i.getId(), i);
        } else {
            throw new InvalidDataTypeException("Wrong Cache!", i, dataType);
        }
        return true;
    }


    @Override
    public I getEntry(int id) throws InvalidIDException {
        if (entries.containsKey(id)) {
            return entries.get(id);
        } else {
            comms.sendMessage(new RequestMessage(dataType, id));
            throw new InvalidIDException("ID: " + id + " does not exist!", id);
        }
    }

    @Override
    public I getData(Data d) {
        for (I data : this) {
            if (data.isData(d)) {
                return data;
            }
        }
        return null;
    }

    @Override
    public List<I> search(I matcher) {
        return null;
    }

    @Override
    public Iterator<I> iterator() {
        return entries.values().iterator();
    }

    public void refresh() {
        comms.sendMessage(new SearchMessage(null, dataType));
    }

    private void itemAdded(I i) {
        for (DataStructureListener dataStructureListener : dataStructureListeners) {
            dataStructureListener.itemAdded(i);
        }
    }

    private void itemChanged(I original, I changed) {
        for (DataStructureListener dataStructureListener : dataStructureListeners) {
            dataStructureListener.itemChanged(original, changed);
        }
    }

    public DataType getDataType() {
        return dataType;
    }

    public void addDataStructureListener(DataStructureListener dataStructureListener) {
        dataStructureListeners.add(dataStructureListener);
    }
}
