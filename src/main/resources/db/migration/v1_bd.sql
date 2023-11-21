
CREATE TABLE public.rol (
	id int8 NOT NULL,
	"name" varchar(100) NOT NULL,
	CONSTRAINT rol_pkey PRIMARY KEY (id)
);

CREATE TABLE public.user_detail (
	id int8 NOT NULL,
	age int4 NULL,
	birth_day date NULL,
	first_name varchar(100) NOT NULL,
	last_name varchar(100) NOT NULL,
	user_id int8 NULL,
	CONSTRAINT uk_dm7hrxg9mvrb92v1p3o6wg97u UNIQUE (user_id),
	CONSTRAINT user_detail_pkey PRIMARY KEY (id)
);

ALTER TABLE public.user_detail ADD CONSTRAINT fkr6i0t96qgu9l8l5nn2vqo8rcl FOREIGN KEY (user_id) REFERENCES public.users(id);

CREATE TABLE public.user_rol (
	id int8 NOT NULL,
	active bool NOT NULL,
	created_at timestamp NOT NULL,
	rol_id int8 NULL,
	user_id int8 NULL,
	CONSTRAINT user_rol_pkey PRIMARY KEY (id)
);

ALTER TABLE public.user_rol ADD CONSTRAINT fk3xg2nuaohq3m1jidxctddln2j FOREIGN KEY (user_id) REFERENCES public.users(id);
ALTER TABLE public.user_rol ADD CONSTRAINT fkpfraq7jod5w5xd3sxm3m6y1o FOREIGN KEY (rol_id) REFERENCES public.rol(id);

CREATE TABLE public.users (
	id int8 NOT NULL,
	created_at timestamp NULL,
	email varchar(150) NOT NULL,
	"password" varchar(150) NOT NULL,
	username varchar(150) NOT NULL,
	CONSTRAINT users_pkey PRIMARY KEY (id)
);