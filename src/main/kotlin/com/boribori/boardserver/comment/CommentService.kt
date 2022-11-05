package com.boribori.boardserver.comment

import com.boribori.boardserver.auth.dto.AuthUser
import com.boribori.boardserver.board.BoardService
import com.boribori.boardserver.comment.dto.RequestOfCreateComment
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
}