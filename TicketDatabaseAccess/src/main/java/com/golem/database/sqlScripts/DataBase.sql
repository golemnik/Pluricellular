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
    _street text
);

create table if not exists venues (
     id serial primary key,
     _name text,
     _capacity decimal,
     _type VenueType,
     _address_id integer,
     foreign key (_address_id) references addresses (id)
);

create table if not exists tickets (
    id serial primary key,
    _key text unique,
    _name text,
    _coordinate_id integer,
    _creationDate date,
    _price double precision,
    _comment text,
    _type TicketType,
    _venue_id integer,
    _owner text,
    foreign key (_venue_id) references venues (id),
    foreign key (_coordinate_id) references coordinates (id)
);

create table if not exists collection_props (
  id integer primary key CHECK ( id = 1 ) default 1,
  _creation date
);
