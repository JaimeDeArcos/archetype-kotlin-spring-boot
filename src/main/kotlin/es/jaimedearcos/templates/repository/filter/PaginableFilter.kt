package es.jaimedearcos.templates.repository.filter

import es.jaimedearcos.templates.service.model.PaginableCmd
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import kotlin.math.min


open class PaginableFilter {

    @Value("\${app.pagination.resultsPerPage:50}")
    private val maxResults: Int = 0

    fun getPagination(cmd: PaginableCmd): PageRequest {

        val pageSize = min(cmd.pageSize?:maxResults,maxResults)
        val page = cmd.page?:0

        val s = getSorting(cmd)

        return if (s == null) PageRequest.of(page, pageSize)
               else PageRequest.of(page, pageSize, s)
    }

    private fun getSorting(cmd: PaginableCmd): Sort? {
        if (cmd.sort == null) return null
        return Sort.by(Sort.Order(cmd.sortDir?:Sort.Direction.DESC, cmd.sort!!))
    }


}
