import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// public class ProductController implements ActionListener {
//     private ProductView productView;

//     public ProductController(ProductView productView) {
//         this.productView = productView;

//         productView.getBtnLoad().addActionListener(this);
//         productView.getBtnSave().addActionListener(this);
//     }


//     public void actionPerformed(ActionEvent e) {
//         if (e.getSource() == productView.getBtnLoad())
//             loadProduct();
//         else
//         if (e.getSource() == productView.getBtnSave())
//             saveProduct();
//     }

//     private void saveProduct() {
//         int productID;
//         try {
//             productID = Integer.parseInt(productView.getTxtProductID().getText());
//         }
//         catch (NumberFormatException e) {
//             JOptionPane.showMessageDialog(null, "Invalid product ID! Please provide a valid product ID!");
//             return;
//         }

//         double productPrice;
//         try {
//             productPrice = Double.parseDouble(productView.getTxtProductPrice().getText());
//         }
//         catch (NumberFormatException e) {
//             JOptionPane.showMessageDialog(null, "Invalid product price! Please provide a valid product price!");
//             return;
//         }

//         double productQuantity;
//         try {
//             productQuantity = Double.parseDouble(productView.getTxtProductQuantity().getText());
//         }
//         catch (NumberFormatException e) {
//             JOptionPane.showMessageDialog(null, "Invalid product quantity! Please provide a valid product quantity!");
//             return;
//         }

//         String productName = productView.getTxtProductName().getText().trim();

//         if (productName.length() == 0) {
//             JOptionPane.showMessageDialog(null, "Invalid product name! Please provide a non-empty product name!");
//             return;
//         }

//         // Done all validations! Make an object for this product!

//         Product product = new Product();
//         product.setProductID(productID);
//         product.setSellerID(Application.getInstance().getCurrentUser().getUserID());
//         product.setName(productName);
//         product.setPrice(productPrice);
//         product.setQuantity(productQuantity);

//         // Store the product to the database

//         Application.getInstance().getDataAdapter().saveProduct(product);
//     }

//     private void loadProduct() {
//         int productID = 0;
//         try {
//             productID = Integer.parseInt(productView.getTxtProductID().getText());
//         }
//         catch (NumberFormatException e) {
//             JOptionPane.showMessageDialog(null, "Invalid product ID! Please provide a valid product ID!");
//             return;
//         }

//         Product product = Application.getInstance().getDataAdapter().loadProduct(productID);

//         if (product == null) {
//             JOptionPane.showMessageDialog(null, "This product ID does not exist in the database!");
//             return;
//         }

//         productView.getTxtProductName().setText(product.getName());
//         productView.getTxtProductPrice().setText(String.valueOf(product.getPrice()));
//         productView.getTxtProductQuantity().setText(String.valueOf(product.getQuantity()));
//     }


// }

public class ProductController extends JFrame implements ActionListener {
    // private ProductView productView;

    private JTextField txtProductID  = new JTextField(10);
    private JTextField txtProductName  = new JTextField(30);
    private JTextField txtProductPrice  = new JTextField(10);
    private JTextField txtProductQuantity  = new JTextField(10);

    private JButton btnLoad = new JButton("Load Product");
    private JButton btnSave = new JButton("Save Product");

    public JButton getBtnLoad() {
        return btnLoad;
    }

    public JButton getBtnSave() {
        return btnSave;
    }

    public JTextField getTxtProductID() {
        return txtProductID;
    }

    public JTextField getTxtProductName() {
        return txtProductName;
    }

    public JTextField getTxtProductPrice() {
        return txtProductPrice;
    }

    public JTextField getTxtProductQuantity() {
        return txtProductQuantity;
    }

    public ProductController() {
        // this.productView = productView;

        this.setTitle("Manage Products");
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
        this.setSize(500, 200);

        JPanel panelButton = new JPanel();
        panelButton.add(btnLoad);
        panelButton.add(btnSave);
        this.getContentPane().add(panelButton);

        JPanel panelProductID = new JPanel();
        panelProductID.add(new JLabel("Product ID: "));
        panelProductID.add(txtProductID);
        txtProductID.setHorizontalAlignment(JTextField.RIGHT);
        this.getContentPane().add(panelProductID);

        JPanel panelProductName = new JPanel();
        panelProductName.add(new JLabel("Product Name: "));
        panelProductName.add(txtProductName);
        this.getContentPane().add(panelProductName);

        JPanel panelProductInfo = new JPanel();
        panelProductInfo.add(new JLabel("Price: "));
        panelProductInfo.add(txtProductPrice);
        txtProductPrice.setHorizontalAlignment(JTextField.RIGHT);

        panelProductInfo.add(new JLabel("Quantity: "));
        panelProductInfo.add(txtProductQuantity);
        txtProductQuantity.setHorizontalAlignment(JTextField.RIGHT);

        this.getContentPane().add(panelProductInfo);


        this.getBtnLoad().addActionListener(this);
        this.getBtnSave().addActionListener(this);
    }


    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.getBtnLoad())
            loadProduct();
        else
        if (e.getSource() == this.getBtnSave())
            saveProduct();
    }

    private void saveProduct() {
        int productID;
        try {
            productID = Integer.parseInt(this.getTxtProductID().getText());
        }
        catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid product ID! Please provide a valid product ID!");
            return;
        }

        double productPrice;
        try {
            productPrice = Double.parseDouble(this.getTxtProductPrice().getText());
            if (productPrice < 0) {
                JOptionPane.showMessageDialog(null, "Product price cannot be negative!");
                return;
            }
        }
        catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid product price! Please provide a valid product price!");
            return;
        }

        double productQuantity;
        try {
            productQuantity = Double.parseDouble(this.getTxtProductQuantity().getText());
            if (productQuantity < 0) {
                JOptionPane.showMessageDialog(null, "Product quantity cannot be negative!");
                return;
            }
        }
        catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid product quantity! Please provide a valid product quantity!");
            return;
        }

        String productName = this.getTxtProductName().getText().trim();

        if (productName.length() == 0) {
            JOptionPane.showMessageDialog(null, "Invalid product name! Please provide a non-empty product name!");
            return;
        }

        // Done all validations! Make an object for this product!

        Product product = new Product();
        product.setProductID(productID);
        product.setSellerID(Application.getInstance().getCurrentUser().getUserID());
        product.setName(productName);
        product.setPrice(productPrice);
        product.setQuantity(productQuantity);

        // Store the product to the database

        Application.getInstance().getDataAdapter().saveProduct(product);
    }

    private void loadProduct() {
        int productID = 0;
        try {
            productID = Integer.parseInt(this.getTxtProductID().getText());
        }
        catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid product ID! Please provide a valid product ID!");
            return;
        }

        Product product = Application.getInstance().getDataAdapter().loadProduct(productID);

        if (product == null) {
            JOptionPane.showMessageDialog(null, "This product ID does not exist in the database!");
            return;
        }

        this.getTxtProductName().setText(product.getName());
        this.getTxtProductPrice().setText(String.valueOf(product.getPrice()));
        this.getTxtProductQuantity().setText(String.valueOf(product.getQuantity()));
    }


}