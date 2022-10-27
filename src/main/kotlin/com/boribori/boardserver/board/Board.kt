package com.boribori.boardserver.board


import lombok.Builder
import lombok.Getter
import java.util.*
import javax.persistence.*


@Getter
@Builder
@Entity
class Board {

    @Id
    private val id: UUID = UUID.randomUUID();

    @Column
    private var title: String? = null;



}