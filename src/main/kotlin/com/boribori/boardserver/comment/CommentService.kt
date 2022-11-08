package com.boribori.boardserver.comment

import com.boribori.boardserver.auth.dto.AuthUser
import com.boribori.boardserver.board.BoardService
import com.boribori.boardserver.comment.dto.RequestOfCreateComment
import com.boribori.boardserver.comment.dto.RequestOfGetComment
import com.boribori.boardserver.comment.dto.ResponseOfGetComment
import com.boribori.boardserver.comment.dto.ResponseOfGetCommentList
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Slice
import org.springframework.data.domain.Sort
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

    fun getComment(boardId: String, requestOfGetComment: RequestOfGetComment, pageable: Pageable): ResponseOfGetCommentList {
        var commentListPage: Slice<Comment>;
        var commentList = mutableListOf<ResponseOfGetComment>()
        if(requestOfGetComment.order == "page"){
            var pageable2 = PageRequest.of(pageable.pageNumber, pageable.pageSize, Sort.by("createdAt").descending())
            println("여기 - > " + requestOfGetComment.bookPage)
            if(requestOfGetComment.bookPage == "") requestOfGetComment.bookPage = "1"
            commentListPage = commentRepository.findByBoardAndPage(boardService.getBoardEntity(boardId), requestOfGetComment.bookPage.toInt(), pageable2)
        }else{
            var pageable2 = PageRequest.of(pageable.pageNumber, pageable.pageSize, Sort.by("createdAt").descending())
            commentListPage = commentRepository.findByBoard(boardService.getBoardEntity(boardId), pageable2)
        }

        commentListPage.content.stream().forEach{v ->
            commentList.add(ResponseOfGetComment(
                    id = v.id,
                    writer = v.username,
                    comment = v.content,
                    createdAt = v.createdAt,
                    replyNum = v.replyList.size,
                    page = v.page
            ))
        }
        //number = 현재 슬라이스 번호
        return ResponseOfGetCommentList(
                items = commentList,
                size = commentListPage.size,
                currentPage = commentListPage.number
        )

    }

    fun getCommentEntity(commentId: String): Comment{
        return commentRepository.findById(commentId).get()
    }
}