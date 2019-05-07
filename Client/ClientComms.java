package Client;

import Communication.Comms;
import Communication.MessageListener;
import Communication.MessageThreadListener;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientComms extends Comms {
    private static final String SERVER_IP = "localhost";
    private boolean conected = false;
    private MessageThreadListener messageListenerThread;

    public ClientComms() throws UnknownHostException {
        this(InetAddress.getByName(SERVER_IP), SERVER_PORT);
    }

    public ClientComms(InetAddress ip, int portNumber) {
        super();
        connect(ip, portNumber);
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            public void run() {
                disconnect();
            }
        }));
    }

    protected void connect(InetAddress ip, int portNumber) {
        try {
            socket = new Socket(ip, portNumber);
            out = new ObjectOutputStream(socket.getOutputStream());
            messageListenerThread = new MessageThreadListener(socket);
            connected = true;

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void disconnect() {
        messageListenerThread.interrupt();
        super.disconnect();
    }

    public void addMessageListener(MessageListener messageListener) {
        messageListenerThread.addMessageListener(messageListener);
    }


}
