CREATE TABLE "users" (
    users_id UUID PRIMARY KEY,
    username VARCHAR(255),
    email VARCHAR(255),
    password VARCHAR(255),
    deleted BOOLEAN
);

CREATE TABLE "merchant" (
    merchant_id UUID PRIMARY KEY,
    name VARCHAR(255),
    location VARCHAR(255),
    open BOOLEAN,
    deleted BOOLEAN
);

CREATE TABLE "product" (
    product_id UUID PRIMARY KEY,
    product_name VARCHAR(255),
    merchant_id UUID,
    deleted BOOLEAN,
    FOREIGN KEY (merchant_id) REFERENCES "merchant" (merchant_id)
);

CREATE TABLE "order" (
    id UUID PRIMARY KEY,
    order_time TIMESTAMP,
    destination_address VARCHAR(255),
    completed BOOLEAN,
    user_id UUID,
    merchant_id UUID,
    deleted BOOLEAN,
    FOREIGN KEY (user_id) REFERENCES "users" (users_id),
    FOREIGN KEY (merchant_id) REFERENCES "merchant" (merchant_id)
);

CREATE TABLE "order_detail" (
    order_detail_id UUID PRIMARY KEY,
    qty INTEGER,
    price INTEGER,
    product_id UUID,
    order_id UUID,
    deleted BOOLEAN,
    FOREIGN KEY (product_id) REFERENCES "product" (order_id),
    FOREIGN KEY (order_id) REFERENCES "orders" (order_id)
);