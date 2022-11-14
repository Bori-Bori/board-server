package com.boribori.boardserver.board.redis

import com.boribori.boardserver.board.Board
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface BoardRepositoryRedis : CrudRepository<BoardCached, String>{
}