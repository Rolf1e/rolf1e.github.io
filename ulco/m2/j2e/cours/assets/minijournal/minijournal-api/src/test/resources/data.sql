INSERT INTO authors(name)
VALUES ('Tigran');
INSERT INTO authors(name)
VALUES ('Arthur');

INSERT INTO articles(title, content)
VALUES ('First article', 'Content of the first article');
INSERT INTO articles(title, content)
VALUES ('Second article', 'Content of the second article');
INSERT INTO articles(title, content)
VALUES ('Third article', 'Content of the third article');

INSERT INTO authors_articles(author_id, article_id)
VALUES (1, 1);
INSERT INTO authors_articles(author_id, article_id)
VALUES (1, 2);
INSERT INTO authors_articles(author_id, article_id)
VALUES (2, 3);
    
SELECT * FROM authors;
SELECT * FROM articles;

CREATE DATABASE default_database;
