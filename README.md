# Role System

### SET UP
```
1 git clone https://github.com/knayamlohani/role-system.git
2 cd role-system
3 mvn clean install
4 java -jar target/role-system-1.0.0.jar
```



### APIs

1 -  add user
```

curl --location --request POST 'http://localhost:9191/role-system/api/v1/user' \
--header 'Content-Type: application/json' \
--data-raw '{
    "username": "a"
}'

```
only username is mandatory


2 - add role to user with user-name a
```
curl --location --request POST 'http://localhost:9191/role-system/api/v1/user/a/role/DELETE_ROLE'
curl --location --request POST 'http://localhost:9191/role-system/api/v1/user/a/role/READ_ROLE'
curl --location --request POST 'http://localhost:9191/role-system/api/v1/user/a/role/WRITE_ROLE'

```

3 - write resource 
```

curl --location --request POST 'http://localhost:9191/role-system/api/v1/resource' \
--header 'Content-Type: application/json' \
--data-raw '{
    "resource": {
        "content": "billa123"
    },
    "actionType": "WRITE",
    "username": "a"
}'

```
4 - read resource
```

curl --location --request POST 'http://localhost:9191/role-system/api/v1/resource' \
--header 'Content-Type: application/json' \
--data-raw '{
    "resource": {
        "resourceId": "ce675b9f-246c-11eb-8848-7154282772c4"
    },
    "actionType": "READ",
    "username": "a"
}'
```

5 -  delete resource
```

curl --location --request POST 'http://localhost:9191/role-system/api/v1/resource' \
--header 'Content-Type: application/json' \
--data-raw '{
    "resource": {
        "resourceId": "dff3eed8-246e-11eb-a103-03186168d302"
    },
    "actionType": "DELETE",
    "username": "a"
}'

```

6 - get all resources

```

curl --location --request GET 'http://localhost:9191/role-system/api/v1/resources' \
--header 'Content-Type: application/json' \
--data-raw '{
    "username": "a"
}'


```

### Controllers

* UserController
* ResourceController


### Services

* UserService
* UserValidatorService
* UserHelperService
* InMemoryDataService
* ExceptionHelperService
* ResourceService
* ResourceValidatorService


### Aspects

* ExceptionHandler


### all the data is kept in memory and is lost on application restart
