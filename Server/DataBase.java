package Server;

import Exceptions.DuplicateDataException;
import Infomation.DataStructure;
import Infomation.Data;
import Exceptions.DuplicateIDException;
import Exceptions.InvalidIDException;
import Infomation.DataStructureListener;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by mark on 12/05/15.
 */
public class DataBase <I extends Data> implements DataStructure<I>, Iterable<I>, Serializable{

    private HashMap<Integer, I> entries;
    private int nextId;
    private File file;
    private transient List<DataStructureListener> dataStructureListeners;

    public DataBase(File file) {
        this.file = file;
        nextId = 1;
        entries = new HashMap<>();
        dataStructureListeners = new ArrayList<>();

        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            private DataBase dataBase = DataBase.this;
            public void run() {
                dataBase.saveData();
            }
        }));
    }

    public boolean addEntry(I i) throws DuplicateIDException, DuplicateDataException {
        if (getData(i) != null) {
            throw new DuplicateDataException("Data Already exists!", i);
        } else {
            i.setId(getNextId());
            entries.put(i.getId(), i);
            itemAdded(i);
            saveData();
            return true;
        }
    }

    public I getData(Data data) {
        for (I data2: this) {
            if (data.isData(data2)) {
                return data2;
            }
        }
        return null;
    }

    @Override
    public List<I> search(I matcher) {
        List<I> matches = new ArrayList<>();
        for (I item: this) {
            if (item.matches(matcher))
                matches.add(item);
        }
        return matches;
    }

    @Override
    public void addDataStructureListener(DataStructureListener dataStructureListener) {
        dataStructureListeners.add(dataStructureListener);
    }

    private void itemAdded(I i) {
        for (DataStructureListener dataStructureListener : dataStructureListeners) {
            dataStructureListener.itemAdded(i);
        }
    }

    public I getEntry(int id) throws InvalidIDException{
        if (entries.containsKey(id))
            return entries.get(id);
        else
            throw new InvalidIDException("ID: " + id + " does not exist!", id);
    }

    public File getFile() {
        return file;
    }

    public Iterator<I> iterator() {
        return entries.values().iterator();
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (I entry: this) {
            stringBuilder.append(entry);
        }
        return stringBuilder.toString();
    }

    public static <I extends Data & Serializable> DataBase<I> loadData(File file) {
        DataBase<I> dataBase = IO.loadData(file);
        if (dataBase == null) {
            System.out.println("Could not load Database!");
            dataBase = new DataBase<I>(file);
        }
        return dataBase;
    }

    public <I extends Data> void saveData() {
        IO.saveData(this.getFile(), this);
    }

    public int getNextId() {
        while (entries.containsKey(nextId)) {
            nextId++;
        }
        return nextId;
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        dataStructureListeners = new ArrayList<>();
    }
}
