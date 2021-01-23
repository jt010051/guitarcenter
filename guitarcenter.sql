create database guitarcenter;
use guitarcenter;
create table info (
 id  int(3)not null auto_increment primary key,
        name varchar(20),
        available bool


);
use drinks;
select * from info;


insert into guitarcenter.info values (1, 'Flute', true), (2,  'Drumset', false), (3,  'Piano', true), (4,  'Microphone', true), (5,  'Bass', false), (6,  'Saxophone', true), (7,  'Pa System', false)
, (8,  'Guitar', false), (9,  'Trumpet', true), (10,  'Organ', false);

drop table guitarcenter.info;

