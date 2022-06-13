use mysql;

CREATE USER 'repl'@'%' IDENTIFIED WITH 'mysql_native_password' BY '1234';

GRANT REPLICATION SLAVE ON *.* TO 'repl'@'%';

GRANT INSERT,SELECT,UPDATE,DELETE ON `test_db`.* TO `app_user`@`%`;