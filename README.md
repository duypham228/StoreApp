Duy Pham
Submission

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