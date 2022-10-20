create database fddata DEFAULT CHARACTER SET utf8;
GRANT ALL ON  fddata.* TO 'fdadmin'@'%' WITH GRANT OPTION;
set password for fdadmin = 'Qaz*0001';
