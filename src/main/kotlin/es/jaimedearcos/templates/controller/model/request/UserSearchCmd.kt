package es.jaimedearcos.templates.controller.model.request

import es.jaimedearcos.templates.domain.Roles
import es.jaimedearcos.templates.service.model.PaginableCmd

data class UserSearchCmd (
    var ids : Set<Long>? = null,
    var email : String? = null,
    var role  : Roles? = null
) : PaginableCmd()