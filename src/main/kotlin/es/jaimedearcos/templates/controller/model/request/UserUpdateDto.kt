package es.jaimedearcos.templates.controller.model.request

import com.github.pozo.KotlinBuilder
import es.jaimedearcos.templates.domain.Roles
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@KotlinBuilder
data class UserUpdateDto (

        @field:NotNull
        @field:Size(min=2,max=256)
        var firstName : String? = null,

        @field:NotNull
        @field:Size(min=2,max=256)
        var lastName : String? = null,

        @field:NotNull
        @field:Size(min=2,max=256)
        var country : String? = null,

        @field:NotNull
        @field:Size(min=2,max=256)
        var city : String? = null,

        @field:Size(min=2,max=256)
        var company : String? = null,

        @field:NotNull
        var role : Roles? = null,

        var enabled : Boolean? = null

)