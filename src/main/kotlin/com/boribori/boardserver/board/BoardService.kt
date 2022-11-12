package com.boribori.boardserver.board

import com.boribori.boardserver.board.dto.request.RequestOfGetBooks
import com.boribori.boardserver.board.dto.request.RequestOfSearchBooks
import com.boribori.boardserver.board.dto.response.ResponseOfGetBoard
import com.boribori.boardserver.board.dto.response.ResponseOfSearchBoard
import com.boribori.boardserver.board.dto.response.ResponseOfSearchBoards
import com.boribori.boardserver.board.exception.NotFoundBoardException
import com.boribori.boardserver.common.Content
import com.boribori.boardserver.util.RequestUtil
import com.boribori.boardserver.util.dto.ResponseOfGetBook
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Slice
import org.springframework.data.domain.SliceImpl
import org.springframework.stereotype.Service
import kotlin.RuntimeException

@Service
class BoardService (
        private val boardRepository: BoardRepository,
        private val requestUtil: RequestUtil,
        ){

        fun getBoard(isbn: String): ResponseOfGetBoard {

                var responseOfGetBook : ResponseOfGetBook = requestUtil.getIsbn(isbn)
                var content : Content? = responseOfGetBook.content

                boardRepository.findByIsbn(isbn)
                        .let {

                                if(it != null){
                                        return ResponseOfGetBoard(
                                                isbn = isbn,
                                                author = content?.author,
                                                pubDate = content?.pubDate,
                                                title = content?.title,
                                                category1 = content?.category1,
                                                category2 = content?.category2,
                                                category3 = content?.category3,
                                                description = content?.description,
                                                publisher = content?.publisher
                                        )
                                }

                                var board = Board(
                                isbn = isbn,
                                author = content?.author,
                                publisher = content?.publisher,
                                pubDate = content?.pubDate,
                                title = content?.title,
                                category1 = content?.category1,
                                category2 = content?.category2,
                                category3 = content?.category3,
                                commentList = mutableListOf()
                        );

                                boardRepository.save(board)
                                return ResponseOfGetBoard(
                                        isbn = isbn,
                                        author = content?.author,
                                        pubDate = content?.pubDate,
                                        title = content?.title,
                                        category1 = content?.category1,
                                        category2 = content?.category2,
                                        category3 = content?.category3,
                                        description = content?.description,
                                        publisher = content?.publisher
                                )

                        }


        }

        fun getBoardEntity(isbn : String) : Board {
               return boardRepository.findByIsbn(isbn)?: throw NotFoundBoardException(msg = "해당하는 게시글을 찾을 수 없습니다.")
        }

        fun searchBoards(requestOfSearchBooks: RequestOfSearchBooks, pageable: Pageable): ResponseOfSearchBoards {
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

    private fun afterTreatments(boardList : MutableList<ResponseOfSearchBoard>, pageable: Pageable, query: String): ResponseOfSearchBoards{
        var hasNext = true;
        if(boardList.size < pageable.pageSize){
            hasNext = false;
        }

        return ResponseOfSearchBoards().of(boardList, pageable, query)

    }

}