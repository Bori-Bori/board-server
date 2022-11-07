package com.boribori.boardserver.comment

import com.boribori.boardserver.auth.dto.AuthUser
import com.boribori.boardserver.board.BoardService
import com.boribori.boardserver.comment.dto.RequestOfCreateComment
import com.boribori.boardserver.comment.dto.RequestOfGetComment
import com.boribori.boardserver.comment.dto.ResponseOfGetComment
import com.boribori.boardserver.comment.dto.ResponseOfGetCommentList
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service


@Service
class CommentService (
        private val commentRepository: CommentRepository,
        private val boardService: BoardService
        ){


    fun createComment(boardId: String, authUser: AuthUser, requestOfCreateComment: RequestOfCreateComment): Comment{
        var boardEntity = boardService.getBoardEntity(boardId)

        var comment = Comment(
                board = boardEntity,
                username = authUser.username,
                userId = authUser.id,
                content = requestOfCreateComment.content,
                page = requestOfCreateComment.page
        );
        return commentRepository.save(comment);
    }

    fun getComment(boardId: String, order: String, pageable: Pageable): ResponseOfGetCommentList {
        var commentListPage = commentRepository.findByBoard(boardService.getBoardEntity(boardId), pageable)

        var commentList = mutableListOf<ResponseOfGetComment>()
        commentListPage.content.stream().forEach{v ->
            commentList.add(ResponseOfGetComment(
                    id = v.id,
                    writer = v.username,
                    comment = v.content,
                    createdAt = v.createdAt,
                    replyNum = v.replyList.size
            ))
        }
        return ResponseOfGetCommentList(
                items = commentList,
                totalPage = commentListPage.totalPages,
                size = commentListPage.size,
                currentPage = commentListPage.number

        )

    }
}