package Communication.Messages;

import Infomation.DataType;
import Infomation.Machine;

import javax.swing.*;

/**
 * Created by mark on 15/05/15.
 */
public class RequestFailedMessage implements Message {
    private Message message;

    public RequestFailedMessage(Message message) {
        this.message = message;
    }

    public Message getMessage() {
        return message;
    }

    @Override
    public Message performAction(Machine instance) {
        return null;
    }

    @Override
    public MessageType type() {
        return MessageType.RequestFailed;
    }

    @Override
    public DataType getDataType() {
        return null;
    }
}
