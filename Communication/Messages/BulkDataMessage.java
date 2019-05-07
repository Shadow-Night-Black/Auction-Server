package Communication.Messages;

import Exceptions.DuplicateDataException;
import Exceptions.DuplicateIDException;
import Infomation.Data;
import Infomation.DataType;
import Infomation.Machine;

import java.util.List;

/**
 * Created by mark on 15/05/15.
 */
public class BulkDataMessage implements Message{
    private List<Data> dataList;

    public BulkDataMessage(List<Data> dataList) {
        this.dataList = dataList;
    }



    @Override
    public Message performAction(Machine instance) {
        for (Data data: dataList) {
            try {
                instance.getDataStructure(data.getDataType()).addEntry(data);
            } catch (DuplicateIDException e) {
                e.printStackTrace();
            } catch (DuplicateDataException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public MessageType type() {
        return MessageType.BulkDataMessage;
    }

    @Override
    public DataType getDataType() {
        return dataList.get(0).getDataType();
    }
}
