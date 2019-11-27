package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.domain.Product;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Profile("hibernate")
@Transactional
public class ProductRepositoryHibernate implements RepositoryInterface {

    private final SessionFactory sessionFactory;

    @Autowired
    public ProductRepositoryHibernate(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Optional<Product> findProduct(Long productId) {
        Product product = (Product) sessionFactory.getCurrentSession().createCriteria(Product.class).add(Restrictions.eq("id", productId)).uniqueResult();
        return Optional.ofNullable(product);
    }

    @Override
    public Long save(Product product) {
        return (Long) sessionFactory.getCurrentSession().save(product);
    }

    @Override
    public boolean existsByName(String providedName) {
        Product product = (Product) sessionFactory.getCurrentSession().createCriteria(Product.class).add(Restrictions.eq("name", providedName)).setMaxResults(1).uniqueResult();
        return product != null;
    }

    @Override
    public void delete(Product product) {
        sessionFactory.getCurrentSession().delete(product);
    }

    @Override
    public void update(Product product) {
        sessionFactory.getCurrentSession().update(product);
    }

    @Override
    public List<Product> getAll() {
        return sessionFactory.getCurrentSession().createCriteria(Product.class).list();
    }
}