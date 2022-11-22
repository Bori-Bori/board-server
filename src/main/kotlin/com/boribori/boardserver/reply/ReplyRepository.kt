package com.boribori.boardserver.reply


import com.boribori.boardserver.comment.Comment
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface ReplyRepository : JpaRepository<Reply, String>{

    fun findByComment(comment: Comment, pageable: Pageable) : Page<Reply>

    fun findAllByUserId(userId: String): MutableList<Reply>?

}