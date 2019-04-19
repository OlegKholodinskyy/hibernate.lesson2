package lesson3_2;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.loader.custom.sql.SQLQueryParser;
import org.hibernate.query.Query;

import java.sql.PreparedStatement;
import java.util.List;

public class ProductDAO {
    /*
    Сигнатура методов:
    findById(Long id) - поиск продукта по id
    findByName(String name) - поиск продуктов по имени
    findByContainedName(String name) - поиск продуктов, которые в своем имени содержать слово name
    findByPrice(int price, int delta) - поиск продуктов по вилке цен price+-delta включительно
    findByNameSortedAsc(String name) - поиск продуктов по имени, результат отсортирован по алфавитному порядку колонки name
    findByNameSortedDesc - поиск продуктов по имени, результат отсортирован начиная с конца алфавита колонки name
    findByPriceSortedDesc(int price, int delta) - поиск продуктов по вилке цен price+-delta включительно, результат отсортирован по убыванию цен
*/
    //  private static SessionFactory sessionFactory;

    private static SessionFactory sessionFactory;

    /*
       findById(Long id) - поиск продукта по id
     */
    public static Product findById(Long id) {
        Session session = null;
        Transaction tr = null;
        List<Product> products = null;
        Product product = null;
        try {
            session = createSessionFactory().openSession();
            tr = session.getTransaction();
            tr.begin();

            Query sqlQuery = session.createSQLQuery("SELECT * FROM PRODUCT where id =?").addEntity(Product.class);
            sqlQuery.setParameter(0, id);
            products = sqlQuery.list();

            product = products.get(0);
            tr.commit();
        } catch (HibernateException e) {
            System.err.println("Something wrong during runing method \"Find Product\" wiht id : " + id);
            System.out.println(e.getMessage());
            if (tr != null) {
                tr.rollback();
            }
        } finally {
            if (session != null)
                session.close();
        }
        return product;
    }

    /*
        findByName(String name) - поиск продуктов по имени
     */
    public static List<Product> findByName(String name) {
        Session session = null;
        Transaction tr = null;
        List<Product> products = null;
        try {
            session = createSessionFactory().openSession();
            tr = session.getTransaction();
            tr.begin();
            Query sqlQuery = session.createSQLQuery("SELECT * FROM PRODUCT where name =?").addEntity(Product.class);
            sqlQuery.setParameter(0, name);
            products = sqlQuery.list();
            tr.commit();
        } catch (HibernateException e) {
            System.err.println("Something wrong during runing method \"Find Product\" wiht name : " + name);
            System.out.println(e.getMessage());
            if (tr != null) {
                tr.rollback();
            }
        } finally {
            if (session != null)
                session.close();
        }
        return products;
    }

    /*
        findByContainedName(String name) - поиск продуктов, которые в своем имени содержать слово name
     */
    public static List<Product> findByContainedName(String name) {
        Session session = null;
        Transaction tr = null;
        List<Product> products = null;
        try {
            session = createSessionFactory().openSession();
            tr = session.getTransaction();
            tr.begin();

            Query sqlQuery = session.createSQLQuery("SELECT * FROM Product WHERE name like ?").addEntity(Product.class);
            sqlQuery.setParameter(0, "%" + name + "%");
            products = sqlQuery.list();
            tr.commit();
        } catch (HibernateException e) {
            System.err.println("Something wrong during runing method \"FindByContainedName\" wiht name : " + name);
            System.out.println(e.getMessage());
            if (tr != null) {
                tr.rollback();
            }
        } finally {
            if (session != null)
                session.close();
        }
        return products;
    }

    /*
        findByPrice(int price, int delta) - поиск продуктов по вилке цен price+-delta включительно
     */
    public static List<Product> findByPrice(int price, int delta) {
        Session session = null;
        Transaction tr = null;
        List<Product> products = null;
        try {
            session = createSessionFactory().openSession();
            tr = session.getTransaction();
            tr.begin();

            Query query = session.createSQLQuery("SELECT * FROM Product WHERE price <= ? AND price >= ?").addEntity(Product.class);
            query.setParameter(0, price + delta);
            query.setParameter(1, price - delta);
            products = query.list();

            tr.commit();
        } catch (HibernateException e) {
            System.err.println("Something wrong during runing method \"findByPrice\" wiht price : " + price + " and delta : " + delta);
            System.out.println(e.getMessage());
            if (tr != null) {
                tr.rollback();
            }
        } finally {
            if (session != null)
                session.close();
        }
        return products;
    }

    /*
    findByNameSortedDesc(String name) - поиск продуктов по имени,  результат отсортирован начиная с конца алфавита колонки name
     */
    public static List<Product> findByNameSortedDesc() {
        Session session = null;
        Transaction tr = null;
        List<Product> products = null;
        try {
            session = createSessionFactory().openSession();
            tr = session.getTransaction();
            tr.begin();

            Query sqlQuery = session.createSQLQuery("SELECT * FROM PRODUCT ORDER BY NAME DESC").addEntity(Product.class);
            products = sqlQuery.list();

            tr.commit();
        } catch (HibernateException e) {
            System.err.println("Something wrong during runing method \"findByNameSortedDesc\" ");
            System.out.println(e.getMessage());
            if (tr != null) {
                tr.rollback();
            }
        } finally {
            if (session != null)
                session.close();
        }
        return products;
    }

    /*
  findByNameSortedAsc(String name) - поиск продуктов по имени, результат отсортирован по алфавитному порядку колонки name
   */
    public static List<Product> findByNameSortedAsc() {
        Session session = null;
        Transaction tr = null;
        List<Product> products = null;
        try {
            session = createSessionFactory().openSession();
            tr = session.getTransaction();
            tr.begin();

            Query query = session.createSQLQuery("SELECT * FROM PRODUCT ORDER BY NAME ASC").addEntity(Product.class);
            products = query.list();

            tr.commit();
        } catch (HibernateException e) {
            System.err.println("Something wrong during runing method \"findByNameSortedAsc\" ");
            System.out.println(e.getMessage());
            if (tr != null) {
                tr.rollback();
            }
        } finally {
            if (session != null)
                session.close();
        }
        return products;
    }

    /*
      findByPriceSortedDesc(int price, int delta) - поиск продуктов по вилке цен price+-delta включительно, результат отсортирован по убыванию цен
     */
    public static List<Product> findByPriceSortedDesc(int price, int delta) {
        Session session = null;
        Transaction tr = null;
        List<Product> products = null;
        try {
            session = createSessionFactory().openSession();
            tr = session.getTransaction();
            tr.begin();

            Query query = session.createSQLQuery("SELECT * FROM PRODUCT WHERE PRICE <= ? AND price >= ? ORDER BY PRICE DESC").addEntity(Product.class);
            query.setParameter(0, price + delta);
            query.setParameter(1, price - delta);
            products = query.list();

            tr.commit();
        } catch (HibernateException e) {
            System.err.println("Something wrong during runing method \"findByPriceSortedDesc\" wiht price : " + price + " and delta : " + delta);
            System.out.println(e.getMessage());
            if (tr != null) {
                tr.rollback();
            }
        } finally {
            if (session != null)
                session.close();
        }
        return products;
    }

    public static SessionFactory createSessionFactory() {
        // singleton
        if (sessionFactory == null) {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }
        return sessionFactory;
    }

}
