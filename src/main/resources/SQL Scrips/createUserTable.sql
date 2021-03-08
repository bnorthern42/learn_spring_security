--look into: https://www.postgresqltutorial.com/postgresql-create-table/ and seperate roles into own table and account details to role with foreign keys
CREATE TABLE users(
        id serial PRIMARY KEY,
        active BOOLEAN NOT NULL,
        roles VARCHAR(50),
        username VARCHAR(50) UNIQUE NOT NULL,
        password VARCHAR(200) NOT NULL,
        email VARCHAR(255) UNIQUE NOT NULL,
        created_on TIMESTAMP NOT NULL,
        last_login TIMESTAMP
        );