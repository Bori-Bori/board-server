package com.boribori.boardserver.comment

import com.boribori.boardserver.board.Board
import com.boribori.boardserver.board.dto.response.ResponseOfGetBoard
import com.boribori.boardserver.comment.auditing.CommentBaseEntity
import com.boribori.boardserver.reply.Reply
import lombok.Builder
import java.util.UUID
import javax.persistence.*

@Builder
@Entity
class Comment(
        @Id
        @Builder.Default()
        var id: String = UUID.randomUUID().toString(),

        @OneToMany(cascade = [(CascadeType.ALL)], fetch = FetchType.LAZY, mappedBy = "comment")
        var replyList: MutableList<Reply> = mutableListOf<Reply>(),

        @Column
        var content: String,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "BOARD_ID")
        var board: Board,

        @Column
        var username: String,

        @Column
        var userId : String,

        @Column
        var page: Int

): CommentBaseEntity() {



}