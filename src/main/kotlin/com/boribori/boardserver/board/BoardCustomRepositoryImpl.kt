package com.boribori.boardserver.board

import com.boribori.boardserver.board.QBoard.board
import com.boribori.boardserver.board.dto.request.RequestOfSearchBooks
import com.querydsl.core.types.Order
import com.querydsl.core.types.OrderSpecifier
import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Slice
import org.springframework.data.domain.SliceImpl


class BoardCustomRepositoryImpl(
        private val queryFactory: JPAQueryFactory
) : BoardCustomRepository {
    override fun searchAllBoards(requestOfSearchBooks: RequestOfSearchBooks, pageable: Pageable): MutableList<Board> {
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
                .orderBy(OrderSpecifier(Order.DESC, board.viewCount))
                .fetch()

    }

    private fun containTitle(title : String?) : BooleanExpression{
        title?:return board.title.contains("")
        return board.title.contains(title)
    }

    private fun eqCategory1(category1 : String?) : BooleanExpression{
        category1?:return board.category1.eq("")
        return board.category1.eq(category1)
    }

    private fun eqCategory2(category2 : String?) : BooleanExpression{
        category2?:return board.category2.eq("")
        return board.category2.eq(category2)
    }

    private fun eqCategory3(category3 : String?) : BooleanExpression{
        category3?: board.category3.eq("")
        return board.category3.eq(category3)
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

    private fun afterTreatments(boardList : MutableList<Board>, pageable: Pageable): Slice<Board>{
        var hasNext = true;
        if(boardList.size < pageable.pageSize){
            hasNext = false;
        }
        return SliceImpl<Board>(boardList, pageable, hasNext)
    }
}