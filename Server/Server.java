package Server;

import Exceptions.DuplicateDataException;
import Exceptions.DuplicateIDException;
import Infomation.*;
import Infomation.Data;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class Server implements Iterable<DataBase<Data>>{
    private static final String USER_FILE = "SaveData/users.db";
    private static final String AUCTION_FILE = "SaveData/auctions.db";
    private static final String ITEM_FILE = "SaveData/items.db";
    private static Server server;
    private Map<DataType, DataBase<Data>> databases;
    private ServerComms comms;

    public static void main(String[] args) {
        new Server();
    }

    private Server() {
        server = this;
        databases = new HashMap<>();
        databases.put(DataType.User, DataBase.loadData(new File(USER_FILE)));
        databases.put(DataType.Auction, DataBase.loadData(new File(AUCTION_FILE)));
        databases.put(DataType.Item, DataBase.loadData(new File(ITEM_FILE)));

        databases.get(DataType.Auction).addDataStructureListener(new DataStructureListener() {
            @Override
            public void itemAdded(Data item) {
                Auction auction = (Auction) item;
                try {
                    databases.get(DataType.Item).addEntry(auction.getItem());
                } catch (DuplicateIDException e) {
                    e.printStackTrace();
                } catch (DuplicateDataException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void itemChanged(Data original, Data changed) {
            }
        });

        comms = new ServerComms();

        for(DataBase dataBase: this) {
            dataBase.addDataStructureListener(comms);
        }
        run();

    }

    private void run() {
        comms.connect();
    }

    public static Server getServerInstance() {
        if (server == null)
            server = new Server();
        return server;
    }

    public DataBase<Data> getDataStructure(DataType dataType) {
        return databases.get(dataType);
    }

    public ServerComms getComms() {
        return comms;
    }

    public void exit() {
        comms.disconnect();
        for (DataBase dataBase: this) {
            dataBase.saveData();
        }
        System.exit(0);
    }

    @Override
    public Iterator<DataBase<Data>> iterator() {
        return databases.values().iterator();
    }
}

