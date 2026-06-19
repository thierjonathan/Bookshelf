CREATE TABLE user_books (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    book_id UUID NOT NULL REFERENCES books(id) ON DELETE CASCADE,
    status VARCHAR(20) NOT NULL DEFAULT 'WANT_TO_READ',
    current_page INTEGER,
    start_date DATE,
    finish_date DATE,
    rating SMALLINT CHECK (rating >= 1 AND rating <= 5),
    UNIQUE (user_id, book_id)
);