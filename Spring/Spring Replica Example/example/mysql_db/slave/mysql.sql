use mysql;

CHANGE MASTER TO MASTER_HOST='mysql_master', MASTER_PORT=3306, MASTER_USER='repl', MASTER_PASSWORD='1234', MASTER_LOG_FILE='mysql-bin.000002', MASTER_LOG_POS=156;

GRANT INSERT,SELECT,UPDATE,DELETE ON `test_db`.* TO `app_user`@`%`;