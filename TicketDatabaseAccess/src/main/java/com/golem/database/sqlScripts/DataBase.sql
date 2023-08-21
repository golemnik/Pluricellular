create type TicketType as enum (
    'VIP',
    'USUAL',
    'BUDGETARY',
    'CHEAP'
);

create type VenueType as enum (
    'BAR',
    'LOFT',
    'OPEN_AREA',
    'THEATRE',
    'MALL'
);

create table if not exists coordinates (
    id serial primary key,
    _x decimal,
    _y decimal
);

create table if not exists addresses (
    id serial primary key,
    _street varchar(200)
);

create table if not exists clients (
    id serial primary key,
    _name varchar(50)
);

create table if not exists venues (
     id serial primary key,
     _name varchar(50),
     _capacity decimal,
     _type VenueType,
     _address_id integer,
     foreign key (_address_id) references addresses (id)
);

create table if not exists tickets (
    id serial primary key,
    _name varchar(50),
    _coordinate_id integer,
    _creationDate date,
    _price double precision,
    _comment varchar(200),
    _type TicketType,
    _venue_id integer,
    _client_id integer,
    foreign key (_venue_id) references venues (id),
    foreign key (_client_id) references clients (id),
    foreign key (_coordinate_id) references coordinates (id)
);



