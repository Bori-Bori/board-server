package com.boribori.boardserver.util

import com.boribori.boardserver.util.dto.AladinCategory
import org.springframework.stereotype.Component

@Component
class ParsingUtil {

    fun getCategoryFromAladin(res : String):AladinCategory{

        var categoryList = res.split(">")
        var category : AladinCategory = AladinCategory(category1 = categoryList.get(0),
        category2 = categoryList.get(1),
        category3 = categoryList.get(2))

        return category
    }
}