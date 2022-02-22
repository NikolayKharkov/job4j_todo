CREATE TABLE items (
        id SERIAL PRIMARY KEY,
        description VARCHAR(255),
        created DATE default CURRENT_DATE,
	    done BOOLEAN default false
);