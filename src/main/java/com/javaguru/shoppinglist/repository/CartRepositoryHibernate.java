package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.domain.Cart;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Profile("hibernate")
@Transactional
public class CartRepositoryHibernate {

    private final SessionFactory sessionFactory;

    @Autowired
    public CartRepositoryHibernate(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Long save(Cart cart) {
        return (Long) this.sessionFactory.getCurrentSession().save(cart);
    }

    public Optional<Cart> findCartById(Long cartId) {
        Cart cart = (Cart) this.sessionFactory.getCurrentSession().createCriteria(Cart.class).add(Restrictions.eq("id", cartId)).uniqueResult();
        return Optional.ofNullable(cart);
    }

    public void update(Cart cart) {
        this.sessionFactory.getCurrentSession().update(cart);
    }

    public void remove(Cart cart) {
        this.sessionFactory.getCurrentSession().delete(cart);
    }
}