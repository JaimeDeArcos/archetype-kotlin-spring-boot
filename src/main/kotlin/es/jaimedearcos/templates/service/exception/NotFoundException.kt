package es.jaimedearcos.templates.service.exception

class NotFoundException : RuntimeException {
    constructor(message: String, ex: Exception?): super(message, ex)
    constructor(message: String): super(message)
    constructor(): super()
}