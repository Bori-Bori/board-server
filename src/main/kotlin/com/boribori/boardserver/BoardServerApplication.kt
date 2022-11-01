package com.boribori.boardserver

import com.boribori.boardserver.board.Board
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class BoardServerApplication

fun main(args: Array<String>) {
	runApplication<BoardServerApplication>(*args)

}
