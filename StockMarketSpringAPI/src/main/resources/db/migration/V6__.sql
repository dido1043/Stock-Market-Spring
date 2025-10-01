ALTER TABLE users
    ADD provider_type VARCHAR(50);

ALTER TABLE users
    ALTER COLUMN password_hash
    DROP NOT NULL;
ALTER TABLE company
    ALTER COLUMN company_name SET NOT NULL;

ALTER TABLE company
    ALTER COLUMN country SET NOT NULL;

ALTER TABLE stocks
    ALTER COLUMN created_at SET NOT NULL;

ALTER TABLE stocks
    ALTER COLUMN market_capitalization SET NOT NULL;

ALTER TABLE users
    ALTER COLUMN password_hash DROP NOT NULL;

ALTER TABLE stocks
    ALTER COLUMN share_outstanding SET NOT NULL;

ALTER TABLE company
    ALTER COLUMN symbol SET NOT NULL;