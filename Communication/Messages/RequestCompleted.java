package Communication.Messages;

import Infomation.DataType;
import Infomation.Machine;

import javax.swing.*;

/**
 * Created by mark on 15/05/15.
 */
public class RequestCompleted implements Message {
    private Message message;

    public RequestCompleted(Message message) {
        this.message = message;
    }

    public Message getMessage() {
        return message;
    }

    @Override
    public Message performAction(Machine instance) {
        switch (message.type()) {
            case Login:
            case Register:
                JOptionPane.showMessageDialog(new JFrame("Login Successful!"), "Login Successful!");
                instance.setUser(((RegisterMessage)message).getUser());
                break;
            case Bid:
                BidMessage bm = (BidMessage) message;
                JOptionPane.showMessageDialog(new JFrame("Bid Successful!"), "Your bid on " + bm.getBid().getAuction().getItem().getTitle() +
                        "\n was Successful!");
                break;
        }

        return null;
    }

    @Override
    public MessageType type() {
        return MessageType.RequestCompleted;
    }

    @Override
    public DataType getDataType() {
        return null;
    }
}
