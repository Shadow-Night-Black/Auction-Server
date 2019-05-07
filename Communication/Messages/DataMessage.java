package Communication.Messages;

import Exceptions.DuplicateDataException;
import Infomation.DataType;
import Infomation.Data;
import Infomation.Machine;
import Exceptions.DuplicateIDException;

/**
 * Created by mark on 13/05/15.
 */
public class DataMessage implements Message {
    private Data item;

    public DataMessage(Data item) {
        this.item = item;
    }

    public Data getObject() {
        return item;
    }

    public DataType getDataType() {
        return item.getDataType();
    }

    @Override
    public Message performAction(Machine instance) {
        try {
            instance.getDataStructure(item.getDataType()).addEntry(item);
        }catch (DuplicateIDException e) {
            return new RequestFailedMessage(this);
        } catch (DuplicateDataException e) {
            return new RequestFailedMessage(this);
        }
        return new RequestCompleted(this);

    }

    @Override
    public MessageType type() {
        return MessageType.Data;
    }
}
