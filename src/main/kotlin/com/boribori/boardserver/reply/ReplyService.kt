package com.boribori.boardserver.reply

import com.boribori.boardserver.auth.dto.AuthUser
import com.boribori.boardserver.comment.CommentService
import com.boribori.boardserver.comment.dto.EventOfUpdateNickname
import com.boribori.boardserver.reply.dto.RequestOfCreateReply
import com.boribori.boardserver.reply.dto.ResponseOfCreateReply
import com.boribori.boardserver.reply.dto.ResponseOfGetReply
import com.boribori.boardserver.reply.dto.ResponseOfGetReplyList
import org.springframework.context.ApplicationEventPublisher
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class ReplyService (
        private val replyRepository: ReplyRepository,
        private val commentService: CommentService,
        private val eventPublisher: ApplicationEventPublisher
        ){

    fun createReply(commentId: String, authUser: AuthUser, requestOfCreateReply : RequestOfCreateReply): ResponseOfCreateReply {
        var commentEntity = commentService.getCommentEntity(commentId)

        var replyEntity = replyRepository.save(Reply(
                content = requestOfCreateReply.content,
                comment = commentEntity,
                userNickname = authUser.username,
                userId = authUser.id

        ))
        eventPublisher.publishEvent(replyEntity)
        return ResponseOfCreateReply(
                userNickname = replyEntity.userNickname,
                userId = commentEntity.userId,
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
                    userId = v.userId,
                    userNickname = v.userNickname,
                    createdAt = v.createdAt
            ))
        }

        return ResponseOfGetReplyList(
                items = replyList,
                currentPage = replyListPage.number,
                size = replyListPage.size
        )

    }


    fun updateNickname(eventOfUpdateNickname: EventOfUpdateNickname){
        var replyList = replyRepository.findAllByUserId(eventOfUpdateNickname.id)
                ?: throw RuntimeException("해당하는 댓글을 찾지 못하였습니다.")
        replyList.map{
            it.updateNickname(eventOfUpdateNickname.nickname)
        }

        replyRepository.saveAll(replyList)

    }
}