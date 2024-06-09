package com.dsm.athena.domain.recruit.repository

import com.dsm.athena.domain.recruit.entity.Recruit
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface RecruitRepository: CrudRepository<Recruit, Long?> {


}