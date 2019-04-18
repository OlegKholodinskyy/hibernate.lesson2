package lesson3;

import java.util.List;

public class Demo {

    public static void main(String[] args) {
        ProductDAO productDAO = new ProductDAO();
/*
        System.out.println(productDAO.findById(1L));

        List<Product> productList = productDAO.findByName("Shampoo");
        for (Product p: productList){
            System.out.println(p.toString());
        }

        List<Product> productList = productDAO.findByContainedName("st");
        for (Product p: productList){
            System.out.println(p.toString());
        }

        List<Product> productList = productDAO.findByPrice(1000,950);
        for (Product p: productList){
            System.out.println(p.toString());
        }

        List<Product> productList2 = productDAO.findByNameSortedAsc();
        for (Product p: productList2){
            System.out.println(p.toString());
        }

        List<Product> productList2 = productDAO.findByNameSortedDesc();
        for (Product p: productList2){
            System.out.println(p.toString());
        }
        */

        List<Product> productList2 = productDAO.findByPriceSortedDesc(1000,700);
        for (Product p: productList2){
            System.out.println(p.toString());
        }

    }
}
