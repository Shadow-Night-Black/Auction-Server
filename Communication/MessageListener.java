package Communication;

import Communication.Messages.Message;

/**
 * Created by mark on 12/05/15.
 */
public interface MessageListener {
    public void receivedMessage(Message message);

}
