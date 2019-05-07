package Communication.Messages;

import Exceptions.InvalidIDException;
import Infomation.DataType;
import Infomation.Data;
import Infomation.Machine;

/**
 * Created by mark on 12/05/15.
 */
public class RequestMessage implements Message {
    private DataType request;
    private int id;

    public RequestMessage(DataType request, int id) {
        this.request = request;
        this.id = id;
    }

    public Message performAction(Machine instance) {
        try {
            Data identifiable = instance.getDataStructure(getDataType()).getEntry(id);
            if (identifiable != null) {
                return new DataMessage(identifiable);
            }
        } catch (InvalidIDException e) {
            return new RequestFailedMessage(this);
        }
        return null;
    }

    @Override
    public MessageType type() {
        return MessageType.Request;
    }

    @Override
    public DataType getDataType() {
        return request;
    }


}
