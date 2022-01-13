use mysql;
create user 'repl'@'%' identified with 'mysql_native_password' by '1234';
grant replication slave on *.* to 'repl'@'%';
