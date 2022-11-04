package com.boribori.boardserver.board


import com.boribori.boardserver.comment.Comment
import java.time.LocalDate
import javax.persistence.*


@Entity
class Board ( @Id val isbn: String,
              @Column
              private var title: String? = null,

              @Column
              private var author: String? = null,

              @Column
              private var pubDate: LocalDate? = null,

              @Column
              private var category1: String? = null,

              @Column
              private var category2: String? = null,

              @Column
              private var category3: String? = null,

              @Column
              private var publisher: String? = null,

              @OneToMany(cascade = [(CascadeType.ALL)], fetch = FetchType.LAZY, mappedBy = "board")
              private var commentList: MutableList<Comment> = mutableListOf<Comment>()){




}