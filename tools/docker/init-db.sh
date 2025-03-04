#!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" <<-EOSQL
  CREATE DATABASE ride;
  CREATE DATABASE driver;
  CREATE DATABASE fleet;
  CREATE DATABASE payment;
  
  CREATE ROLE ro;
  CREATE ROLE rw;
EOSQL
