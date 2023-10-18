create table userimage (
seq int(10) primary key auto_increment, 
imageName varchar(50),
imageContent varchar(4000),
imageFileName varchar(100) not null,
imageOriginalName varchar(100) not null);