
# People API - Java
![Badge In Development](http://img.shields.io/static/v1?label=STATUS&message=IN%20DEVELOPMENT&color=blue&style=for-the-badge)  ![Badge Versão](https://img.shields.io/badge/VERSION-1.0.0-blue?style=for-the-badge)
![Badge Coverage](https://img.shields.io/badge/tests%20coverage-100%25-GREEN?style=for-the-badge)
![Badge Testes](https://img.shields.io/badge/tests-32%E2%9C%94-brightgreen?style=for-the-badge)

## Index

* [Description](#description)
* [Getting Started](#getting-started)
	* [Preparing environment](#preparing-environment)
* [Endpoints](#endpoints)
	* [Get all persons](#get-all-persons)
	* [Get one person](#get-one-person)
	* [Add person](#add-person)
	* [Update person](#update-person)
	* [Add address](#add-address)
	*  [Update address](#update-address)
* [Technologies](#technologies)
* [Author](#author)

## Description

The **People API** was developed for people management. Adding people and your addresses. 

## Getting Started

### Preparing environment
First you need to clone this repository typing the command below in your terminal:

    git clone https://github.com/dchueri/people.git

## Endpoints

### Get all persons
`localhost:8080/persons`

Method: **GET**

***Return example:***

Status: **200**

Body:
```json
[
	{
		"id": 1,
		"name": "Person 1",
		"birthDate": "1995-03-16"
	},
	{
		"id": 2,
		"name": "Person 2",
		"birthDate": "1995-03-16"
	},
	...
]
```
### Get one person
`localhost:8080/persons/{personId}`

Method: **GET**

***Return example:***

Status: **200**

Body:
```json
{
	"id": 1,
	"name": "Person 1",
	"birthDate": "1995-03-16",
	"adresses": [
		{
			"id":  1,
			"street": "Rua 08",
			"cep": "78210078",
			"number": "715",
			"town": "Cáceres-MT",
			"isMain": true
		},
		...
	]
}
```

### Add person
`localhost:8080/persons/{personId}`

Method: **POST**

***Request body example:***
```json
{
	"name": "New person",     // String
	"birthDate": "1995-03-16" // Date on format: YYYY-MM-DD
}
```

***Return example:***

Status: **201**

Body:
```json
{
	"id": 1,
	"name": "New person",
	"birthDate": "1995-03-16"
}
```

### Update person
`localhost:8080/persons/{personId}`

Method: **PUT**

***Request body example:***
```json
{
	"name": "Updated name",      // String
}
```

***Return example:***

Status: **200**

Body:
```json
{
	"id": 1,
	"name": "Updated name",
	"birthDate": "1995-03-16"
}
```

### Add address
`localhost:8080/persons/{personId}/addresses`

Method: **POST**

***Request body example:***
```json
{
	"street": "Rua 08",   // String
	"cep": "78210078",    // String
	"number": "734",      // String
	"town": "Cáceres-MT", // String
	"isMain": true,       // boolean
	"personId": 1         // Long
}
```

***Return example:***

Status: **201**

Body:
```json
{
	"id": 1,
	"street": "Rua 08",
	"cep": "78210078",
	"number": "734",
	"town": "Cáceres-MT",
	"isMain": true
}
```

> **IMPORTANT:** A person can have just one main address. When the user added a new address as the main address, your last main address is defined with `isMain = false`.

### Update address
`localhost:8080/persons/{personId}/addresses/{addressId}`

Method: **PUT**

***Request body example:***
```json
{
	"street": "Rua 07"  // String
}
```

***Return example***:

Status: **204**

Body:
```json
{
	"id": 1,
	"street": "Rua 07",
	"cep": "78210078",
	"number": "734",
	"town": "Cáceres-MT",
	"isMain": false
}
```
> **IMPORTANT:** A person can have just one main address. When the user updated a address as the main address, your last main address is defined with `isMain = false`.

### Delete address
`localhost:8080/persons/{personId}/addresses/{addressId}`

Method: **DELETE**

***Return example:***

Status: **204**

## Technologies

* `Java`
* `SpringBoot`
* `Maven`
* `JUnit`
* `Mockito`
* `H2`

## Author

| [<img src="https://avatars.githubusercontent.com/u/84249430?s=400&u=b789830e57ccc23a4d4d758542785461dd656b5f&v=4" width=115><br><sub>Diego  Chueri</sub>](https://github.com/dchueri) | 
| :---: |
