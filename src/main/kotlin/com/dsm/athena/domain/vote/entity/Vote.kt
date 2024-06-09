package com.dsm.athena.domain.vote.entity

import com.dsm.athena.domain.recruit.entity.Recruit
import com.dsm.athena.domain.thesis.entity.Thesis
import com.dsm.athena.domain.user.entity.User
import javax.persistence.*

@Entity(name = "tbl_vote")
data class Vote(
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long?,

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    var user: User,

    @ManyToOne(optional = false)
    @JoinColumn(name = "thesis_id", nullable = false, updatable = false)
    var thesis: Thesis,

    @ManyToOne(optional = false)
    @JoinColumn(name = "recruit_id", nullable = false, updatable = false)
    var recruit: Recruit
) {
}