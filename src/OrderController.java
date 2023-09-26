import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.*;

// public class OrderController implements ActionListener {
//     private OrderView view;
//     private Order order = null;

//     public OrderController(OrderView view) {
//         this.view = view;

//         view.getBtnAdd().addActionListener(this);
//         view.getBtnPay().addActionListener(this);

//         order = new Order();

//     }


//     public void actionPerformed(ActionEvent e) {
//         if (e.getSource() == view.getBtnAdd())
//             addProduct();
//         else
//         if (e.getSource() == view.getBtnPay())
//             makeOrder();
//     }

//     private void makeOrder() {
//         JOptionPane.showMessageDialog(null, "This function is being implemented!");

//         /* Remember to update new quantity of products!
//         product.setQuantity(product.getQuantity() - quantity); // update new quantity!!
//         dataAdapter.saveProduct(product); // and save this product back 
//         */

//     }

//     private void addProduct() {
//         String id = JOptionPane.showInputDialog("Enter ProductID: ");
//         Product product = Application.getInstance().getDataAdapter().loadProduct(Integer.parseInt(id));
//         if (product == null) {
//             JOptionPane.showMessageDialog(null, "This product does not exist!");
//             return;
//         }

//         double quantity = Double.parseDouble(JOptionPane.showInputDialog(null,"Enter quantity: "));

//         if (quantity < 0 || quantity > product.getQuantity()) {
//             JOptionPane.showMessageDialog(null, "This quantity is not valid!");
//             return;
//         }

//         OrderLine line = new OrderLine();
//         line.setOrderID(this.order.getOrderID());
//         line.setProductID(product.getProductID());
//         line.setQuantity(quantity);
//         line.setCost(quantity * product.getPrice());
//         order.getLines().add(line);
//         order.setTotalCost(order.getTotalCost() + line.getCost());



//         Object[] row = new Object[5];
//         row[0] = line.getProductID();
//         row[1] = product.getName();
//         row[2] = product.getPrice();
//         row[3] = line.getQuantity();
//         row[4] = line.getCost();

//         this.view.addRow(row);
//         this.view.getLabTotal().setText("Total: $" + order.getTotalCost());
//         this.view.invalidate();
//     }

// }


public class OrderController extends JFrame implements ActionListener {
    // private OrderView view;
    private Order order = null;

    private Address shippingAddress = null;
    private CreditCard payment = null;

    private JButton btnAdd = new JButton("Add a new item");
    private JButton btnPay = new JButton("Finish and pay");
    private JButton btnAddress = new JButton("Add Address");
    private JButton btnCard = new JButton("Add Credid Card");

    private JLabel labAddress = new JLabel();
    private JLabel labCard = new JLabel();

    private DefaultTableModel items = new DefaultTableModel(); // store information for the table!

    private JTable tblItems = new JTable(items);
    private JLabel labTotal = new JLabel("Total: ");

    public void setAddress(Address address) {
        this.shippingAddress = address;
    }

    public void setPayment (CreditCard card) {
        this.payment = card;
    }

    public String getPayment () {
        return this.payment.getCardNumber();
    }

    public JButton getBtnAdd() {
        return btnAdd;
    }

    public JButton getBtnPay() {
        return btnPay;
    }

    public JButton getBtnAddress() {
        return btnAddress;
    }

    public JButton getBtnCard() {
        return btnCard;
    }

    public JLabel getLabTotal() {
        return labTotal;
    }

    public JLabel getLabAddress() {
        return labAddress;
    }

    public JLabel getLabCard() {
        return labCard;
    }

    public void addRow(Object[] row) {
        items.addRow(row);
    }

    public OrderController() {
        
        this.setTitle("Order View");
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        this.setSize(400, 600);


        items.addColumn("Product ID");
        items.addColumn("Name");
        items.addColumn("Price");
        items.addColumn("Quantity");
        items.addColumn("Cost");

        JPanel panelOrder = new JPanel();
        panelOrder.setPreferredSize(new Dimension(400, 450));
        panelOrder.setLayout(new BoxLayout(panelOrder, BoxLayout.PAGE_AXIS));
        tblItems.setBounds(0, 0, 400, 350);
        panelOrder.add(tblItems.getTableHeader());
        panelOrder.add(tblItems);
        panelOrder.add(labTotal);
        panelOrder.add(labAddress);
        panelOrder.add(labCard);
        tblItems.setFillsViewportHeight(true);
        this.getContentPane().add(panelOrder);

        JPanel panelButton = new JPanel();
        panelButton.setPreferredSize(new Dimension(400, 100));
        panelButton.add(btnAdd);
        panelButton.add(btnPay);
        panelButton.add(btnAddress);
        panelButton.add(btnCard);
        this.getContentPane().add(panelButton);


        this.getBtnAdd().addActionListener(this);
        this.getBtnPay().addActionListener(this);
        this.getBtnAddress().addActionListener(this);
        this.getBtnCard().addActionListener(this);

        order = new Order();

    }

    public String generateReceipt(Order order) {
        String content = "Order ID: " + order.getOrderID() + "\n";
        content += "Buyer ID: " + order.getBuyerID() + "\n";
        content += "Date: " + order.getDate() + "\n";
        content += "Address: " + order.getAddressID() + "\n";
        content += "Card: " + this.getPayment().substring(this.getPayment().length() - 4) + "\n";
        content += "Total: " + order.getTotalCost() + "\n";
        content += "Tax: " + order.getTotalTax() + "\n";
        content += "Items: \n";
        content += "Product ID\tQuantity\tCost\n";
        for (OrderLine line : order.getLines()) {
            content += line.getProductID() + "\t" + line.getQuantity() + "\t" + line.getCost() + "\n";
        }
        
        return content;
    }


    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.getBtnAdd())
            addProduct();
        else
        if (e.getSource() == this.getBtnPay())
            makeOrder();
        else
        if (e.getSource() == this.getBtnAddress())
            addAddress();
        else
        if (e.getSource() == this.getBtnCard())
            addCard();
    }

    private void makeOrder() {

        if (this.shippingAddress == null) {
            JOptionPane.showMessageDialog(null, "Please add an address!");
            return;
        }

        if (this.payment == null) {
            JOptionPane.showMessageDialog(null, "Please add a credit card!");
            return;
        }

        order.setOrderID(Application.getInstance().getDataAdapter().getTotalOrders() + 1); 

        for (OrderLine line : order.getLines()) {
            Product product = Application.getInstance().getDataAdapter().loadProduct(line.getProductID());
            product.setQuantity(product.getQuantity() - line.getQuantity());
            Application.getInstance().getDataAdapter().saveProduct(product);
        }

        order.setAddressID(this.shippingAddress.getAddressID());
        order.setCardID(this.payment.getCardID());
        order.setDate(new java.util.Date().toString());
        order.setTotalTax(order.getTotalCost() * 0.0825);
        order.setBuyerID(Application.getInstance().getCurrentUser().getUserID());



        Application.getInstance().getDataAdapter().saveOrder(order);
        String receipt = generateReceipt(order);
        Application.getInstance().getDataAdapter().saveReceipt(order.getOrderID(), receipt);
        JOptionPane.showMessageDialog(null, receipt);
        /* Remember to update new quantity of products!
        product.setQuantity(product.getQuantity() - quantity); // update new quantity!!
        dataAdapter.saveProduct(product); // and save this product back 
        */

    } 

    private void addProduct() {
        String id = JOptionPane.showInputDialog("Enter ProductID: ");
        Product product = Application.getInstance().getDataAdapter().loadProduct(Integer.parseInt(id));
        if (product == null) {
            JOptionPane.showMessageDialog(null, "This product does not exist!");
            return;
        }

        double quantity = Double.parseDouble(JOptionPane.showInputDialog(null,"Enter quantity: "));

        if (quantity < 0 || quantity > product.getQuantity()) {
            JOptionPane.showMessageDialog(null, "This quantity is not valid!");
            return;
        }

        OrderLine line = new OrderLine();
        line.setOrderID(this.order.getOrderID());
        line.setProductID(product.getProductID());
        line.setQuantity(quantity);
        line.setCost(quantity * product.getPrice());
        order.getLines().add(line);
        order.setTotalCost(order.getTotalCost() + line.getCost());



        Object[] row = new Object[5];
        row[0] = line.getProductID();
        row[1] = product.getName();
        row[2] = product.getPrice();
        row[3] = line.getQuantity();
        row[4] = line.getCost();

        this.addRow(row);
        this.getLabTotal().setText("Total: $" + order.getTotalCost());
        this.invalidate();
    }

    private void addAddress() {

        int addressID = Application.getInstance().getDataAdapter().getTotalAddresses() + 1;

        String street = JOptionPane.showInputDialog("Enter Address Number and Street: ");
        String city = JOptionPane.showInputDialog("Enter City: ");
        String state = JOptionPane.showInputDialog("Enter State: ");
        String zipcode = JOptionPane.showInputDialog("Enter Zip Code: ");
        String country = JOptionPane.showInputDialog("Enter Country: ");

        Address address = new Address();

        address.setAddressID(addressID);
        address.setStreet(street);
        address.setCity(city);
        address.setState(state);
        address.setZipcode(zipcode);
        address.setCountry(country);

        // Order order = Application.getInstance().getDataAdapter().loadOrder(Integer.parseInt(address));
        Application.getInstance().getDataAdapter().saveAddress(address);
        this.setAddress(address);
        this.getLabAddress().setText("Address: " + address.getStreet() + ", " + address.getCity() + ", " + address.getState() + ", " + address.getZipcode() + ", " + address.getCountry());
    }

    private void addCard() {
            
            int cardID = Application.getInstance().getDataAdapter().getTotalCards() + 1;
    
            String holder = JOptionPane.showInputDialog("Enter Name on Card: ");
            String cardNumber = JOptionPane.showInputDialog("Enter Card Number: ");
            String expirationDate = JOptionPane.showInputDialog("Enter Expiration Date: ");
            String CVV = JOptionPane.showInputDialog("Enter CVV: ");
    
            CreditCard card = new CreditCard();
    
            card.setCardID(cardID);
            card.setCardNumber(cardNumber);
            card.setHolder(holder);
            card.setExpirationDate(expirationDate);
            card.setCVV(CVV);
    
            // Order order = Application.getInstance().getDataAdapter().loadOrder(Integer.parseInt(address));
            Application.getInstance().getDataAdapter().saveCard(card);
            this.setPayment(card);
            this.getLabCard().setText("Payment: " + card.getHolder() + ", " + card.getCardNumber().substring(card.getCardNumber().length() - 4));
    }

}