# HMPPS DPR Fake DPS Service
[![repo standards badge](https://img.shields.io/badge/dynamic/json?color=blue&style=flat&logo=github&label=MoJ%20Compliant&query=%24.result&url=https%3A%2F%2Foperations-engineering-reports.cloud-platform.service.justice.gov.uk%2Fapi%2Fv1%2Fcompliant_public_repositories%2Fhmpps-dpr-fake-dps-service)](https://operations-engineering-reports.cloud-platform.service.justice.gov.uk/public-github-repositories.html#hmpps-dpr-fake-dps-service "Link to report")
[![CircleCI](https://circleci.com/gh/ministryofjustice/hmpps-dpr-fake-dps-service/tree/main.svg?style=svg)](https://circleci.com/gh/ministryofjustice/hmpps-dpr-fake-dps-service)
[![Docker Repository on Quay](https://quay.io/repository/hmpps/hmpps-dpr-fake-dps-service/status "Docker Repository on Quay")](https://quay.io/repository/hmpps/hmpps-dpr-fake-dps-service)
[![API docs](https://img.shields.io/badge/API_docs_-view-85EA2D.svg?logo=swagger)](https://hmpps-dpr-fake-dps-service.hmpps.service.justice.gov.uk/swagger-ui/index.html)

## Purpose

This service has been created to test the process of importing data into the Digital Prisons Reporting project (in the Modernisation Platform) from an external DPS service hosted in the Cloud Platform Environment.

Initially it will be used to verify the PostgreSQL settings needed for data import. In future, it will be used to enable automated tests of the import process.

## Deployment

This application is hosted in the Cloud Platform Environment in the [hmpps-dpr-fake-dps-service](https://github.com/ministryofjustice/cloud-platform-environments/tree/main/namespaces/live.cloud-platform.service.justice.gov.uk/hmpps-dpr-fake-dps-service) namespace.

It is deployed via CircleCI, enabled by the [DPS Bootstrap project](https://github.com/ministryofjustice/dps-project-bootstrap/blob/main/projects.json#L2219).

There is no environment-specific version of the service - it is just a single namespace.

## Using the service

The API can be accessed directly, or via the [Swagger UI](https://hmpps-dpr-fake-dps-service.hmpps.service.justice.gov.uk/swagger-ui/index.html).

The API is authenticated using an API token in the request header, called `x-api-token`.

This token can be found in the kubernetes secret `api-token`, within the namespace:

```shell
kubectl get secret -n hmpps-dpr-fake-dps-service api-token -o jsonpath='{.data.api_token}' | base64 -d && echo
```
