INSERT INTO author (name, birthday)
VALUES ('F. Scott Fitzgerald', '1896-09-24'),
	   ('Harper Lee', '1926-04-28'),
	   ('George Orwell', '1903-06-25'),
	   ('Jane Austen', '1775-12-16'),
	   ('Herman Melville', '1819-08-01'),
	   ('Leo Tolstoy', '1828-09-09'),
	   ('J.D. Salinger', '1919-01-01'),
	   ('J.R.R. Tolkien', '1892-01-03'),
	   ('Fyodor Dostoevsky', '1821-11-11'),
	   ('Aldous Huxley', '1894-07-26'),
	   ('Arthur Conan Doyle', '1859-05-22'),
	   ('Mary Shelley', '1797-08-30'),
	   ('Bram Stoker', '1847-11-08'),
	   ('Homer', NULL),
	   ('James Joyce', '1882-02-02'),
	   ('Miguel de Cervantes', '1547-09-29');

INSERT INTO genre (name, description)
VALUES ('Classics', 'Timeless works of literature'),
	   ('Fiction', 'Literary works based on imaginative narration'),
	   ('Philosophical', 'Books dealing with philosophical themes'),
	   ('Adventure', 'Exciting and action-packed stories'),
	   ('Fantasy', 'Stories set in fantastical worlds'),
	   ('Science Fiction', 'Books with futuristic or speculative concepts'),
	   ('Horror', 'Books meant to scare or unsettle the reader'),
	   ('Drama', 'Serious, character-driven narratives'),
	   ('Epic', 'Long, grand stories with heroic elements');

INSERT INTO book_author (book_id, author_id)
VALUES (1, 1), -- The Great Gatsby by F. Scott Fitzgerald
	   (2, 2), -- To Kill a Mockingbird by Harper Lee
	   (3, 3), -- 1984 by George Orwell
	   (4, 4), -- Pride and Prejudice by Jane Austen
	   (5, 5), -- Moby Dick by Herman Melville
	   (6, 6), -- War and Peace by Leo Tolstoy
	   (7, 7), -- The Catcher in the Rye by J.D. Salinger
	   (8, 8), -- The Hobbit by J.R.R. Tolkien
	   (9, 9), -- Crime and Punishment by Fyodor Dostoevsky
	   (10, 6), -- Anna Karenina by Leo Tolstoy
	   (11, 10), -- Brave New World by Aldous Huxley
	   (12, 8), -- The Lord of the Rings by J.R.R. Tolkien
	   (13, 11), -- The Adventures of Sherlock Holmes by Arthur Conan Doyle
	   (14, 9), -- The Brothers Karamazov by Fyodor Dostoevsky
	   (15, 12), -- Frankenstein by Mary Shelley
	   (16, 13), -- Dracula by Bram Stoker
	   (17, 14), -- The Odyssey by Homer
	   (18, 15), -- Ulysses by James Joyce
	   (19, 14), -- The Iliad by Homer
	   (20, 16); -- Don Quixote by Miguel de Cervantes

INSERT INTO book_genre (book_id, genre_id)
VALUES (1, 1),  -- The Great Gatsby (Classics)
	   (2, 1),  -- To Kill a Mockingbird (Classics)
	   (3, 6),  -- 1984 (Science Fiction)
	   (4, 1),  -- Pride and Prejudice (Classics)
	   (5, 4),  -- Moby Dick (Adventure)
	   (6, 1),  -- War and Peace (Classics)
	   (7, 1),  -- The Catcher in the Rye (Classics)
	   (8, 5),  -- The Hobbit (Fantasy)
	   (9, 3),  -- Crime and Punishment (Philosophical)
	   (10, 1), -- Anna Karenina (Classics)
	   (11, 6), -- Brave New World (Science Fiction)
	   (12, 5), -- The Lord of the Rings (Fantasy)
	   (13, 4), -- The Adventures of Sherlock Holmes (Adventure)
	   (14, 3), -- The Brothers Karamazov (Philosophical)
	   (15, 7), -- Frankenstein (Horror)
	   (16, 7), -- Dracula (Horror)
	   (17, 9), -- The Odyssey (Epic)
	   (18, 9), -- Ulysses (Epic)
	   (19, 9), -- The Iliad (Epic)
	   (20, 4); -- Don Quixote (Adventure)