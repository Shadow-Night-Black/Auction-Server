package Communication.Messages;

import Infomation.DataType;
import Infomation.Login;
import Infomation.Machine;

/**
 * Created by mark on 15/05/15.
 */
public class LoginMessage implements Message {
    private Login login;

    public LoginMessage(Login login) {
        this.login = login;
    }

    @Override
    public Message performAction(Machine instance) {
        throw new UnsupportedOperationException();
    }

    @Override
    public MessageType type() {
        return MessageType.Login;
    }

    @Override
    public DataType getDataType() {
        return DataType.User;
    }

    public Login getLogin() {
        return login;
    }
}
