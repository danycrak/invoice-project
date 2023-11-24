CREATE TABLE IF NOT EXISTS  client (
    id SERIAL,
    fullname  VARCHAR (255) NOT NULL,
    adrress VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
    );


CREATE TABLE IF NOT EXISTS invoice(
    id SERIAL,
    create_at DATE NOT NULL,
    total DECIMAL(10,2),
    client_id INT,
    PRIMARY KEY (id),
    FOREIGN KEY (client_id) REFERENCES client(id)
    );

CREATE TABLE IF NOT EXISTS product(
    id SERIAL ,
    description VARCHAR (100) NOT NULL,
    brand VARCHAR (50) NOT NULL,
    price VARCHAR (50) NOT NULL,
    stock INT NOT NULL,
    PRIMARY KEY (id)
    );


CREATE TABLE IF NOT EXISTS detail(
    id SERIAL ,
    quantity INT NOT NULL,
    price VARCHAR (50) NOT NULL,
    invoice_id INT,
    product_id INT,
    PRIMARY KEY (id),
    FOREIGN KEY (invoice_id) REFERENCES invoice(id),
    FOREIGN KEY (product_id) REFERENCES product(id)
    );

create view  invoice_view as
SELECT i.*, c.fullname
FROM invoice i JOIN client c
ON  c.id = i.client_id
