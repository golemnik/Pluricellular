select currval (id) from tickets;
select nextval (id) from tickets;

select *
from clients where _name = '';

select id from addresses where _street = '';

insert into clients (_name) values ();
insert into addresses (_street) values ();
insert into coordinates (_x, _y) values ();
insert into venues (_name, _capacity, _type, _address_id) values ();
insert into tickets (_name, _coordinate_id, _creationdate, _price, _comment, _type, _venue_id, _client_id) values ();