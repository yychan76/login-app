CREATE TABLE ROLE (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    version INT,
    name VARCHAR(20) NOT NULL,
    authority VARCHAR_IGNORECASE(50) NOT NULL
);

CREATE TABLE USER_ACCOUNT (
    username VARCHAR_IGNORECASE(50) NOT NULL PRIMARY KEY,
    version INT,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    password VARCHAR(100) NOT NULL,
    enabled TINYINT NOT NULL DEFAULT 1,
    role_id BIGINT,
    CONSTRAINT FK_USER_ACCOUNT_ROLE FOREIGN KEY (role_id) REFERENCES ROLE (id)
);

CREATE TABLE AUTHORITIES (
	username VARCHAR_IGNORECASE(50) NOT NULL,
	authority VARCHAR_IGNORECASE(50) NOT NULL,
	CONSTRAINT FK_AUTHORITIES_USERS FOREIGN KEY(username) REFERENCES USER_ACCOUNT(username)
);
CREATE UNIQUE INDEX IX_AUTH_USERNAME ON AUTHORITIES (username,authority);