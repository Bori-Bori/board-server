package com.boribori.boardserver.comment

import com.boribori.boardserver.board.Board
import com.boribori.boardserver.comment.auditing.CommentBaseEntity
import com.boribori.boardserver.reply.Reply
import lombok.Builder
import java.util.UUID
import javax.persistence.*

@Builder
@Entity
class Comment: CommentBaseEntity() {

    @Id
    @Builder.Default()
    private var id: UUID? = UUID.randomUUID()

    @Column
    private var content: String? = null;

    @OneToMany(cascade = [(CascadeType.ALL)], fetch = FetchType.LAZY, mappedBy = "comment")
    private var replyList = mutableListOf<Reply>()

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board")
    private var board: Board? = null;

    @Column
    private var writer: String? = null


}