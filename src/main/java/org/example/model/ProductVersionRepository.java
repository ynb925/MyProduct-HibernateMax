package org.example.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ProductVersionRepository {

    private final SessionFactory sessionFactory;

    public ProductVersionRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public ProductVersion save(ProductVersion productVersion) {
        Session session = sessionFactory.openSession();
        var transaction = session.beginTransaction();

        session.persist(productVersion);

        transaction.commit();

        return productVersion;
    }

    public ProductVersion findById(long id) {
        Session session = sessionFactory.openSession();
        var transaction = session.beginTransaction();

        var identifier = session.get(ProductVersion.class, id);

        transaction.commit();

        return identifier;
    }

    public boolean deleteById(long id) {
        boolean result = false;

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        ProductVersion productVersion = session.get(ProductVersion.class, id);

        if (productVersion != null) {
            session.remove(productVersion);
            result = true;
        }
        transaction.commit();

        return result;
    }

    public List<ProductVersion> findAll() {
        List<ProductVersion> productVersions;

        Session session = sessionFactory.openSession();
        String hql = "from ProductVersion";
        Query<ProductVersion> query = session.createQuery(hql, ProductVersion.class);
        productVersions = query.list();

        return productVersions;
    }

    public void close() {
        sessionFactory.close();
    }
}

