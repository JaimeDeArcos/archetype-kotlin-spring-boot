package es.jaimedearcos.templates.service.model

import com.github.pozo.KotlinBuilder
import es.jaimedearcos.templates.domain.Roles
import es.jaimedearcos.templates.domain.Roles.ROLE_USER

@KotlinBuilder
data class UserCreateCmd (
        val email     : String,
        var role      : Roles = ROLE_USER,
        var firstName : String? = null,
        var lastName  : String? = null,
        var country   : String? = null,
        var city      : String? = null,
        var phone     : String? = null,
        var company   : String? = null,
        var enabled   : Boolean = false
)
