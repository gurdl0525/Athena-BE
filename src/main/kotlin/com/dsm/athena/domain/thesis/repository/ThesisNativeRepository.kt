package com.dsm.athena.domain.thesis.repository

import com.dsm.athena.domain.thesis.entity.Thesis
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.Repository
import org.springframework.data.repository.query.Param

@org.springframework.stereotype.Repository
interface ThesisNativeRepository: Repository<Thesis, Long?> {

    @Query(
        "select * from tbl_thesis as t where t.is_selected = true ORDER BY t.create_at desc limit 1 offset :cursor",
        nativeQuery = true
    )
    fun findByCursor(@Param("cursor") cursor: Long): Thesis?
}