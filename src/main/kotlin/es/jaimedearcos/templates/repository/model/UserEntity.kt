package es.jaimedearcos.templates.repository.model

import com.github.pozo.KotlinBuilder
import es.jaimedearcos.templates.domain.Roles
import java.time.LocalDateTime
import javax.persistence.*
import javax.persistence.EnumType.STRING

@Entity
@KotlinBuilder
@Table(name = "users")
data class UserEntity(

        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long? = null,

        @Column(name = "email")
        var email: String? = null,

        @Column(name = "role")
        @Enumerated(STRING)
        var role: Roles = Roles.ROLE_USER,

        @Column(name = "first_name")
        var firstName: String? = null,

        @Column(name = "last_name")
        var lastName: String? = null,

        @Column(name = "country")
        var country: String? = null,

        @Column(name = "city")
        var city: String? = null,

        @Column(name = "company")
        var company: String? = null,

        @Column(name = "enabled")
        var enabled: Boolean = false,

        @Column(name = "verified")
        var verified: Boolean = false

)