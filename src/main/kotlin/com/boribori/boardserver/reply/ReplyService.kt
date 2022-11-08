package com.boribori.boardserver.reply

import com.boribori.boardserver.auth.dto.AuthUser
import com.boribori.boardserver.comment.CommentService
import com.boribori.boardserver.reply.dto.RequestOfCreateReply
import com.boribori.boardserver.reply.dto.ResponseOfCreateReply
import com.boribori.boardserver.reply.dto.ResponseOfGetReply
import com.boribori.boardserver.reply.dto.ResponseOfGetReplyList
import org.springframework.data.domain.Pageable
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

    fun getReplyList(commentId: String, pageable: Pageable): ResponseOfGetReplyList{

        var replyListPage = replyRepository.findByComment(commentService.getCommentEntity(commentId), pageable)

        var replyList = mutableListOf<ResponseOfGetReply>()

        replyListPage.content.stream().forEach{v ->
            replyList.add(ResponseOfGetReply(
                    reply = v.content,
                    writer = v.writer,
                    createdAt = v.createdAt
            ))
        }

        return ResponseOfGetReplyList(
                items = replyList,
                currentPage = replyListPage.number,
                size = replyListPage.size
        )

    }
}