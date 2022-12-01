# board-server
이 레포지토리는 boribori 서비스의 board server 레포지토리입니다.

## How To Contribute?

1. Fork this Repository
2. Add Issue on this repository
3. Typing Code
4. Create Pull & Request
5. Merge 🤗 

## 기능

1. 댓글 기능
2. 책 게시글 기능
3. 책 검색 기능

# 참고 Wiki
* [✅ Board Server Wiki Page](https://github.com/Bori-Bori/board-server/wiki)

# Server Architecture

<img width="714" alt="image" src="https://user-images.githubusercontent.com/79268661/200160923-1ffa1825-14af-4b47-aaff-f56d121148f3.png">

# Server Stack

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










