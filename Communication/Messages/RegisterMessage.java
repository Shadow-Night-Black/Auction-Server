package Communication.Messages;

import Exceptions.DuplicateDataException;
import Exceptions.DuplicateIDException;
import Infomation.DataType;
import Infomation.Machine;
import Infomation.User;
import Server.ClientHandler;

/**
 * Created by mark on 15/05/15.
 */
public class RegisterMessage implements Message{
    private User user;

    public RegisterMessage(User user) {
        this.user = user;
    }
    @Override
    public Message performAction(Machine instance) {
        ClientHandler server = (ClientHandler) instance;
        boolean done = false;
        try {
            while (!done) {
                user.setId(server.getDataStructure(DataType.User).getNextId());

                try {
                    done = server.getDataStructure(DataType.User).addEntry(user);
                } catch (DuplicateIDException e) {
                    e.printStackTrace();
                }
            }
        } catch (DuplicateDataException e) {
            e.printStackTrace();
            return new LoginFailedMessage("Email address is already in use!");
        }
        return new LoginSuccessful(user);
    }

    @Override
    public MessageType type() {
        return MessageType.Register;
    }

    public User getUser() {
        return user;
    }

    @Override
    public DataType getDataType() {
        return DataType.User;
    }
}
