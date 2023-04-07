package org.example.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ProductRepository {

    private final SessionFactory sessionFactory;

    public ProductRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Product save(Product product) {
        Session session = sessionFactory.openSession();
        var transaction = session.beginTransaction();

        session.persist(product);

        transaction.commit();

        return product;
    }

    public Product findById(long id) {
        Session session = sessionFactory.openSession();
        var transaction = session.beginTransaction();

        var identifier = session.get(Product.class, id);

        transaction.commit();

        return identifier;
    }

    public boolean deleteById(long id) {
        boolean result = false;

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Product product = session.get(Product.class, id);

        if (product != null) {
            session.delete(product);
            result = true;
        }
        transaction.commit();

        return result;
    }

    public List<Product> findAll() {
        List<Product> products;

        Session session = sessionFactory.openSession();
        String hql = "from Product";
        Query<Product> query = session.createQuery(hql, Product.class);
        products = query.list();

        return products;
    }

    public void close() {
        sessionFactory.close();
    }
}

