CREATE TABLE IF NOT EXISTS carts (
 id BIGINT NOT NULL AUTO_INCREMENT,
 name VARCHAR(100) NOT NULL,
 created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
 PRIMARY KEY (id)
)
 ENGINE = InnoDB
 AUTO_INCREMENT = 1;

CREATE TABLE IF NOT EXISTS products (
 id BIGINT NOT NULL AUTO_INCREMENT,
 name VARCHAR(100) NOT NULL,
 description VARCHAR(1000) NULL,
 category VARCHAR(100) NOT NULL,
 price DECIMAL(10,2) NOT NULL,
 discount DECIMAL(10,2) NOT NULL,
 cart_id BIGINT NULL,
 created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
 PRIMARY KEY (id),
 FOREIGN KEY (cart_id) REFERENCES carts(id)
)
 ENGINE = InnoDB
 AUTO_INCREMENT = 1;