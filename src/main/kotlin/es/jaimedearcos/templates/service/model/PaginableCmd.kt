package es.jaimedearcos.templates.service.model

import org.springframework.data.domain.Sort

open class PaginableCmd (
    var pageSize: Int? = null,
    var page: Int? = null,
    var sort: String? = null,
    var sortDir: Sort.Direction? = null
)