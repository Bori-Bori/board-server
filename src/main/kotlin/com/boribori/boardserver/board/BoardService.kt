package com.boribori.boardserver.board

import com.boribori.boardserver.board.dto.request.RequestOfGetBooks
import com.boribori.boardserver.board.dto.request.RequestOfSearchBoards
import com.boribori.boardserver.board.dto.response.ResponseOfGetBoard
import com.boribori.boardserver.board.dto.response.ResponseOfGetBooks
import com.boribori.boardserver.board.dto.response.ResponseOfSearchBoard
import com.boribori.boardserver.board.dto.response.ResponseOfSearchBoards
import com.boribori.boardserver.board.exception.NotFoundBoardException
import com.boribori.boardserver.common.ResponseOfGetBookContent
import com.boribori.boardserver.util.RequestUtil
import com.boribori.boardserver.util.dto.ResponseOfGetBook
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class BoardService (
        private val boardRepository: BoardRepository,
        private val requestUtil: RequestUtil,
        ){

        fun getBoard(isbn: String): ResponseOfGetBoard {

                var responseOfGetBook : ResponseOfGetBook = requestUtil.getIsbn(isbn)
                var content : ResponseOfGetBookContent? = responseOfGetBook.content
                        ?: throw NotFoundBoardException(msg = "해당 게시 글을 찾을 수 없습니다.")

                boardRepository.findByIsbn(isbn)
                        .let {


                                it?: run {
                                    var board = Board().of(content!!)
                                    boardRepository.save(board)
                                    return ResponseOfGetBoard().of(content)
                                }
                                return ResponseOfGetBoard().of(it)
                        }


        }

        fun getBoardEntity(isbn : String) : Board {
               return boardRepository.findByIsbn(isbn)?: throw NotFoundBoardException(msg = "해당하는 게시글을 찾을 수 없습니다.")
        }

        fun getBoards(requestOfSearchBooks: RequestOfSearchBoards, pageable: Pageable): ResponseOfSearchBoards {
                var boardList = boardRepository.searchAllBoards(requestOfSearchBooks, pageable)

                var boardListDto = mutableListOf<ResponseOfSearchBoard>()

                boardList.stream()
                        .forEach {
                                boardListDto.add(
                                        ResponseOfSearchBoard(
                                                title = it.title,
                                                author = it.author,
                                                isbn = it.isbn,
                                                commentCount = it.commentList.size,
                                                imagePath = it.imagePath
                                                )
                                )
                        }

            return afterTreatments(boardListDto, pageable, requestOfSearchBooks.keyword!!)


        }

    fun getBooks(requestOfSearchBooks: RequestOfGetBooks) : ResponseOfGetBooks {
        return requestUtil.searchBookList(requestOfSearchBooks)
    }

    private fun afterTreatments(boardList : MutableList<ResponseOfSearchBoard>, pageable: Pageable, query: String): ResponseOfSearchBoards{
        return ResponseOfSearchBoards().of(boardList, pageable, query)
    }

}