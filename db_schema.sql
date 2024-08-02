CREATE DATABASE library;

\c library;

-- Create Borrower table
CREATE TABLE borrower (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE
);

-- Create Book table
CREATE TABLE book (
    id SERIAL PRIMARY KEY,
    isbn VARCHAR(20) NOT NULL,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    is_borrowed BOOLEAN DEFAULT FALSE
);

-- Grant all privileges on tables to the user
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO admin;

-- Grant necessary permissions on sequences to the user
GRANT USAGE, SELECT ON SEQUENCE borrower_id_seq TO admin;
GRANT USAGE, SELECT ON SEQUENCE book_id_seq TO admin;
