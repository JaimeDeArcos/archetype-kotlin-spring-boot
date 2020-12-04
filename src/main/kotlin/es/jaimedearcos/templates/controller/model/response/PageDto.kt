package es.jaimedearcos.templates.controller.model.response

data class PageDto<T> (
    var  last:Boolean = false,
    var  pageNumber:Int = 0,
    var  pageSize:Int = 0,
    var  totalPages:Int = 0,
    var  totalResults:Int = 0,
    var  content : List<T> = listOf()
)