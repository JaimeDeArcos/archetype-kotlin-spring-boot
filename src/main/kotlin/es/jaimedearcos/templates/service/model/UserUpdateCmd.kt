package es.jaimedearcos.templates.service.model

import com.github.pozo.KotlinBuilder
import es.jaimedearcos.templates.domain.Roles
import es.jaimedearcos.templates.domain.Roles.ROLE_USER

@KotlinBuilder
data class UserUpdateCmd (
        var firstName : String? = null,
        var lastName  : String? = null,
        var country   : String? = null,
        var city      : String? = null,
        var dni       : String? = null,
        var company   : String? = null,
        var enabled   : Boolean? = false,
        var role      : Roles? = ROLE_USER
)
