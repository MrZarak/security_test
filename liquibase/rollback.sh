#!/usr/bin/env bash

docker run --net=host -v Z:/security_test/src/main/resources/db/changelog:/liquibase/changelog --rm --name liquibase_update liquibase/liquibase \
--url="jdbc:postgresql://localhost:5431/postgres" --driver=org.postgresql.Driver \
--username=postgres --password=postgres \
--changeLogFile=db.changelog-master.yaml \
rollbackOneUpdate --deploymentId=4566576096