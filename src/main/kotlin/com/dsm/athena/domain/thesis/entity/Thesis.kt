package com.dsm.athena.domain.thesis.entity

import com.dsm.athena.domain.article.entity.Article
import com.dsm.athena.domain.recruit.entity.Recruit
import com.dsm.athena.domain.vote.entity.Vote
import org.hibernate.annotations.DynamicUpdate
import org.springframework.data.annotation.CreatedDate
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@DynamicUpdate
@Table(name = "tbl_thesis")
data class Thesis(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?,

    @Column(name = "content", nullable = false, updatable = false, unique = true, length = 23)
    var content: String,

    @Column(name = "is_selected", columnDefinition = "BIT(1) default 0", nullable = false)
    var isSelected: Boolean,

    @ManyToOne(optional = false)
    @JoinColumn(name = "recruit_id", nullable = false, insertable = true, updatable = false)
    var recruit: Recruit,

    @OneToMany(mappedBy = "thesis", cascade = [CascadeType.ALL])
    var voteList: MutableList<Vote> = mutableListOf(),

    @OneToMany(mappedBy = "thesis", cascade = [CascadeType.REMOVE])
    var articleList: MutableList<Article> = mutableListOf(),

    @Column(name = "vote_count", nullable = false, columnDefinition = "INT default 0")
    var voteCount: Int,

    @Column(name = "create_at", nullable = false, updatable = false)
    var createAt: LocalDateTime = LocalDateTime.now()
) {

    fun vote(vote: Vote) {
        voteList.add(vote)
        this.voteCount++
    }

    fun unVote() {
        this.voteCount--
    }
}