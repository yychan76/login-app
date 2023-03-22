INSERT INTO "ROLE"  VALUES
(1, 1, 'User', 'ROLE_USER'),
(2, 1, 'Manager', 'ROLE_MANAGER');
INSERT INTO "USER_ACCOUNT" (version, first_name, last_name, username, password, enabled, role_id) VALUES
(1, 'Barry', 'Sim', 'barry.sim', '$2a$12$BVTz442m5oTHXWHgKf5RnunUIQqPjwFhnIgbjriJg05oz4l6bu0UK', 1, 1), --pw1
(1, 'Olivia', 'Huang', 'olivia.huang', '$2a$12$3qCL4MnL5hDr5lZPG/wKQ.axeChOZVk2eu810dJHk6P0/0IpTpH6e', 1, 2); --pw2
