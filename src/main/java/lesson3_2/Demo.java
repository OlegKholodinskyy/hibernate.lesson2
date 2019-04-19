package lesson3_2;

import java.util.List;

public class Demo {

    public static void main(String[] args) {
        ProductDAO productDAO = new ProductDAO();

        System.out.println("***findById***");
        System.out.println(productDAO.findById(1L));
        System.out.println("***findByName***");
        List<Product> productList = productDAO.findByName("Shampoo");
        for (Product p: productList){
            System.out.println(p.toString());
        }
        System.out.println("***findByContainedName***");
        List<Product> productList2 = productDAO.findByContainedName("st");
        for (Product p: productList2){
            System.out.println(p.toString());
        }
        System.out.println("***findByPrice***");
        List<Product> productList3 = productDAO.findByPrice(1000,950);
        for (Product p: productList3){
            System.out.println(p.toString());
        }
        System.out.println("***findByNameSortedAsc***");
        List<Product> productList4 = productDAO.findByNameSortedAsc();
        for (Product p: productList4){
            System.out.println(p.toString());
        }
        System.out.println("***findByNameSortedDesc***");
        List<Product> productList5 = productDAO.findByNameSortedDesc();
        for (Product p: productList5){
            System.out.println(p.toString());
        }
        System.out.println("***findByPriceSortedDesc***");
        List<Product> productList6 = productDAO.findByPriceSortedDesc(1000,700);
        for (Product p: productList6){
            System.out.println(p.toString());
        }
    }
}
