create type TicketType as enum (
    VIP,
    USUAL,
    BUDGETARY,
    CHEAP
)

create table Ticket (
    id integer,
    t_name varchar(50),
    coordinates Coordinates,
    creationDate date,
    price double precision,
    comment varchar(200),
    t_type TicketType,
    venue Venue
);

create table Coordinates (

);

create table Venue (

);

create table User (

);