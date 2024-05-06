-- Create User
CREATE OR REPLACE PROCEDURE create_user(
    p_users_id UUID,
    p_username VARCHAR(255),
    p_email VARCHAR(255),
    p_password VARCHAR(255),
    p_deleted BOOLEAN
)
LANGUAGE plpgsql
AS $$
BEGIN
    INSERT INTO users (users_id, username, email, password, deleted)
    VALUES (p_users_id, p_username, p_email, p_password, p_deleted);
END;
$$;

-- Read User
CREATE OR REPLACE FUNCTION read_user(p_users_id UUID)
RETURNS TABLE (
    users_id UUID,
    username VARCHAR(255),
    email VARCHAR(255),
    password VARCHAR(255),
    deleted BOOLEAN
)
AS $$
BEGIN
    RETURN QUERY SELECT * FROM users WHERE users_id = p_users_id;
END;
$$
LANGUAGE plpgsql;

-- Update User
CREATE OR REPLACE PROCEDURE update_user(
    p_users_id UUID,
    p_username VARCHAR(255),
    p_email VARCHAR(255),
    p_password VARCHAR(255),
    p_deleted BOOLEAN
)
LANGUAGE plpgsql
AS $$
BEGIN
    UPDATE users
    SET username = p_username,
        email = p_email,
        password = p_password,
        deleted = p_deleted
    WHERE users_id = p_users_id;
END;
$$;

-- Delete User
CREATE OR REPLACE PROCEDURE delete_user(p_users_id UUID)
LANGUAGE plpgsql
AS $$
BEGIN
    DELETE FROM users WHERE users_id = p_users_id;
END;
$$;

-- Create Merchant
CREATE OR REPLACE PROCEDURE create_merchant(
    p_merchant_id UUID,
    p_name VARCHAR(255),
    p_location VARCHAR(255),
    p_open BOOLEAN,
    p_deleted BOOLEAN
)
LANGUAGE plpgsql
AS $$
BEGIN
    INSERT INTO merchant (merchant_id, name, location, open, deleted)
    VALUES (p_merchant_id, p_name, p_location, p_open, p_deleted);
END;
$$;

-- Read Merchant
CREATE OR REPLACE FUNCTION read_merchant(p_merchant_id UUID)
RETURNS TABLE (
    merchant_id UUID,
    name VARCHAR(255),
    location VARCHAR(255),
    open BOOLEAN,
    deleted BOOLEAN
)
AS $$
BEGIN
    RETURN QUERY SELECT * FROM merchant WHERE merchant_id = p_merchant_id;
END;
$$
LANGUAGE plpgsql;

-- Update Merchant
CREATE OR REPLACE PROCEDURE update_merchant(
    p_merchant_id UUID,
    p_name VARCHAR(255),
    p_location VARCHAR(255),
    p_open BOOLEAN,
    p_deleted BOOLEAN
)
LANGUAGE plpgsql
AS $$
BEGIN
    UPDATE merchant
    SET name = p_name,
        location = p_location,
        open = p_open,
        deleted = p_deleted
    WHERE merchant_id = p_merchant_id;
END;
$$;

-- Delete Merchant
CREATE OR REPLACE PROCEDURE delete_merchant(p_merchant_id UUID)
LANGUAGE plpgsql
AS $$
BEGIN
    DELETE FROM merchant WHERE merchant_id = p_merchant_id;
END;
$$;

-- Create Product
CREATE OR REPLACE PROCEDURE create_product(
    p_product_id UUID,
    p_product_name VARCHAR(255),
    p_merchant_id UUID,
    p_deleted BOOLEAN
)
LANGUAGE plpgsql
AS $$
BEGIN
    INSERT INTO product (product_id, product_name, merchant_id, deleted)
    VALUES (p_product_id, p_product_name, p_merchant_id, p_deleted);
END;
$$;

-- Read Product
CREATE OR REPLACE FUNCTION read_product(p_product_id UUID)
RETURNS TABLE (
    product_id UUID,
    product_name VARCHAR(255),
    merchant_id UUID,
    deleted BOOLEAN
)
AS $$
BEGIN
    RETURN QUERY SELECT * FROM product WHERE product_id = p_product_id;
END;
$$
LANGUAGE plpgsql;

-- Update Product
CREATE OR REPLACE PROCEDURE update_product(
    p_product_id UUID,
    p_product_name VARCHAR(255),
    p_merchant_id UUID,
    p_deleted BOOLEAN
)
LANGUAGE plpgsql
AS $$
BEGIN
    UPDATE product
    SET product_name = p_product_name,
        merchant_id = p_merchant_id,
        deleted = p_deleted
    WHERE product_id = p_product_id;
END;
$$;

-- Delete Product
CREATE OR REPLACE PROCEDURE delete_product(p_product_id UUID)
LANGUAGE plpgsql
AS $$
BEGIN
    DELETE FROM product WHERE product_id = p_product_id;
END;
$$;

-- Create Order
CREATE OR REPLACE PROCEDURE create_order(
    p_id UUID,
    p_order_time TIMESTAMP,
    p_destination_address VARCHAR(255),
    p_completed BOOLEAN,
    p_user_id UUID,
    p_merchant_id UUID,
    p_deleted BOOLEAN
)
LANGUAGE plpgsql
AS $$
BEGIN
    INSERT INTO orders (id, order_time, destination_address, completed, user_id, merchant_id, deleted)
    VALUES (p_id, p_order_time, p_destination_address, p_completed, p_user_id, p_merchant_id, p_deleted);
END;
$$;

-- Read Order
CREATE OR REPLACE FUNCTION read_order(p_id UUID)
RETURNS TABLE (
    id UUID,
    order_time TIMESTAMP,
    destination_address VARCHAR(255),
    completed BOOLEAN,
    user_id UUID,
    merchant_id UUID,
    deleted BOOLEAN
)
AS $$
BEGIN
    RETURN QUERY SELECT * FROM orders WHERE id = p_id;
END;
$$
LANGUAGE plpgsql;

-- Update Order
CREATE OR REPLACE PROCEDURE update_order(
    p_id UUID,
    p_order_time TIMESTAMP,
    p_destination_address VARCHAR(255),
    p_completed BOOLEAN,
    p_user_id UUID,
    p_merchant_id UUID,
    p_deleted BOOLEAN
)
LANGUAGE plpgsql
AS $$
BEGIN
    UPDATE orders
    SET order_time = p_order_time,
        destination_address = p_destination_address,
        completed = p_completed,
        user_id = p_user_id,
        merchant_id = p_merchant_id,
        deleted = p_deleted
    WHERE id = p_id;
END;
$$;

-- Delete Order
CREATE OR REPLACE PROCEDURE delete_order(p_id UUID)
LANGUAGE plpgsql
AS $$
BEGIN
    DELETE FROM orders WHERE id = p_id;
END;
$$;

-- Create Order Detail
CREATE OR REPLACE PROCEDURE create_order_detail(
    p_order_detail_id UUID,
    p_qty INTEGER,
    p_price INTEGER,
    p_product_id UUID,
    p_order_id UUID,
    p_deleted BOOLEAN
)
LANGUAGE plpgsql
AS $$
BEGIN
    INSERT INTO order_detail (order_detail_id, qty, price, product_id, order_id, deleted)
    VALUES (p_order_detail_id, p_qty, p_price, p_product_id, p_order_id, p_deleted);
END;
$$;

-- Read Order Detail
CREATE OR REPLACE FUNCTION read_order_detail(p_order_detail_id UUID)
RETURNS TABLE (
    order_detail_id UUID,
    qty INTEGER,
    price INTEGER,
    product_id UUID,
    order_id UUID,
    deleted BOOLEAN
)
AS $$
BEGIN
    RETURN QUERY SELECT * FROM order_detail WHERE order_detail_id = p_order_detail_id;
END;
$$
LANGUAGE plpgsql;

-- Update Order Detail
CREATE OR REPLACE PROCEDURE update_order_detail(
    p_order_detail_id UUID,
    p_qty INTEGER,
    p_price INTEGER,
    p_product_id UUID,
    p_order_id UUID,
    p_deleted BOOLEAN
)
LANGUAGE plpgsql
AS $$
BEGIN
    UPDATE order_detail
    SET qty = p_qty,
        price = p_price,
        product_id = p_product_id,
        order_id = p_order_id,
        deleted = p_deleted
    WHERE order_detail_id = p_order_detail_id;
END;
$$;

-- Delete Order Detail
CREATE OR REPLACE PROCEDURE delete_order_detail(p_order_detail_id UUID)
LANGUAGE plpgsql
AS $$
BEGIN
    DELETE FROM order_detail WHERE order_detail_id = p_order_detail_id;
END;
$$;
