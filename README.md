## How to deploy

### Locally
- harbor

### At ec2
- If the mysql isnt running:

```bash
docker run --name mysql -e MYSQL_DATABASE=auth -e MYSQL_ROOT_PASSWORD=<password> -v /mysql:/var/lib/mysql -d mysql/mysql-server --character-set-server=utf8 --collation-server=utf8_general_ci

```

- Pull the new version:

```bash
docker pull leocwolter/authentication
```

- Stop and remove the running container:

```
docker stop checkout
docker rm checkout
```

- Run the container:

```bash
docker run --name auth -p 8080:8080 --link mysql:mysql -d leocwolter/authentication
```


## How to setup

- Import the project as an Existing Maven Project at eclipse
- Import base.sql into your checkout database:

```bash
mysql -u root auth < base.sql
``` 

- Copy the application.properties.sample:

```bash
cp src/main/resources/application.properties.sample src/main/resources/application.properties
```

## How to run

- If you have the project imported, just run the class com.senzo.qettal.auth.AuthApplication
- If you don't, run 'mvn spring-boot:run' at your terminal

## How to test

 If you don't have an application to test with, use curl sending a json with the structure provided bellow:


## User

### How to create one

- Json template:

```json
{
	"name": "Leonardo",
	"email": "leo@leo.com",
	"password": "123"
}
```

Example: 

```bash
 curl -H "Content-Type:application/json" -X POST http://localhost:8083/users --data "{\"name\": \"Leonardo\", \"email\": \"leo@leo.com\", \"password\": \"123\"}"
```

Possible responses:

- 202 - User created
- 400 - Invalid or insufficient data


## Login

After you create an user, you have to log in to be able to access any other resources

## How to

Example:

```bash
curl -X POST -c /tmp/cookies.txt  http://localhost:8083/login -d email=leo@leo.com -d password=123
```

This will create a file with your cookies at /tmp/cookies.txt, you will need to send this file in each request to prove you are authenticated
