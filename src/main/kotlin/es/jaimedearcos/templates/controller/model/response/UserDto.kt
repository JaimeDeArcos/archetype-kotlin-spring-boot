package es.jaimedearcos.templates.controller.model.response

import com.github.pozo.KotlinBuilder
import es.jaimedearcos.templates.domain.Roles

@KotlinBuilder
data class UserDto(
        val id : Long? = null,
        val firstName: String? = null,
        val lastName: String? = null,
        val country: String? = null,
        val city: String? = null,
        val company: String? = null,
        val email: String? = null,
        var role: Roles? = null,
        var enabled: Boolean = false,
        var verified: Boolean = false
)