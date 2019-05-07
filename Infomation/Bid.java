package Infomation;

import java.io.Serializable;
import java.math.BigDecimal;

public class Bid implements Serializable{

    private User bidder;
    private Auction auction;
    private BigDecimal amount;

    public Bid(User bidder, BigDecimal amount, Auction auction) {
        this.bidder = bidder;
        this.amount = amount;
        this.auction = auction;
    }

    public User getBidder() {
        return bidder;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Auction getAuction() {return auction;}

    public String toString() {
        return "Bidder: " + bidder.getFirstName() +
                "\n   Amount:  " + amount;

    }
}
