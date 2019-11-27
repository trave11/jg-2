package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.domain.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
@Profile("jdbc")
public class ProductRepositoryJdbc implements RepositoryInterface {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ProductRepositoryJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Product> findProduct(Long productId) {
        String query = "select * from products where id=?";

        List<Product> products = jdbcTemplate.query(query, new BeanPropertyRowMapper(Product.class), productId);

        if (!products.isEmpty()) {
            return Optional.ofNullable(products.get(0));
        }
        return Optional.empty();
    }

    @Override
    public Long save(Product product) {
        String query = "insert into products (name, description, category, price, discount) values (?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, product.getName());
            ps.setString(2, product.getDescription());
            ps.setString(3, product.getCategory().toString());
            ps.setBigDecimal(4, product.getPrice());
            ps.setBigDecimal(5, product.getDiscount());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }

    @Override
    public boolean existsByName(String providedName) {
        String query = "select * from products where name=?";
        List<Product> products = jdbcTemplate.query(query, new BeanPropertyRowMapper(Product.class), providedName);

        return !products.isEmpty();
    }

    @Override
    public void delete(Product product) {

    }

    @Override
    public void update(Product product) {

    }

    @Override
    public List<Product> getAll() {
        return null;
    }
}