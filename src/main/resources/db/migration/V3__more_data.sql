alter table book alter column description type varchar(255);

create table author
(
	id       serial primary key,
	name     varchar(255) not null,
	birthday date null
);

create table book_author
(
	book_id   int not null,
	author_id int not null,
	primary key (book_id, author_id),
	constraint "book_author_book" foreign key (book_id) references book (id) on delete cascade,
	constraint "book_author_author" foreign key (author_id) references author (id) on delete cascade
);

create table genre
(
	id   serial primary key,
	name varchar(255) not null,
	description varchar(255) null
);

create table book_genre
(
	book_id int not null,
	genre_id int not null,
	primary key (book_id, genre_id),
	constraint "book_genre_book" foreign key (book_id) references book (id) on delete cascade,
	constraint "book_genre_genre" foreign key (genre_id) references genre (id) on delete cascade
);


