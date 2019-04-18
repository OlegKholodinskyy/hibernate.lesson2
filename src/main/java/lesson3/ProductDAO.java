package lesson3;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

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

            String hqlFindById = "FROM Product WHERE Id = :idProduct";
            Query query = session.createQuery(hqlFindById);
            query.setParameter("idProduct", id);
            products = query.list();

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

        if (products != null && !products.isEmpty()) {
            product = products.get(0);
        } else {
            System.out.println("Nothing found. :)");
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

            String hqlFindById = "FROM Product WHERE name = :nameProduct";
            Query query = session.createQuery(hqlFindById);
            query.setParameter("nameProduct", name);
            products = query.list();

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

            String hqlFindByName = "FROM Product WHERE name like :nameProduct";
            Query query = session.createQuery(hqlFindByName);
            query.setParameter("nameProduct", "%" + name + "%");
            products = query.list();

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

            String hqlFindByPrice = "FROM Product WHERE price <= :maxValue AND price >= :minValue";
            Query query = session.createQuery(hqlFindByPrice);
            query.setParameter("maxValue", price + delta);
            query.setParameter("minValue", price - delta);
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

            String queryString = "From Product ORDER BY name DESC";
            Query query = session.createQuery(queryString);
           // query.setParameter("nameProduct", name);
            products = query.list();

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
  Извини. Но тут условие непонятное чесно говоря. Если я выбираю по имени - то сортировать по нему же я не вижу смысла.
  Поетому изменил сигнатуру метода.
   */
    public static List<Product> findByNameSortedAsc() {
        Session session = null;
        Transaction tr = null;
        List<Product> products = null;
        try {
            session = createSessionFactory().openSession();
            tr = session.getTransaction();
            tr.begin();

            String queryString = "From Product ORDER BY name ASC";
            Query query = session.createQuery(queryString);
            // query.setParameter("nameProduct", name);
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

        String hqlFindByPrice = "FROM Product WHERE price <= :maxValue AND price >= :minValue ORDER BY price DESC";
        Query query = session.createQuery(hqlFindByPrice);
        query.setParameter("maxValue", price + delta);
        query.setParameter("minValue", price - delta);
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
