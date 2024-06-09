package com.dsm.athena.domain.thesis.repository

import com.dsm.athena.domain.thesis.entity.Thesis
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ThesisRepository: CrudRepository<Thesis, Long?> {

}