package es.jaimedearcos.templates.service.model

enum class ErrorCodes constructor(val code: Int, val message: String) {
    DUPLICATED_USER         (1,"The email is already in the database"),
}

