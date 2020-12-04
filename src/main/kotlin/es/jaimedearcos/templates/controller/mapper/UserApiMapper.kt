package es.jaimedearcos.templates.controller.mapper

import es.jaimedearcos.templates.controller.model.request.UserCreateDto
import es.jaimedearcos.templates.controller.model.request.UserUpdateDto
import es.jaimedearcos.templates.controller.model.response.UserDto
import es.jaimedearcos.templates.domain.User
import es.jaimedearcos.templates.service.model.UserCreateCmd
import es.jaimedearcos.templates.service.model.UserUpdateCmd
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface UserApiMapper {

    fun toDto(source : User) : UserDto

    fun toDomain(source : UserCreateDto) : UserCreateCmd
    fun toDomain(source : UserUpdateDto) : UserUpdateCmd

}