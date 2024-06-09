package com.dsm.athena.domain.article.entity

import com.dsm.athena.domain.thesis.entity.Thesis
import com.dsm.athena.domain.user.entity.User
import org.springframework.data.domain.Persistable
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "tbl_article")
data class Article(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @get:JvmName("getIdByJVM")
    val id: Long?,

    @Column(name = "title", nullable = false, length = 30)
    var title: String,

    @Column(name = "content", nullable = false, length = 10000)
    var content: String,

    @Column(name = "thumbnail_url", nullable = true)
    var thumbnailUrl: String?,

    @ManyToOne(optional = false)
    @JoinColumn(name = "writer_id", nullable = false, updatable = false)
    var writer: User,

    @ManyToOne(optional = false)
    @JoinColumn(name = "thesis_id", nullable = false, updatable = false)
    val thesis: Thesis,

    @Column(name = "like_count", nullable = false)
    var likeCount: Long = 0,

    @Column(name = "comment_count", nullable = false)
    var commentCount: Long = 0,

    @Column(name = "created_at", nullable = false)
    var createdAt: LocalDateTime
): Persistable<Long?> {

    override fun getId(): Long? = this.id

    override fun isNew(): Boolean = this.id == null
}