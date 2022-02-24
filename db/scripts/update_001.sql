CREATE TABLE items (
        id SERIAL PRIMARY KEY,
        description VARCHAR(255),
        created DATE default CURRENT_DATE,
	    done BOOLEAN default false
);

CREATE TABLE users (
        id SERIAL PRIMARY KEY,
        name VARCHAR(255),
        email VARCHAR(100) UNIQUE,
        password VARCHAR(100),
        created DATE default CURRENT_DATE
);