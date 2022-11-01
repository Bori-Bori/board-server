package com.boribori.boardserver.reply

import com.boribori.boardserver.reply.auditing.ReplyBaseEntity
import lombok.Builder
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Builder
@Entity
class Reply: ReplyBaseEntity() {

    @Id
    private var id: String? = UUID.randomUUID().toString();

    @Column
    private var content: String? = null;


}