package com.boribori.boardserver.reply

import com.boribori.boardserver.comment.Comment
import lombok.Builder
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.util.*
import javax.persistence.*

@Builder
@Entity
@EntityListeners(AuditingEntityListener::class)
class Reply {
    @Id
    @Builder.Default()
    private var id: UUID? = UUID.randomUUID()

    @Column
    private var content: String? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reply")
    private var comment: Comment? = null

    @Column
    private var writer: String? = null

}