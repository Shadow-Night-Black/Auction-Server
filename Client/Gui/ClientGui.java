package Client.Gui;

import Client.Client;
import Infomation.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mark on 15/05/15.
 */
public class ClientGui implements DataStructureListener {
    private JTabbedPane MainTabbedPanel;
    private JPanel panel1;
    private JPanel Auctions;
    private JPanel Users;
    private JPanel Items;
    private JList AuctionList;
    private JLabel Name;
    private JLabel Email;
    private JList UserList;
    private JList ItemList;
    private JTabbedPane Personal;
    private JList BidsList;
    private JList SellingList;
    private JPanel AuctionBids;
    private JPanel YourItems;
    private JPanel PersonalDetails;
    private JPanel Category;
    private JTree CategoryTree;
    private JButton refreshButton;
    private JButton createAuctionButton;

    private Client client;
    private Map<DataType, JList> listMap;

    public ClientGui(final Client client) {
        this.client = client;
        client.addDataStructureListeners(this);

        User user = client.getUser();
        Name.setText(user.getFirstName() + " " + user.getFamilyName());
        Email.setText(user.getEmail());

        listMap = new HashMap<>();
        listMap.put(DataType.Auction, AuctionList);
        listMap.put(DataType.User, UserList);
        listMap.put(DataType.Item, ItemList);

        for (DataType dataType : DataType.values()) {
            listMap.get(dataType).setModel(new DefaultListModel());
        }

        BidsList.setModel(new DefaultListModel());
        SellingList.setModel(new DefaultListModel());


        client.refreshCaches();


        JFrame frame = new JFrame("ClientGui");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        MouseAdapter auctionListener = new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                JList list = (JList) e.getSource();
                if (e.getClickCount() == 2 && list.getSelectedValue() != null) {
                    new AuctionView((Auction) list.getSelectedValue(), client);

                }
            }
        };

        AuctionList.addMouseListener(auctionListener);
        BidsList.addMouseListener(auctionListener);
        SellingList.addMouseListener(auctionListener);

        refreshButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                client.refreshCaches();
            }
        });

        createAuctionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                new AuctionCreater(client);
            }
        });
    }


    public void itemAdded(Data item) {
        System.out.println(item.getDataType() + " added!");
        addItemToList(listMap.get(item.getDataType()), item);

        if (item instanceof Auction) {
            Auction auction = (Auction) item;
            if (auction.getSeller().getId() == client.getUser().getId()) {
                addItemToList(SellingList, item);
            }
            if (auction.getBidders().contains(client.getUser())) {
                addItemToList(BidsList, item);
            }

        }
    }

    @Override
    public void itemChanged(Data original, Data changed) {
        System.out.println(original.getDataType() + " Changed!");
        changeItemInList(listMap.get(original.getDataType()), original, changed);

        if (changed instanceof Auction) {
            Auction auction = (Auction) original;
            if (auction.getSeller().getId() == client.getUser().getId()) {
                changeItemInList(SellingList, original, changed);
            }
            if (auction.getBidders().contains(client.getUser())) {
                changeItemInList(BidsList, original, changed);
            }

        }
    }

    private void addItemToList(JList list, Data data) {
        ((DefaultListModel) list.getModel()).addElement(data);
    }

    private void changeItemInList(JList list, Data original, Data changed) {
        DefaultListModel listModel = (DefaultListModel) list.getModel();
        listModel.removeElement(original);
        listModel.addElement(changed);
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panel1 = new JPanel();
        panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        MainTabbedPanel = new JTabbedPane();
        panel1.add(MainTabbedPanel, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(200, 200), null, 0, false));
        Auctions = new JPanel();
        Auctions.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        MainTabbedPanel.addTab("Auctions", Auctions);
        final JScrollPane scrollPane1 = new JScrollPane();
        Auctions.add(scrollPane1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        AuctionList = new JList();
        AuctionList.setSelectionMode(0);
        scrollPane1.setViewportView(AuctionList);
        Users = new JPanel();
        Users.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        MainTabbedPanel.addTab("Users", Users);
        final JScrollPane scrollPane2 = new JScrollPane();
        Users.add(scrollPane2, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        UserList = new JList();
        UserList.setSelectionMode(0);
        scrollPane2.setViewportView(UserList);
        Items = new JPanel();
        Items.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        MainTabbedPanel.addTab("Items", Items);
        final JScrollPane scrollPane3 = new JScrollPane();
        Items.add(scrollPane3, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        ItemList = new JList();
        ItemList.setSelectionMode(0);
        scrollPane3.setViewportView(ItemList);
        Category = new JPanel();
        Category.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        MainTabbedPanel.addTab("KeyWords", Category);
        final JScrollPane scrollPane4 = new JScrollPane();
        Category.add(scrollPane4, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        CategoryTree = new JTree();
        scrollPane4.setViewportView(CategoryTree);
        PersonalDetails = new JPanel();
        PersonalDetails.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(4, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(PersonalDetails, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        Name = new JLabel();
        Name.setText("Label");
        PersonalDetails.add(Name, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Email = new JLabel();
        Email.setText("Label");
        PersonalDetails.add(Email, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Personal = new JTabbedPane();
        PersonalDetails.add(Personal, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(200, 200), null, 0, false));
        AuctionBids = new JPanel();
        AuctionBids.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        Personal.addTab("Your Bids", AuctionBids);
        final JScrollPane scrollPane5 = new JScrollPane();
        AuctionBids.add(scrollPane5, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        BidsList = new JList();
        final DefaultListModel defaultListModel1 = new DefaultListModel();
        BidsList.setModel(defaultListModel1);
        BidsList.setSelectionMode(0);
        scrollPane5.setViewportView(BidsList);
        YourItems = new JPanel();
        YourItems.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        Personal.addTab("Your Auctions", YourItems);
        final JScrollPane scrollPane6 = new JScrollPane();
        YourItems.add(scrollPane6, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        SellingList = new JList();
        SellingList.setSelectionMode(0);
        scrollPane6.setViewportView(SellingList);
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        PersonalDetails.add(panel2, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        refreshButton = new JButton();
        refreshButton.setText("Refresh");
        panel2.add(refreshButton, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        createAuctionButton = new JButton();
        createAuctionButton.setText("Create Auction");
        panel2.add(createAuctionButton, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }
}
