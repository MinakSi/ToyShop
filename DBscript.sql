CREATE DATABASE toyshop_db;
USE toyshop_db;

CREATE TABLE `order`
(
	id                   INTEGER NOT NULL primary key auto_increment,
	date                 DATE NOT NULL,
	status_id            INTEGER NOT NULL CHECK ( status_id >= 1 ),
	user_id              INTEGER NOT NULL CHECK ( user_id >= 1 ),
	invoice_number       VARCHAR(14) NULL,
    total			     double not null
);
CREATE UNIQUE INDEX XAK1order ON `order`
(
	invoice_number
);
CREATE INDEX XIE1order ON `order`
(
	date
);
CREATE TABLE order_product
(
	product_id           INTEGER NOT NULL,
	order_id             INTEGER NOT NULL,
	amount               INTEGER NOT NULL CHECK ( amount >= 1 ),
	price                DOUBLE NOT NULL CHECK ( price >= 1 )
);


ALTER TABLE order_product
ADD PRIMARY KEY (product_id,order_id);
CREATE INDEX XIE1order_product ON order_product
(
	price
);
CREATE TABLE product
(
	id                   INTEGER NOT NULL primary key auto_increment,
	name                 VARCHAR(255) NOT NULL,
	description          TEXT NULL,
	price                DOUBLE NOT NULL CHECK ( price >= 1 ),
	amount_on_storage    INTEGER NOT NULL CHECK ( amount_on_storage >= 1 )
);
CREATE UNIQUE INDEX XAK1product ON product
(
	name
);
CREATE INDEX XIE1product ON product
(
	price
);
CREATE TABLE status
(
	id                   INTEGER NOT NULL primary key auto_increment,
	name                 VARCHAR(35) NOT NULL
);

CREATE UNIQUE INDEX XAK1status ON status
(
	name
);
CREATE TABLE user
(
	id                   INTEGER NOT NULL primary key auto_increment,
    first_name           VARCHAR(35) NOT NULL,
    second_name          VARCHAR(35) NOT NULL,
    email                VARCHAR(50) NULL,
    phone_number         VARCHAR(26) NOT NULL,
	address              VARCHAR(255) NOT NULL
);

CREATE UNIQUE INDEX XAK1user ON user
(
	phone_number,
	email
);
ALTER TABLE `order`
ADD FOREIGN KEY R_3 (status_id) REFERENCES status (id);
ALTER TABLE `order`
ADD FOREIGN KEY R_2 (user_id) REFERENCES user (id);
ALTER TABLE order_product
ADD FOREIGN KEY R_1 (product_id) REFERENCES product (id)
		ON DELETE CASCADE;
ALTER TABLE order_product
ADD FOREIGN KEY R_5 (order_id) REFERENCES `order` (id)
		ON DELETE CASCADE;

CREATE DEFINER=`admin`@`%` TRIGGER `order_product_BEFORE_INSERT` BEFORE INSERT ON `order_product` FOR EACH ROW BEGIN
	SET NEW.price = (SELECT price FROM product WHERE NEW.product_id = product.id);

    UPDATE `order` SET total = ifnull(total, 0) + NEW.price * NEW.amount
    WHERE `order`.id = NEW.order_id;
END

CREATE DEFINER=`admin`@`%` TRIGGER `order_product_AFTER_INSERT` AFTER INSERT ON `order_product` FOR EACH ROW BEGIN
	DECLARE newamount double;

    #UPDATE product SET price = (SELECT price FROM product WHERE product_id = product.id);

    UPDATE product SET product.amount_on_storage = product.amount_on_storage
		- NEW.amount WHERE product.id = NEW.product_id;

END

CREATE DEFINER=`admin`@`%` TRIGGER `order_product_AFTER_UPDATE` AFTER UPDATE ON `order_product` FOR EACH ROW BEGIN
	DECLARE newamount double;

    SET newamount = NEW.amount;

    UPDATE product SET product.amount_on_storage = product.amount_on_storage
		- newamount + OLD.amount
    WHERE product.id = NEW.product_id;

    UPDATE `order` SET total = total - OLD.price * OLD.amount
    + NEW.price * NEW.amount
    WHERE `order`.id = NEW.order_id;


END

CREATE DEFINER=`admin`@`%` TRIGGER `order_product_AFTER_DELETE` AFTER DELETE ON `order_product` FOR EACH ROW BEGIN
	UPDATE product SET product.amount_on_storage = product.amount_on_storage
		+ OLD.amount WHERE product.id = OLD.product_id;

	UPDATE `order` SET total = total - OLD.price * OLD.amount
    WHERE `order`.id = OLD.order_id;
END