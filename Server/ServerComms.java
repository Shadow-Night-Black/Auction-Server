package Server;

import Client.Client;
import Communication.Comms;
import Communication.MessageThreadListener;
import Communication.Messages.DataMessage;
import Infomation.Data;
import Infomation.DataStructureListener;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mark on 12/05/15.
 */
public class ServerComms extends Comms implements DataStructureListener{
    private List<ClientHandler> clientHandlerList;

    public ServerComms() {
        super();
        clientHandlerList = new ArrayList<>();
    }

    public void connect() {
        try {
        ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
            while (true) try {
            socket = serverSocket.accept();
            ClientHandler clientHandler = new ClientHandler(socket.getOutputStream(), new MessageThreadListener(socket));
            clientHandlerList.add(clientHandler);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void disconnect() {
        for (ClientHandler clientHandler: clientHandlerList) {
            clientHandler.disconnect();
        }
    }

    @Override
    public void itemAdded(Data item) {
        for (ClientHandler clientHandler: clientHandlerList) {
            clientHandler.sendMessage(new DataMessage(item));
        }
    }

    @Override
    public void itemChanged(Data original, Data changed) {
        for (ClientHandler clientHandler: clientHandlerList) {
            clientHandler.sendMessage(new DataMessage(changed));
        }
    }
}
