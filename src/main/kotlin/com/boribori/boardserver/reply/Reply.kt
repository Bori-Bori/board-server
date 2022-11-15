package com.boribori.boardserver.reply

import com.boribori.boardserver.comment.Comment
import com.boribori.boardserver.reply.auditing.ReplyBaseEntity
import lombok.Builder
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.util.*
import javax.persistence.*


@Entity
@EntityListeners(AuditingEntityListener::class)
class Reply (

        @Id
        @Builder.Default()
        var id: String = UUID.randomUUID().toString(),

        @Column
        var content: String,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "reply")
        var comment: Comment,

        @Column
        var userId: String,
        @Column
        var userNickname : String

        ) : ReplyBaseEntity() {

                fun updateNickname(nickname: String){
                        this.userNickname = nickname
                }

}