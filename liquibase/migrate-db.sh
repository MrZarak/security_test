#!/usr/bin/env bash

docker run --net=host -v Z:/security_test/liquibase/changelog:/liquibase/changelog --rm --name liquibase_update liquibase/liquibase \
--url="jdbc:postgresql://localhost:5431/postgres" --driver=org.postgresql.Driver \
--username=postgres --password=postgres \
--changeLogFile=db_changelog.yaml \
update