CREATE TABLE public.users
(
    id bigserial NOT NULL,
    username character varying(250) NOT NULL,
    "fullName" character varying(250) NOT NULL,
    password character varying(250) NOT NULL,
    "phoneNumber" character varying(250) NOT NULL,
    email character varying(250) NOT NULL,
    "createdTime" timestamp without time zone NOT NULL,
    PRIMARY KEY (id)
);