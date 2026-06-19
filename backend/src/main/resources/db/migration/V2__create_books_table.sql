CREATE TABLE books (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    google_books_id VARCHAR(50) NOT NULL UNIQUE,
    title VARCHAR(500) NOT NULL,
    authors VARCHAR(1000),
    description TEXT,
    thumbnail_url VARCHAR(2000),
    isbn_10 VARCHAR(20),
    isbn_13 VARCHAR(20),
    page_count INTEGER,
    published_date VARCHAR(20),
    cached_at TIMESTAMP NOT NULL DEFAULT NOW()
);
