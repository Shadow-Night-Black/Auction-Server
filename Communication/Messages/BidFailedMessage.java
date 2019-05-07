package Communication.Messages;

import Infomation.DataType;
import Infomation.Machine;

import javax.swing.*;

/**
 * Created by mark on 17/05/15.
 */
public class BidFailedMessage implements Message {
    private String message;

    public BidFailedMessage(String message) {
        this.message = message;
    }

    @Override
    public Message performAction(Machine instance) {
        JOptionPane.showMessageDialog(new JFrame("Bid Failed!"), message);
        return null;
    }

    @Override
    public MessageType type() {
        return MessageType.BidFailed;
    }

    @Override
    public DataType getDataType() {
        return null;
    }
}
