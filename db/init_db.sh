#!/bin/bash
set -e

echo "########### Hello, this is first string!"

# Check if the database exists
psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "postgres" <<-EOSQL
   DROP DATABASE IF EXISTS cache_example;
   CREATE DATABASE cache_example;
EOSQL

echo "########### Hello, this is last string!"
