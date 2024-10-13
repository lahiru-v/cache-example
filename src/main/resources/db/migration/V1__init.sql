CREATE TABLE books
(
	id          SERIAL PRIMARY KEY,
	title       VARCHAR(50) NOT NULL,
	description VARCHAR(50) NULL,
	page_count  INT         NOT NULL
);
