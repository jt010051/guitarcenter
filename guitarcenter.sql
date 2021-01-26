create database guitarcenter;
use guitarcenter;
create table product (
 id  int(3)not null auto_increment primary key,
        name varchar(20),
        available bool


);
use drinks;
select * from product;


insert into guitarcenter.product values (1, 'Flute', true), (2,  'Drumset', false), (3,  'Piano', true), (4,  'Microphone', true), (5,  'Bass', false), (6,  'Saxophone', true), (7,  'Pa System', false)
, (8,  'Guitar', false), (9,  'Trumpet', true), (10,  'Organ', false);

drop table guitarcenter.product;

create database desserts;
use desserts;
create table goodstuff (id int(10) NOT NULL AUTO_INCREMENT, name varchar(55), good tinyint(1), PRIMARY KEY (id));
insert into goodstuff (name, good) VALUES ("chocolate", 0);
insert into goodstuff (name, good) VALUES ("tiramisu", 1);
insert into goodstuff (name, good) VALUES ("bananas", 1);
select * from goodstuff;

DELIMITER $$

CREATE PROCEDURE GetIsGood(
	IN id int(10),
	OUT goodBAD tinyint(1)
)
BEGIN
  SELECT goodstuff.good
  INTO good
  FROM goodstuff
  WHERE goodstuff.id = id;
END$$

DELIMITER ;
DELIMITER $$

CREATE PROCEDURE GetAlsoGood(
	IN id int(10),
	OUT good tinyint(1),
	OUT alsoGood tinyint(1)
)
BEGIN
	SELECT goodstuff.good
	INTO good
	FROM goodstuff
	WHERE goodstuff.id = id;
  
  	SELECT goodstuff.good
	INTO alsoGood
	FROM goodstuff
	WHERE goodstuff.id = id + 1;
END$$

DELIMITER ;

