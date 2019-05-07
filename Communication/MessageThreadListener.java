package Communication;

import Communication.Messages.Message;
import Exceptions.NotConnectedException;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
* Created by mark on 12/05/15.
*/
public class MessageThreadListener extends Thread {
    private ObjectInputStream in;
    private List<MessageListener> messageListeners;
    private Socket socket;

    public MessageThreadListener(Socket socket) {
            messageListeners = Collections.synchronizedList(new ArrayList<MessageListener>());
            this.socket = socket;
            this.start();
    }

    public void run() {
        try {
            in = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true) {
            Message message = receiveMessage();
            System.out.println("Received Message type:" + message.type());
            for (int i = 0; i < messageListeners.size(); i++) {
                messageListeners.get(i).receivedMessage(message);
            }
        }
    }

    public Message receiveMessage() {
        try {
            return (Message) in.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        throw new NotConnectedException();
    }

    public void addMessageListener(MessageListener messageListener) {
        messageListeners.add(messageListener);
    }

    public void removeMessageListener(MessageListener messageListener) {
        messageListeners.remove(messageListener);
    }

}
