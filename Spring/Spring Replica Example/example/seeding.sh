#!/bin/bash

set -e #Exit immediately if a command exits with a non-zero status.

mysql_sqls=(
    "mysql.sql"
    )

test_db=(
	"schema.sql"
	)

sqlExecute() {
	path=$1
	shift
	sqls=("${@}")

	for file in "${sqls[@]}"
	do
		echo "- import: /$path/$file"
		mysql --default-character-set=utf8 -uroot -p1234 < "/$path/$file"
	done
}


sqlExecute "mysql" "${mysql_sqls[@]}"

sqlExecute "test_db" "${test_db[@]}"