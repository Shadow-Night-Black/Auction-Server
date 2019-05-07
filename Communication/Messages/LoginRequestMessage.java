package Communication.Messages;

import Client.Gui.LoginDialogBox;
import Infomation.DataType;
import Infomation.Machine;

/**
 * Created by mark on 14/05/15.
 */
public class LoginRequestMessage implements Message {
    @Override
    public Message performAction(Machine instance) {
        LoginDialogBox.showLoginRequest(instance);
        return null;
    }

    @Override
    public MessageType type() {
        return MessageType.LoginRequest;
    }

    @Override
    public DataType getDataType() {
        return null;
    }
}
