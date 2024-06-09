package com.dsm.athena.domain.recruit.entity

import com.dsm.athena.domain.thesis.entity.Thesis
import org.hibernate.annotations.DynamicUpdate
import java.time.LocalDate
import javax.persistence.*

@DynamicUpdate
@Entity(name = "tbl_recruit")
@Table(uniqueConstraints = [UniqueConstraint(
    name = "idx_start_at_end_at",
    columnNames = ["start_at", "end_at"])
])
data class Recruit (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,

    @Column(name = "start_at", nullable = false, updatable = false)
    val startAt: LocalDate,

    @Column(name = "end_at", nullable = false, updatable = false)
    val endAt: LocalDate,

    @Column(name = "is_closed", nullable = false, columnDefinition = "BIT(1) default 0")
    var isClosed: Boolean,

    @Column(name = "total_count", nullable = false, columnDefinition = "BIGINT default 0")
    var totalCount: Int,

    @OneToMany(mappedBy = "recruit")
    val theses: MutableList<Thesis> = mutableListOf()
)