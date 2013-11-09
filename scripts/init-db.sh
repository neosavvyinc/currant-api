#!/bin/bash

DB=currant

echo "Cleanup ..."
echo "DROP OWNED BY currant_user CASCADE;" | psql -h localhost -p 5432 $DB
echo "DROP OWNED BY readwrite_currant CASCADE;" | psql -h localhost -p 5432 $DB
echo "DROP OWNED BY integration_test_currant CASCADE;" | psql -h localhost -p 5432 $DB
echo "DROP ROLE IF EXISTS currant_user;" | psql -h localhost -p 5432 $DB
echo "DROP ROLE IF EXISTS readwrite_currant;" | psql -h localhost -p 5432 $DB
echo "DROP ROLE IF EXISTS integration_test_currant;" | psql -h localhost -p 5432 $DB
echo "DROP DATABASE IF EXISTS currant;" | psql -h localhost -p 5432 postgres

echo "Creating database ... "
echo "CREATE DATABASE $DB;" | psql -h localhost -p 5432 postgres

echo "Initializing system accounts ..."

echo " ... RW service ..."

currantPasswordEscaped=currant_user

echo "CREATE ROLE readwrite_currant; GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA public TO readwrite_currant; GRANT USAGE ON ALL SEQUENCES IN SCHEMA public TO readwrite_currant;" | psql -h localhost -p 5432 $DB
echo "CREATE ROLE currant_user with LOGIN PASSWORD '$currantPasswordEscaped' IN ROLE readwrite_currant;" | psql -h localhost -p 5432 $DB
echo "CREATE ROLE integration_test_currant with LOGIN PASSWORD 'integration_test_currant' IN ROLE readwrite_currant;" | psql -h localhost -p 5432 $DB
echo "ALTER ROLE integration_test_currant CREATEDB;" | psql -h localhost -p 5432 $DB


echo ".....creating schema...."

psql -h localhost -p 5432 $DB -f ddl.sql


echo "Done!"
