package com.boribori.boardserver.board


import com.boribori.boardserver.comment.Comment
import java.time.LocalDate
import javax.persistence.*


@Entity
class Board ( @Id val isbn: String,
              @Column
              val title: String? = null,

              @Column
              val author: String? = null,

              @Column
              var pubDate: LocalDate? = null,

              @Column
              var category1: String? = null,

              @Column
              var category2: String? = null,

              @Column
              var category3: String? = null,

              @Column
              var publisher: String? = null,

              @OneToMany(cascade = [(CascadeType.ALL)], fetch = FetchType.LAZY, mappedBy = "board")
              var commentList: MutableList<Comment> = mutableListOf<Comment>()){




}