package Infomation;

import Exceptions.InvalidBidException;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

public class Auction extends Data implements Iterable<Bid>, Serializable {
    private Item item;
    private User seller;
    private int auctionID;
    private Calendar startTime, endTime;
    private BigDecimal reserve;
    private List<Bid> bids;
    private Set<User> bidders;

    public Auction(Item item, User seller, Calendar startTime, Calendar endTime, BigDecimal reserve) {
        this.item = item;
        this.seller = seller;
        this.reserve = reserve;
        this.bids = new ArrayList<Bid>();
        setTime(startTime, endTime);
        this.bidders = new HashSet<>();
    }

    private void setTime(Calendar startTime, Calendar endTime) {
        if (startTime.before(endTime)) {
            this.startTime = startTime;
            this.endTime = endTime;
        } else {
            this.startTime = endTime;
            this.endTime = startTime;
        }
    }

    public void bid(Bid bid) throws InvalidBidException {
        if (bid.getAmount().compareTo(getHighestBid().getAmount()) > 0) {
            bids.add(bid);
            bidders.add(bid.getBidder());
        } else {
            throw new InvalidBidException(bid, "Highest bid on " + getItem().getTitle() + " is " + getHighestBid());
        }
    }

    public Bid getHighestBid() {
        if (bids.size() == 0)
            return new Bid(null, new BigDecimal(0), this);
        else
            return bids.get(bids.size() - 1);
    }

    public String toString() {
        return "Item: " + item +
                "\n  Seller:  " + seller.getFirstName() +
                "\n  EndTime:  " + endTime.getTime();
    }

    public Item getItem() {
        return item;
    }

    public User getSeller() {
        return seller;
    }

    public int getId() {
        return auctionID;
    }

    @Override
    public void setId(int id) {
        this.auctionID = id;
    }

    @Override
    public boolean isData(Data i) {
        if (i instanceof Auction) {
            Auction auction = (Auction) i;
            return auction.getItem().equals(this.item) && auction.getSeller().equals(seller);
        }
        return false;
    }

    @Override
    public DataType getDataType() {
        return DataType.Auction;
    }

    @Override
    public boolean matches(Data i) {
        if (i == null) {
            return true;
        } else if (i instanceof Auction) {
            Auction auction = (Auction) i;
            if (auction.getItem() == item ||
                    auction.getSeller().equals(seller) ||
                    auction.getId() == auctionID ||
                    auction.getStartTime().before(startTime) ||
                    auction.getEndTime().after(endTime) ||
                    auction.getReserve().compareTo(reserve) > 1) {
                return true;
            }
        }
        return false;
    }


    public Iterator<Bid> iterator() {
        return bids.iterator();
    }

    public BigDecimal getReserve() {
        return reserve;
    }

    public Calendar getStartTime() {
        return startTime;
    }

    public Calendar getEndTime() {
        return endTime;
    }

    public Set<User> getBidders() {
        return bidders;
    }
}