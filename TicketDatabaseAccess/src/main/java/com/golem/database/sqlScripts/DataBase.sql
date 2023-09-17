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
    id serial unique primary key,
    _x decimal not null ,
    _y decimal not null
);

create table if not exists addresses (
    id serial unique primary key,
    _street text not null
);

create table if not exists venues (
     id serial unique primary key,
     _name text not null ,
     _capacity decimal not null ,
     _type VenueType not null ,
     _address_id integer not null ,
     foreign key (_address_id) references addresses (id)
);

create table if not exists tickets (
    id serial unique primary key,
    _key text unique not null,
    _name text not null,
    _coordinate_id integer not null,
    _creationDate date not null,
    _price double precision not null,
    _comment text,
    _type TicketType not null,
    _venue_id integer not null,
    _owner text not null,
    foreign key (_venue_id) references venues (id),
    foreign key (_coordinate_id) references coordinates (id)
);

create table if not exists collection_props (
  id integer primary key CHECK ( id = 1 ) default 1,
  _creation date
);

insert into collection_props (_creation) values (current_date);
