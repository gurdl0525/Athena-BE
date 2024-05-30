package com.dsm.athena.domain.thesis

import com.dsm.athena.domain.recruit.entity.Recruit
import org.hibernate.annotations.DynamicUpdate
import javax.persistence.*

@Entity
@DynamicUpdate
@Table(name = "tbl_thesis")
data class Thesis(
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long?,

    @Column(name = "content", nullable = false, updatable = false, unique = true, length = 23)
    var content: String,

    @Column(name = "is_selected", columnDefinition = "BIT(1) default 0", nullable = false)
    var isSelected: Boolean,

    @ManyToOne(optional = false)
    @JoinColumn(name = "recruit_id", nullable = false, insertable = true, updatable = false)
    var recruit: Recruit,

    @Column(name = "vote_count", nullable = false, columnDefinition = "INT default 0")
    var voteCount: Int
) {

    fun vote() {
        this.voteCount++
    }

    fun unVote() {
        this.voteCount--
    }
}