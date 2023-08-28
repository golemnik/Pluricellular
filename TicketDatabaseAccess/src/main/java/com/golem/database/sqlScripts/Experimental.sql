select currval (id) from tickets;
select nextval (id) from tickets;

insert into addresses (_street) values ();
insert into coordinates (_x, _y) values ();
insert into venues (_name, _capacity, _type, _address_id) values ();
insert into tickets (_name, _coordinate_id, _creationdate, _price, _comment, _type, _venue_id, _owner) values ();