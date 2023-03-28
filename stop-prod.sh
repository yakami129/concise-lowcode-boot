#!/bin/bash
docker-compose -f docker-compose-start-prod.yaml stop && docker-compose -f docker-compose-start-prod.yaml rm -f