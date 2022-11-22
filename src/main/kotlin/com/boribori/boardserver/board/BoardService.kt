package com.boribori.boardserver.board

import com.boribori.boardserver.board.dto.request.RequestOfGetBooks
import com.boribori.boardserver.board.dto.request.RequestOfSearchBoards
import com.boribori.boardserver.board.dto.response.ResponseOfGetBoard
import com.boribori.boardserver.board.dto.response.ResponseOfGetBooks
import com.boribori.boardserver.board.dto.response.ResponseOfSearchBoard
import com.boribori.boardserver.board.dto.response.ResponseOfSearchBoards
import com.boribori.boardserver.board.exception.NotFoundBoardException
import com.boribori.boardserver.util.RequestUtil
import org.springframework.cache.annotation.CachePut
import org.springframework.cache.annotation.Cacheable
import org.springframework.context.ApplicationEventPublisher
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BoardService (
        private val boardRepository: BoardRepository,
        private val requestUtil: RequestUtil
        ){


        @Cacheable(value = ["board"], key="#isbn")
        fun getBoard(isbn: String): ResponseOfGetBoard {
            var result = getBoardDto(isbn)
            return result
        }


        fun getBoardDto(isbn: String): ResponseOfGetBoard{
            var responseOfGetBook  = requestUtil.getIsbn(isbn)
            var content  = responseOfGetBook.content
                    ?: throw NotFoundBoardException(msg = "해당 게시 글을 찾을 수 없습니다.")

            BoardEntity(isbn)
                    .let {
                        it?: run {
                            var board = Board().of(content!!)
                            boardRepository.save(board)
                            return ResponseOfGetBoard().of(content)
                        }

                        return ResponseOfGetBoard().of(content)
                    }

        }


        fun BoardEntity(isbn : String): Board?{
            return boardRepository.findByIsbn(isbn)
        }

        fun getBoardEntity(isbn : String) : Board {
               return boardRepository.findByIsbn(isbn)?: throw NotFoundBoardException(msg = "해당하는 게시글을 찾을 수 없습니다.")
        }
        fun getBoardEntity2(isbn : String) : Board {
        return boardRepository.findByIsbnFetch(isbn)?: throw NotFoundBoardException(msg = "해당하는 게시글을 찾을 수 없습니다.")
    }

        fun saveBoardEntity(board : Board){
            boardRepository.save(board)
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