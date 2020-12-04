package es.jaimedearcos.templates.service.exception

import es.jaimedearcos.templates.service.model.ErrorCodes

class AppException (
        var error: ErrorCodes,
        message: String? = error.message) : RuntimeException(message)
