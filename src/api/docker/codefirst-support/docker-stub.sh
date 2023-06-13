#!/bin/bash

docker cp stub/feedbacks.json codefirst-support-mongo:/tmp/feedbacks.json
docker cp stub/issue_models.json codefirst-support-mongo:/tmp/issue_models.json
docker cp stub/issues.json codefirst-support-mongo:/tmp/issues.json
docker cp stub/surveys.json codefirst-support-mongo:/tmp/surveys.json

docker exec -it codefirst-support-mongo mongoimport --jsonArray --db codefirst-support --collection feedbacks --file /tmp/feedbacks.json
docker exec -it codefirst-support-mongo mongoimport --jsonArray --db codefirst-support --collection issue_models --file /tmp/issue_models.json
docker exec -it codefirst-support-mongo mongoimport --jsonArray --db codefirst-support --collection issues --file /tmp/issues.json
docker exec -it codefirst-support-mongo mongoimport --jsonArray --db codefirst-support --collection surveys --file /tmp/surveys.json