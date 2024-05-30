package com.dsm.athena.domain.user.entity

import com.dsm.athena.domain.arthicle.entity.Article
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
    @Column(name = "id", nullable = false, updatable = false, length = 21)
    val id: String,

    @Column(name = "password", columnDefinition = "CHAR(60)", nullable = false)
    var password: String,

    @Column(name = "name", nullable = false, length = 15)
    var name: String,

    @OneToMany(mappedBy = "writer")
    val articleList: MutableList<Article> = arrayListOf(),

    @Transient
    private var isNew: Boolean = true

): Persistable<String> {

    @PrePersist
    @PostLoad
    private fun isNotNew() {
        isNew = false
    }

    override fun getId(): String = this.id

    override fun isNew(): Boolean = isNew
}