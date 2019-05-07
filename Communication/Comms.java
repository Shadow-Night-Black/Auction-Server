package Communication;

import Communication.Messages.ExitMessage;
import Communication.Messages.Message;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by mark on 12/05/15.
 */
public abstract class Comms {
    protected static final int SERVER_PORT = 6666;
    protected Socket socket;
    protected ObjectOutputStream out;
    protected boolean connected = false;


    public void sendMessage(Message message) {
        if (message != null) {
            try {
                System.out.println("Sending Message Type: " + message.type());
                out.writeObject(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void disconnect() {
        try {
            out.writeObject(new ExitMessage());
            out.flush();
            out.close();
            out = null;
            socket.close();
            socket = null;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            connected = false;
        }

    }
}
