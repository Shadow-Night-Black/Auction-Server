package Communication.Messages;

import Infomation.*;

import java.awt.*;
import java.io.Serializable;

public interface Message extends Serializable {

    public abstract Message performAction(Machine instance);

    public abstract MessageType type();

    public abstract DataType getDataType();
}
