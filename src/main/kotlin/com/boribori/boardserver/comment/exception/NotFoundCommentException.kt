package com.boribori.boardserver.comment.exception

import java.lang.RuntimeException

class NotFoundCommentException(
        var msg : String
) : RuntimeException() {

}