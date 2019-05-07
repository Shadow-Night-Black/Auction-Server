package Client;

import Client.Gui.ClientGui;
import Client.Gui.LoginDialogBox;
import Communication.*;
import Communication.Messages.*;
import Infomation.*;

import java.net.UnknownHostException;
import java.util.*;

/**
 * Created by mark on 13/05/15.
 */
public class Client implements Machine, MessageListener {
    private ClientComms comms;
    private Map<DataType, Cache<Data>> caches;
    private ClientGui clientGui;
    private User user;

    public static void main(String args[]) {
        new Client();
    }

    public Client() {
        caches = new HashMap<>();
        try {
            comms = new ClientComms();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        comms.addMessageListener(this);
        LoginDialogBox.showLoginRequest(this);

    }

    public void receivedMessage(Message message) {
        switch (message.type()) {
            case RequestCompleted:
                if (clientGui == null) {
                }
                message.performAction(this);
                break;
            default:
                Message returnMessage = message.performAction(this);
                if (returnMessage != null) {
                    comms.sendMessage(returnMessage);
                }
        }
    }

    public Cache<Data> getDataStructure(DataType dataType) {
        if (!caches.containsKey(dataType)) {
            caches.put(dataType, new Cache<Data>(comms, dataType));
        }
        return caches.get(dataType);
    }

    public void refreshCaches() {
        for (DataType dataType : DataType.values()) {
            getDataStructure(dataType).refresh();
        }
    }

    public void addDataStructureListeners(DataStructureListener dataStructureListener) {
        for (DataType dataType : DataType.values()) {
            getDataStructure(dataType).addDataStructureListener(dataStructureListener);
        }
    }

    @Override
    public Comms getComms() {
        return comms;
    }

    @Override
    public void exit() {
        comms.disconnect();
        System.exit(0);
    }

    @Override
    public void setUser(User user) {
        this.user = user;
        clientGui = new ClientGui(this);
    }

    public User getUser() {
        return user;
    }
}
