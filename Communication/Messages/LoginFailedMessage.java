package Communication.Messages;

import Client.Gui.LoginDialogBox;
import Communication.Messages.Message;
import Communication.Messages.MessageType;
import Infomation.DataType;
import Infomation.Machine;

import javax.swing.*;

/**
 * Created by mark on 17/05/15.
 */
public class LoginFailedMessage implements Message {
    private String message;
    public LoginFailedMessage(String s) {
        this.message = s;
    }

    @Override
    public Message performAction(Machine instance) {
        JOptionPane.showMessageDialog(new JFrame("Login Failed!"), message);
        LoginDialogBox.showLoginRequest(instance);
        return null;
    }

    @Override
    public MessageType type() {
        return MessageType.LoginFailed;
    }

    @Override
    public DataType getDataType() {
        return null;
    }
}
