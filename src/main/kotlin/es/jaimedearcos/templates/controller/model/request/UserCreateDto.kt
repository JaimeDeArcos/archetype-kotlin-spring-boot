package es.jaimedearcos.templates.controller.model.request

import com.github.pozo.KotlinBuilder
import es.jaimedearcos.templates.domain.Roles
import javax.validation.constraints.Email
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@KotlinBuilder
data class UserCreateDto (

        @field:NotNull
        @field:Size(min=2,max = 256)
        @field:Email
        var email : String?,

        @field:NotNull
        @field:Size(min=6,max=256)
        var password : String?,

        @field:Size(min=2,max = 256)
        var firstName: String? = null,

        @field:Size(min=2,max = 256)
        var lastName: String? = null,

        @field:Size(min=2,max = 256)
        var country: String? = null,

        @field:Size(min=2,max = 256)
        var city: String? = null,

        @field:Size(min=2,max = 256)
        var company: String? = null,

        @field:Size(min=6,max = 24)
        var phone: String? = null,

        @field:NotNull
        var role: Roles? = null,

        var enabled   : Boolean = false

)

