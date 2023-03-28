#!/bin/sh

export FLYWAY_USER=${MYSQL_USER}
export FLYWAY_CONNECT_RETRIES=5
export FLYWAY_PASSWORD=${MYSQL_PWD}
export FLYWAY_BASELINE_ON_MIGRATE="true"
export FLYWAY_PLACEHOLDER_REPLACEMENT="false"
export FLYWAY_LOCATIONS="filesystem:sql/*/"
export FLYWAY_SCHEMAS="FLYWAY_MANAGER_${PRODUCT_SHORT_NAME}"
export FLYWAY_URL="jdbc:mysql://${MYSQL_HOST}:${MYSQL_PORT}?serverTimezone=Asia/Shanghai&useSSL=false&maxAllowedPacket=1073741824"

echo "FLYWAY_USER: ${FLYWAY_USER}"
echo "FLYWAY_LOCATIONS: ${FLYWAY_LOCATIONS}"
echo "FLYWAY_SCHEMAS: ${FLYWAY_SCHEMAS}"
echo "FLYWAY_URL: ${FLYWAY_URL}"

if [ -z ${FLYWAY_COMMAND} ]; then
  echo "flyway migrate -community"
  exec flyway migrate -community
else
  echo "flyway ${FLYWAY_COMMAND} -community"
  exec flyway ${FLYWAY_COMMAND} -community
fi
