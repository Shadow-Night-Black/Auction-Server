package Exceptions;

import Infomation.Bid;

public class InvalidBidException extends Exception {
    private Bid bid;

    public InvalidBidException(Bid bid, String s) {
        super(s);
        this.bid = bid;
    }

    public Bid getBid() {
        return bid;
    }
}
