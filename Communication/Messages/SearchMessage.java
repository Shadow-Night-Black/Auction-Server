package Communication.Messages;

import Infomation.Data;
import Infomation.DataType;
import Infomation.Machine;

/**
 * Created by mark on 15/05/15.
 */
public class SearchMessage implements Message{
    private Data matcher;
    private DataType datatype;

    public SearchMessage(Data matcher, DataType datatype) {
        this.matcher = matcher;
        this.datatype = datatype;

    }

    @Override
    public Message performAction(Machine instance) {
        return new BulkDataMessage(instance.getDataStructure(getDataType()).search(matcher));
    }

    @Override
    public MessageType type() {
        return MessageType.SearchMessage;
    }

    @Override
    public DataType getDataType() {
        return datatype;
    }
}
