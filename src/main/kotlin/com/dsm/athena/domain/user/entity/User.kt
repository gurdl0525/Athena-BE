package com.dsm.athena.domain.user.entity

import com.dsm.athena.domain.article.entity.Article
import com.dsm.athena.domain.vote.entity.Vote
import javax.persistence.CascadeType
import org.hibernate.annotations.DynamicUpdate
import org.springframework.data.domain.Persistable
import javax.persistence.*
import kotlin.jvm.JvmName
import kotlin.jvm.Transient

@Entity
@DynamicUpdate
@Table(name = "tbl_user")
class User(
    @Id @get:JvmName("getIdByJVM")
    @Column(name = "id", nullable = false, length = 21)
    var id: String,

    @Column(name = "password", columnDefinition = "CHAR(60)", nullable = false)
    var password: String,

    @Column(name = "profile_image", nullable = true, length = 255)
    var profileImage: String? = null,

    @OneToMany(mappedBy = "writer", cascade = [CascadeType.REMOVE])
    val articleList: MutableList<Article> = mutableListOf(),

    @OneToMany(mappedBy = "user", cascade = [CascadeType.REMOVE])
    val voteList: MutableList<Vote> = mutableListOf(),
): Persistable<String> {

    @Transient
    private var isNew: Boolean = true

    @PrePersist
    @PostLoad
    private fun isNotNew() {
        isNew = false
    }

    override fun getId(): String = this.id

    override fun isNew(): Boolean = isNew
}