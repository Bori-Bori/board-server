package com.boribori.boardserver.board


import javax.persistence.*


@Entity
class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?= null;

}