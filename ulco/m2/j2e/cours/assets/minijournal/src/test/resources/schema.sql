DROP TABLE IF EXISTS authors_articles;
DROP TABLE IF EXISTS articles;
DROP TABLE IF EXISTS authors;

CREATE TABLE authors
(
    id   INT GENERATED ALWAYS AS IDENTITY,
    name VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE articles
(
    id         INT GENERATED ALWAYS AS IDENTITY,
    title      VARCHAR(255)  NOT NULL,
    content    VARCHAR(1000) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);

CREATE TABLE authors_articles
(
    id         INT GENERATED ALWAYS AS IDENTITY,
    author_id  INT,
    article_id INT,
    PRIMARY KEY (id),
    CONSTRAINT fk_author FOREIGN KEY (author_id) REFERENCES authors (id),
    CONSTRAINT fk_articles FOREIGN KEY (article_id) REFERENCES articles (id)
);
