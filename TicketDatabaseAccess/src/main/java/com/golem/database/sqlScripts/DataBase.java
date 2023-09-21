package com.golem.database.sqlScripts;

public class DataBase {
    public static final String check = "select exists (select 1 from pg_type where typname = 'tickettype')";
    public static final String[] cdb = {"""         
            create type tickettype as enum (
                'VIP',
                'USUAL',
                'BUDGETARY',
                'CHEAP'
            );
            """,
            """
            create type venuetype as enum (
                'BAR',
                'LOFT',
                'OPEN_AREA',
                'THEATRE',
                'MALL'
            );
            """,
            """
            create table if not exists coordinates (
                id serial unique primary key,
                _x decimal not null,
                _y decimal not null
            );
            """,
            """
            create table if not exists addresses (
                id serial unique primary key,
                _street text not null
            );
            """,
            """
            create table if not exists venues (
                 id serial unique primary key,
                 _name text not null,
                 _capacity decimal not null,
                 _type venuetype not null,
                 _address_id integer not null,
                 foreign key (_address_id) references addresses (id) on delete cascade
            );
            """,
            """
            create table if not exists tickets (
                id serial unique primary key,
                _key text unique not null,
                _name text not null,
                _coordinate_id integer not null,
                _creationDate date not null,
                _price double precision not null,
                _comment text,
                _type tickettype not null,
                _venue_id integer not null,
                _owner text not null,
                foreign key (_venue_id) references venues (id) on delete cascade,
                foreign key (_coordinate_id) references coordinates (id) on delete cascade
            );
            """,
            """
            create table if not exists collection_props (
              id integer primary key CHECK ( id = 1 ) default 1,
              _creation date
            );
            """,
            """
            insert into collection_props (_creation) values (current_date);
            """};

    public static final String ddb[] = {"""
                    drop type if exists
                        tickettype,
                        venuetype
                    cascade;
            """,
            """
                    drop table if exists
                        Coordinates,
                        Addresses,
                        Clients,
                        Venues,
                        Tickets,
                        collection_props
                    cascade;
            """};
}
