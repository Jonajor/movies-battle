# Movies Battle
# Creating Rest APIs with Spring and H2

## Requirements

For building and running the application you need:

- [Maven](https://maven.apache.org/)
- [H2](https://www.h2database.com/html/main.html)

## Executing requests

[![Run in Postman](https://run.pstmn.io/button.svg)](https://www.getpostman.com/collections/a580ced61fd3755c863d)

## Exploring the Rest APIs

The application defines following REST APIs

```
1. POST /game/create - Create a game

2. POST /user - Create new user

3. POST /authenticate - Autenticate with user informations

4. GET /game/result/{movie_id}?user_id={user_id}&game_id={game_id} - Use thi endpoint to play the game, you wil need provide the movie_id that you think that have better avaliation.

5. GET /game/result/ - List the ranking

6. GET /game/finish/ - To end the game
```

## Steps to Setup

**1. Clone the application**

```bash
git clone https://github.com/Jonajor/movies-battle.git
cd movies-battle
```

**2. You can also build and run the application using maven**

***3.1. Database
In this project I used H2 in-memory, you can see h2 dashboard running [this link](http://localhost:8080/h2-console/) after to runner the application local.***

***3.2. Documentation
In this project I used OpenApi, you can see swagger running in [this link](http://localhost:8080/swagger-ui/index.html) after to runner the application local.***
You can see the documentation in [this link](https://movie-batle.herokuapp.com/swagger-ui/index.html)
and the file openapi.yaml is disponible in the project too. 

```

***3.2. Run the application using maven***
```bash
mvn spring-boot:run
```

The server will start at <http://localhost:8080>.

## Deploy
The application is hosted on [Heroku](https://id.heroku.com/login)

You can use the aplication through [this endpoint](https://movie-batle.herokuapp.com/swagger-ui/index.html)
```
1. POST https://movie-batle.herokuapp.com/game/create - Create a game

2. POST https://movie-batle.herokuapp.com/user - Create new user

3. POST https://movie-batle.herokuapp.com/authenticate - Autenticate with user informations

4. GET https://movie-batle.herokuapp.com/game/result/{movie_id}?user_id={user_id}&game_id={game_id} - Use thi endpoint to play the game, you wil need provide the movie_id that you think that have better avaliation.

5. GET https://movie-batle.herokuapp.com/game/result/ - List the ranking

6. GET https://movie-batle.herokuapp.com/game/finish/ - To end the game
```

## Running integration tests

The project also contains integration tests for all the Rest APIs. For running the integration tests, go to the root directory of the project and type `mvn test` in your terminal.
```shell
mvn test
```

## Built With

- [Java](https://www.java.com/pt-BR/) - Programming language
- [IntelliJ](https://www.jetbrains.com/idea/) - IDE
- [Spring](https://spring.io/) - Java Framework
- [Maven](https://maven.apache.org/) - Dependency Management
- [H2](https://www.h2database.com/html/main.html) - DataBase
- [Heroku](https://id.heroku.com/login) - Provedor Cloud
