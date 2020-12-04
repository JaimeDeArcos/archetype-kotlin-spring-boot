package es.jaimedearcos.templates.domain

import com.github.pozo.KotlinBuilder
import es.jaimedearcos.templates.domain.Roles.ROLE_USER
import java.time.LocalDateTime

@KotlinBuilder
data class User(
        val id: Long? = null,
        var email: String? = null,
        var password: String? = null,
        var role: Roles = ROLE_USER,
        var firstName: String? = null,
        var lastName: String? = null,
        var country: String? = null,
        var city: String? = null,
        var phone: String? = null,
        var dni: String? = null,
        var company: String? = null,
        var enabled: Boolean = false,
        var verified: Boolean = false,
        var created: LocalDateTime? = null,
        var changedPassword: Boolean = false
)