package com.boribori.boardserver.reply.exception

import java.lang.RuntimeException

class NotFoundReplyException(
        var msg : String
) : RuntimeException() {
}