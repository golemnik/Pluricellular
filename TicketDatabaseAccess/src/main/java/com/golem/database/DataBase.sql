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

create table if not exists Coordinates (
    id serial primary key,
    _x decimal,
    _y decimal
);

create table if not exists Address (
    id serial primary key,
    _street varchar(200)
);

create table if not exists Client (
    id serial primary key,
    _name varchar(50)
);

create table if not exists Venue (
     id serial primary key,
     _v_id serial,
     _name varchar(50),
     _capacity decimal,
     _type VenueType,
     _address_id integer,
     foreign key (_address_id) references Address (id)
);

create table if not exists Ticket (
    id serial primary key,
    _t_id serial,
    _name varchar(50),
    _coordinates Coordinates,
    _creationDate date,
    _price double precision,
    _comment varchar(200),
    _type TicketType,
    _venue_id integer,
    foreign key (_venue_id) references Venue (id),
    _client_id integer,
    foreign key (_client_id) references Client (id)
);



