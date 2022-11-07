package com.boribori.boardserver.reply

import com.boribori.boardserver.auth.dto.AuthUser
import com.boribori.boardserver.comment.CommentService
import com.boribori.boardserver.reply.dto.RequestOfCreateReply
import com.boribori.boardserver.reply.dto.ResponseOfCreateReply
import org.springframework.stereotype.Service

@Service
class ReplyService (
        private val replyRepository: ReplyRepository,
        private val commentService: CommentService
        ){

    fun createReply(commentId: String, authUser: AuthUser, requestOfCreateReply : RequestOfCreateReply): ResponseOfCreateReply {
        var commentEntity = commentService.getCommentEntity(commentId)

        var replyEntity = replyRepository.save(Reply(
                content = requestOfCreateReply.content,
                comment = commentEntity,
                writer = authUser.username
        ))

        return ResponseOfCreateReply(
                writer = replyEntity.writer,
                createdAt = replyEntity.createdAt,
                reply = replyEntity.content
        )
    }
}