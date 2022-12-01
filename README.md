# board-server
이 레포지토리는 boribori 서비스의 board server 레포지토리입니다.

## How To Contribute?

1. Fork this Repository
2. Add Issue on this repository
3. Typing Code
4. Create Pull & Request
5. Merge 🤗 

## How To Run?

1. Git Clone
2. Turn On your MySql
3. Create DB
4. Turn On your Redis
5. Turn On your zookeeper
6. Turn On your Kafka
7. move directory to /board-server
8. type code

```bash
$ ./gradlew build
```

9. move to ./build/libs
10. run jar file

```bash
$ java -jar ~.jar
```

or To Run Background

```bash
$ nohup java -jar ~.jar & /dev/null
```

## 기능

1. 댓글 기능
2. 책 게시글 기능
3. 책 검색 기능

# 참고 Wiki
* [✅ Board Server Wiki Page](https://github.com/Bori-Bori/board-server/wiki)

# Server Architecture

<img width="623" alt="스크린샷 2022-12-01 오후 5 34 30" src="https://user-images.githubusercontent.com/79268661/205018381-b6429592-fdde-4427-8af4-5579bcd40873.png">

# Server Stack

## Language
Kotlin

## Web Framework

* Web Framework : Spring(Boot)

* Web MVC library : Spring Web MVC

## DB

* DataBase : MySQl 8.0.30 (InnoDB)

* DataBase Library : JDBC, Spring Data JPA, QueryDsl

## InMemory DB 

* InMemory : Redis latest

## Authentication & Authorization

* Security : Spring Security 
* JWT
* JWT Library : io.jsonwebtoken:jjwt-api:0.11.2

## Event Queue

* zookeeper
* Kafka
* Topics : profile, reply

## Build Tool
* Gradle 7.5.1










