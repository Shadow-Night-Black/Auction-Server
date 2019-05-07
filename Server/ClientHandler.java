package Server;

import Communication.Comms;
import Communication.MessageListener;
import Communication.MessageThreadListener;
import Communication.Messages.*;
import Infomation.*;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

/**
 * Created by mark on 12/05/15.
 */
public class ClientHandler extends Comms implements MessageListener, Machine {
    private MessageThreadListener messageThreadListener;
    private ObjectOutputStream out;
    private User user = null;

    public ClientHandler(OutputStream s, MessageThreadListener messageThreadListener) {
        this.messageThreadListener = messageThreadListener;
        this.messageThreadListener.addMessageListener(this);
        try {
            out = new ObjectOutputStream(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void receivedMessage(Message message) {
        if (message.type() == MessageType.Login) {
            LoginMessage loginMessage = (LoginMessage) message;
            Login login = loginMessage.getLogin();
            User user = (User) getDataStructure(DataType.User).getData(login);
            if (user != null) {
                ClientHandler.this.user = user;
                sendMessage(new LoginSuccessful(user));
            } else {
                sendMessage(new LoginFailedMessage("Email/Password does not match!"));
            }
        }else if (message.type() == MessageType.Register) {
            Message returnMessage = message.performAction(this);
            authenticate(((RegisterMessage) message).getUser());
            sendMessage(returnMessage);
        } else if (!authenticated()) {
            sendMessage(new LoginRequestMessage());
        } else if (authenticated()) {
            try {
                Message returnMessage = message.performAction(this);
                sendMessage(returnMessage);
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean authenticated() {
        return user != null;
    }

    private void authenticate(User user) {
        if (user.isData(getDataStructure(DataType.User).getData(user))) {
            this.user = user;
        }
    }

    public void disconnect() {
        try {
            sendMessage(new ExitMessage());
            messageThreadListener.removeMessageListener(this);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(Message message) {
        try {
            if (message != null) {
                System.out.println("Sending Message Type: " + message.type());
                out.writeObject(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public DataBase getDataStructure(DataType dataType) {
        return Server.getServerInstance().getDataStructure(dataType);
    }

    @Override
    public Comms getComms() {
        return this;
    }

    @Override
    public void exit() {
        this.disconnect();
    }

    @Override
    public void setUser(User user) {
        authenticate(user);
    }

}


