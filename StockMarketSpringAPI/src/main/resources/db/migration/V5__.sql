ALTER TABLE users
DROP
COLUMN auth_provider_type;

ALTER TABLE users
DROP
COLUMN provider_id;

ALTER TABLE company
    ALTER COLUMN company_name SET NOT NULL;

ALTER TABLE company
    ALTER COLUMN country SET NOT NULL;

ALTER TABLE stocks
    ALTER COLUMN created_at SET NOT NULL;

ALTER TABLE stocks
    ALTER COLUMN market_capitalization SET NOT NULL;

ALTER TABLE stocks
    ALTER COLUMN share_outstanding SET NOT NULL;

ALTER TABLE company
    ALTER COLUMN symbol SET NOT NULL;