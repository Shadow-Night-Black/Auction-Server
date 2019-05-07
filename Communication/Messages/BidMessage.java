package Communication.Messages;

import Exceptions.InvalidBidException;
import Exceptions.InvalidIDException;
import Infomation.Auction;
import Infomation.Bid;
import Infomation.DataType;
import Infomation.Machine;

/**
 * Created by mark on 15/05/15.
 */
public class BidMessage implements Message {
    private Bid bid;

    public BidMessage(Bid bid) {
        this.bid = bid;
    }

    @Override
    public Message performAction(Machine instance) {
        try {
        Auction auction = (Auction) instance.getDataStructure(DataType.Auction).getEntry(bid.getAuction().getId());
            auction.bid(bid);
        } catch (InvalidBidException e) {
            return new BidFailedMessage(e.getMessage());
        } catch (InvalidIDException e) {
            e.printStackTrace();
            return new RequestFailedMessage(this);
        }
        return new RequestCompleted(this);
    }

    @Override
    public MessageType type() {
        return MessageType.Bid;
    }

    @Override
    public DataType getDataType() {
        return DataType.Auction;
    }

    public Bid getBid() {
        return bid;
    }
}
