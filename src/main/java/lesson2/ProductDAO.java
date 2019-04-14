package lesson2;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Arrays;
import java.util.List;

public class ProductDAO {
    /*
   Сигнатура методов:
  +  Product save(Product)
    Product update(Product)
    Product delete(Product)
  +  void saveAll(List products)
    void updateAll(List products)
    void deleteAll(List products)
*/
    private static SessionFactory sessionFactory;

    public static void save(Product product) {
        Session session = null;
        Transaction tr = null;
        try {
            // 1 - create session / transaction
            session = createSessionFactory().openSession();
            tr = session.getTransaction();
            tr.begin();
            // 2 - action
            session.save(product);
            // 3 - close session / transaction
            tr.commit();
            System.out.println("Product id : " + product.getId() + "is saved");

        } catch (HibernateException e) {
            System.err.println("Fail save");
            System.out.println(e.getMessage());
            if (tr != null) {
                tr.rollback();
            }
        } finally {
            if (session != null)
                session.close();
        }
    }

    public static void saveAll(List<Product> productList) {
        Session session = null;
        Transaction tr = null;
        try {
            // 1 - create session / transaction
            session = createSessionFactory().openSession();
            tr = session.getTransaction();
            tr.begin();
            // 2 - action
            for (Product product : productList) {
                session.save(product);
            }
            // 3 - close session / transaction
            tr.commit();
            System.out.println("Products is saved");

        } catch (HibernateException e) {
            System.err.println("Fail save");
            System.out.println(e.getMessage());
            if (tr != null) {
                tr.rollback();
            }
        } finally {
            if (session != null)
                session.close();
        }
    }


    public static void update(Product product) {
        Session session = null;
        Transaction tr = null;
        try {
            // 1 - create session / transaction
            session = createSessionFactory().openSession();
            tr = session.getTransaction();
            tr.begin();
            // 2 - action
            session.update(product);
            // 3 - close session / transaction
            tr.commit();
            System.out.println("Product id : " + product.getId() + " is updated");

        } catch (HibernateException e) {
            System.out.println("Fail update");
            System.out.println(e.getMessage());
            if (tr != null) {
                tr.rollback();
            }
        } finally {
            if (session != null)
                session.close();
        }
    }

    public static void updateAll(List<Product> products) {
        Session session = null;
        Transaction tr = null;
        try {
            // 1 - create session / transaction
            session = createSessionFactory().openSession();
            tr = session.getTransaction();
            tr.begin();
            // 2 - action
            for (Product product : products) {
                session.update(product);
            }
            // 3 - close session / transaction
            tr.commit();
            System.out.println("Products is updated");
        } catch (HibernateException e) {
            System.err.println("Fail save");
            System.out.println(e.getMessage());
            if (tr != null) {
                tr.rollback();
            }
        } finally {
            if (session != null)
                session.close();
        }
    }


    public static void delete(Product product) {
        Session session = null;
        Transaction tr = null;
        try {
            session = createSessionFactory().openSession();
            tr = session.getTransaction();
            tr.begin();

            session.delete(product);
            tr.commit();
            System.out.println("Product id : " + product.getId() + " is deleted");

        } catch (HibernateException e) {
            System.out.println("Wrong.Delete product id: " + product.getId());
            System.out.println(e.getMessage());
            if (tr != null) {
                tr.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }

        }
    }


    public static void deleteAll(List<Product> productList) {

        Session session = null;
        Transaction tr = null;

        try {
            session = createSessionFactory().openSession();
            tr = session.getTransaction();
            tr.begin();

            for (Product product : productList) {
                session.delete(product);
            }
            tr.commit();
            System.out.println("Method deleteAll() finish");
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            System.out.println("DeleteAll was crashed");

            if (tr != null) {
                tr.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }

    }

    public static SessionFactory createSessionFactory() {
        // singleton
        if (sessionFactory == null) {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }
        return sessionFactory;
    }

}
