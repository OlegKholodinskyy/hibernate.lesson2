package lesson2;

import java.util.Arrays;
import java.util.List;

public class Demo {

    public static void main(String[] args) {
        Product product = new Product();
        product.setName("Shampoo");
        product.setDescription("Some description");
        product.setPrice(30);

        //      ProductDAO.save(product);

        Product product2 = new Product();
        product2.setName("Shampoo222");
        product2.setDescription("Some description");
        product2.setPrice(30);
        Product product3 = new Product();
        product3.setName("Shampoo333");
        product3.setDescription("Some description");
        product3.setPrice(30);
        Product product4 = new Product();
        product4.setName("Shampoo444");
        product4.setDescription("Some description");
        product4.setPrice(30);

        List<Product> list = Arrays.asList(product2, product3, product4);
        ProductDAO.saveAll(list);


        product2.setPrice(3030);
        product2.setDescription("TEST second UPDATE zzz");
        ProductDAO.update(product2);

        product2.setPrice(111);
        product3.setPrice(111);
        product4.setPrice(111);
        ProductDAO.updateAll(list);

        ProductDAO.delete(product2);
    }
}
