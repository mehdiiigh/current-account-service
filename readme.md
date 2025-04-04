# Current Account Service

Current Account Service Sample Project

## Table of Contents

- [Overview](#overview)
- [Endpoints](#endpoints)
    - [Create Current Account](#create-current-account)
    - [Get Customer Account Details](#get-account-details)
- [Sample Inputs and Outputs](#sample-inputs-and-outputs)
    - [Create Current Account](#create-current-account-sample)
    - [Get Customer Account Details](#get-account-details-sample)
- [Setup and Installation](#setup-and-installation)
- [Swagger API Document](#swagger-API-Document)

## Overview

This project is a sample service for create current accounts. It demonstrates how to create and retrieve account information using RESTful APIs. The service is built using Kotlin.

## Endpoints

### Create Current Account
- **URL:** `/api/current-account`
- **Method:** `POST`
- **Description:** Creates new current account.

### Get Account Details
- **URL:** `/api/current-account/{customerId}`
- **Method:** `GET`
- **Description:** Retrieves details of an accounts and transactions.

## Sample Inputs and Outputs

### Create Current Account Sample

**Request:**
```json
{
  "customerId": 1,
  "initialCredit": 200
}
```
**Response:**

- In case of success status code is 201
- In case of validation error occurred status code is 400
  ```json
  {
      "status": 400,
      "message": "The initial value should be positive"
  }
  ```
- In case of customer not found status code is 404
    ```json
  {
      "status": 404,
      "message": "Customer 50 Not Found!"
  }
  ```

### Get Account Details Sample
**Request:**
```curl
GET /api/current-account/1
```
**Response:**
- In case of success status code is 200
  ```json
  {
    "customer": {
      "id": 1,
      "name": "John",
      "surname": "Smith"
    },
    "accounts": [
      {
        "id": 1,
        "balance": 200.0,
        "transactions": [
          {
            "amount": 200.0,
            "date": "2025-04-04 16:45:18"
          }
        ]
      }
    ]
  }
  ```
- In case of customer not found status code is 404
    ```json
  {
      "status": 404,
      "message": "Customer 50 Not Found!"
  }
  ```

### Setup and Installation
Clone the repository:
```sh
git clone https://github.com/mehdiiigh/current-account-service.git
```
Navigate to the project directory:
```sh
cd current-account-service
```
Build the project with Maven:
```sh
mvn clean install
```
Run the application:
```sh
mvn spring-boot:run
```

### Swagger API Document
Swagger UI also available in this url:
```
http://localhost:8080/swagger-ui/index.html
```