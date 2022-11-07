package com.boribori.boardserver.reply

import org.springframework.data.jpa.repository.JpaRepository

interface ReplyRepository : JpaRepository<Reply, String>{
}