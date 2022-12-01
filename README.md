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

<img width="623" alt="스크린샷 2022-12-01 오후 5 34 30" src="https://user-images.githubusercontent.com/79268661/205018381-b6429592-fdde-4427-8af4-5579bcd40873.png">

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










