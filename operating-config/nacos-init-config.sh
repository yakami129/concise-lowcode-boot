#!/bin/sh
NS_PATH="/nacos/v1/console/namespaces"
NACOS_ADDRESS=${NACOS}:${NACOS_PORT}
PROTOCAL="http"
NS_ENDPOINT=${PROTOCAL}://${NACOS_ADDRESS}${NS_PATH}
NAMESPACE=${NACOS_NS_PREFIX}-${RUN_ENV}
ns_exist=0

# shellcheck disable=SC2112
function check_ns_exist() {
  REMOTE_NS=$(curl -X GET "${NS_ENDPOINT}" | jq '.data[] | .namespace')
  if [[ ! "${REMOTE_NS[*]}" =~ ${NAMESPACE} ]];
  then
    ns_exist=0
  else
    ns_exist=1
  fi
}

# shellcheck disable=SC2112
function create_namespace() {
  if [[ $ns_exist == 0 ]]
  then
    curl -X POST "${NS_ENDPOINT}" \
           -d "customNamespaceId=${NAMESPACE}&namespaceName=${NAMESPACE}&namespaceDesc=${NAMESPACE}"
  else
    echo "${NAMESPACE} already exist in nacos server."
  fi
}

# shellcheck disable=SC2112
function import_configs() {
  CONFIG_PATH="/nacos/v1/cs/configs"
  CONFIG_ENDPOINT=${PROTOCAL}://${NACOS_ADDRESS}${CONFIG_PATH}
  BASE_DIR=$(cd `dirname $0` && pwd)
  IFS=$'\n'
  CONFIG_FILES=($(find $BASE_DIR -type f -name '*.yaml'))
  unset IFS
  for i in "${CONFIG_FILES[@]}"
  do
    import_config "$i"
  done
}

# shellcheck disable=SC2112
function import_config() {
  echo -e "import $1 to $CONFIG_ENDPOINT \n"
  curl -X POST "${CONFIG_ENDPOINT}" --header 'Content-Type: application/x-www-form-urlencoded' \
        -d tenant=${NAMESPACE} \
        -d type=yaml \
        -d dataId=$(basename -- "$1") \
        -d group=${NACOS_GROUP^^} \
        --data-urlencode content@$1
  echo -e "\n"
}
check_ns_exist
create_namespace
import_configs
