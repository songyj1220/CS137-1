/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

/**
 *
 * @author misoo
 */
public class CartItem {
    Product product;
    int quantity=0;

     public CartItem(Product product){
         this.product = product;
         quantity =0;
     }
    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity= quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public void addOne(){
		quantity += 1;
    }
    public boolean equals(Object obj) {
    if(obj instanceof CartItem){
            CartItem item = (CartItem) obj;
            if(product == null || item.getProduct() == null)
                    return false;
            else
                    return product.getPid()== item.getProduct().getPid();
    }
    else if(obj instanceof Product){
            if(product != null)
                    return product.getPid() == ((Product) obj).getPid();
    }
    else if(obj instanceof Integer){
            if(product != null)
                    return product.getPid() == (Integer)obj;
    }
    return false;
}
}
