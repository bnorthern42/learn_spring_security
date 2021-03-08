INSERT INTO users(active, roles, username, password, email, created_on)
VALUES(true, 'ROLE_USER', 'user', 'user', "user@test.com", current_timestamp);

INSERT INTO users(active, roles, username, password, email, created_on)
VALUES(true, 'ROLE_ADMIN', 'admin', 'admin', "admin@test.com", current_timestamp);