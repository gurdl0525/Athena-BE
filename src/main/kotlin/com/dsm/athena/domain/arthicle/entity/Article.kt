package com.dsm.athena.domain.arthicle.entity

import com.dsm.athena.domain.user.entity.User
import org.springframework.data.domain.Persistable
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "tbl_article")
data class Article(
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @get:JvmName("getIdByJVM")
    val id: Long?,

    @Column(name = "content", nullable = false, length = 10000)
    var content: String,

    @Column(name = "thumbnail_url", nullable = false)
    var thumbnailUrl: String,

    @ManyToOne(optional = false)
    @JoinColumn(name = "writer", nullable = false, updatable = false)
    var writer: User,

    @Column(name = "like_count", nullable = false)
    var likeCount: Long,

    @Column(name = "comment_count", nullable = false)
    var commentCount: Long,

    @Column(name = "created_at", nullable = false)
    var createdAt: LocalDateTime
): Persistable<Long?> {

    override fun getId(): Long? = this.id

    override fun isNew(): Boolean = this.id == null
}