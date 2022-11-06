package com.boribori.boardserver.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import com.querydsl.jpa.impl.JPAQueryFactory

@Configuration
class QuerydslConfig(
        @PersistenceContext val em: EntityManager
) {
    @Bean
    fun jpaQueryFactory() = JPAQueryFactory(em)
}