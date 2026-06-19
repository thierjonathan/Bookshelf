CREATE TABLE shelf_books (
    shelf_id UUID NOT NULL REFERENCES shelves(id) ON DELETE CASCADE,
    book_id UUID NOT NULL REFERENCES books(id) ON DELETE CASCADE,
    added_at TIMESTAMP NOT NULL DEFAULT NOW(),
    PRIMARY KEY (shelf_id, book_id)
);