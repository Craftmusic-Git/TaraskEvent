@echo off

REM launch the compose file
docker-compose up -d

REM add the admin account on keycloak
docker exec local_keycloak /opt/jboss/keycloak/bin/add-user-keycloak.sh -u admin -p admin && docker restart local_keycloak