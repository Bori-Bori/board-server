package com.boribori.boardserver.reply

import com.boribori.boardserver.board.Board
import com.boribori.boardserver.reply.auditing.ReplyBaseEntity
import lombok.Builder
import java.util.UUID
import javax.persistence.*

@Builder
@Entity
class Reply: ReplyBaseEntity() {

    @Id
    private var id: String? = UUID.randomUUID().toString();

    @Column
    private var content: String? = null;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board")
    private var board: Board? = null;


}