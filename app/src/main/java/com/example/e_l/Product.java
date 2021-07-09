package com.example.e_l;

public class Product {
    private String productname;
    private String productcategory;
    private String productqty;
    private String barcodenumber;

    public Product() {

    }
    public Product(String name,String category,String qty,String barcode) {
        this.productname=name;
        this.productcategory=category;
        this.productqty=qty;
        this.barcodenumber= barcode;
            }
    public String getName() {
        return productname;
    }

    public String getCategory() {
        return productcategory;
    }

    public String getQty() {
        return productqty;
    }

    public String getBarcode() {
        return barcodenumber;
    }
}
