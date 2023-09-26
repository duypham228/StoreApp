Duy Pham

Submission 2: Coding2

UAT Testing (Login + Making Order): 
https://youtu.be/Zv93q_lz1DM

Code Change:
Database:
- Create tables for Receipts, Addresses, and CreditCards, foreign key AddressID and CreditCardID for Orders table

DataAdapter:
- Create function to save address and card, as well as load address and card.
- Create function to get total card and total address to auto assign address ID and card ID
- Create function to save receipt

Order Controller:
- Create JButton for add card and add address.
- Create functions addAddress() and addCard() for those 2 buttons above.
- Implement makeOrder() function.
- Implement generateReceipt() function.

Mainscreen:
- Create a JLabel and pass the current user into the Mainscreen constructor during loginController to display user full name.


Submission 1:

Use Case 1:
https://youtu.be/rVh_m9ZdDT4

Use Case 2:
https://youtu.be/SkdzrDNTiVI

Use Case 3:
https://youtu.be/ux_OPtdLGeY

Use Case 4:
https://youtu.be/ThWcb8mlxVU


Validation Logic:

Change code of saveProduct():

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

OrderID validation:
add code into makeOrder() function which is not implemented yet.