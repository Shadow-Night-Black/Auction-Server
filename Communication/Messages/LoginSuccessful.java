package Communication.Messages;

import Infomation.DataType;
import Infomation.Machine;
import Infomation.User;

/**
 * Created by mark on 17/05/15.
 */
public class LoginSuccessful implements Message {
    private User user;

    public LoginSuccessful(User user) {
        this.user = user;
    }

    @Override
    public Message performAction(Machine instance) {
        instance.setUser(user);
        return null;
    }

    @Override
    public MessageType type() {
        return MessageType.LoginSuccessful;
    }

    @Override
    public DataType getDataType() {
        return DataType.User;
    }
}
