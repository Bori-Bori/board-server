package com.boribori.boardserver.board

import com.boribori.boardserver.board.QBoard.board
import com.boribori.boardserver.board.dto.request.RequestOfSearchBoards
import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.domain.Pageable


class BoardCustomRepositoryImpl(
        private val queryFactory: JPAQueryFactory
) : BoardCustomRepository {
    override fun searchAllBoards(requestOfSearchBooks: RequestOfSearchBoards, pageable: Pageable): MutableList<Board> {
        println("asdfasdf = " + pageable.offset)
        return queryFactory.selectFrom(board)
                .leftJoin(board.commentList).fetchJoin()
                .where(
                    checkQueryType(requestOfSearchBooks.queryType, requestOfSearchBooks.keyword!!),
                        eqCategory1(requestOfSearchBooks.category1),
                        eqCategory2(requestOfSearchBooks.category2),
                        eqCategory3(requestOfSearchBooks.category3)
                )
                .offset(pageable.offset)
                .limit(pageable.pageSize.toLong())
                .fetch()

    }

    private fun containTitle(title : String?) : BooleanExpression{
        title?:return board.title.contains("")
        return board.title.contains(title)
    }

    private fun eqCategory1(category1 : String?) : BooleanExpression{
        category1?:return board.category1.contains("")
        return board.category1.contains(category1)
    }

    private fun eqCategory2(category2 : String?) : BooleanExpression{
        category2?:return board.category2.contains("")
        return board.category2.contains(category2)
    }

    private fun eqCategory3(category3 : String?) : BooleanExpression{
        category3?: board.category3.contains("")
        return board.category3.contains(category3)
    }

    private fun containsAuthor(author : String) : BooleanExpression{
        return board.author.contains(author)
    }

    private fun containTitleAndAuthor(query : String) : BooleanExpression{
        return board.title.contains(query).or(board.author.contains(query))
    }

    private fun containsPublisher(publisher : String): BooleanExpression{
        return board.publisher.contains(publisher)
    }

    private fun checkQueryType(queryType: String?, query: String) : BooleanExpression{
        queryType?: return containTitleAndAuthor(query)
        if(queryType == ""){
            return containTitleAndAuthor(query)
        }

        if(queryType == "title"){
            return containTitle(query)
        }

        if(queryType == "author"){
            return containsAuthor(query)
        }

        if(queryType == "publisher"){
            return containsPublisher(query)
        }

        if(queryType == "category"){
            return searchCategory(query)
        }

        return containTitleAndAuthor(query)
    }
    private fun searchCategory(category: String?): BooleanExpression{
        category?: return board.category1.contains("");
        return board.category1.contains("").or(board.category2.contains("")).or(board.category2.contains(""))
    }
}