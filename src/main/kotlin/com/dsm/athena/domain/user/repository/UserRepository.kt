package com.dsm.athena.domain.user.repository

import com.dsm.athena.domain.user.entity.User
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: CrudRepository<User, String> {
}