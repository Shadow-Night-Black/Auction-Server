package Communication.Messages;

import Infomation.DataType;
import Infomation.Machine;

/**
 * Created by mark on 13/05/15.
 */
public class ExitMessage implements Message {

    @Override
    public Message performAction(Machine instance) {
        instance.exit();
        return null;
    }

    @Override
    public MessageType type() {
        return MessageType.Exit;
    }

    @Override
    public DataType getDataType() {
        throw new UnsupportedOperationException();
    }
}
