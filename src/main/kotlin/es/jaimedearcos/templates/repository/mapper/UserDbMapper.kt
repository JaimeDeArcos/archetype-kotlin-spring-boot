package es.jaimedearcos.templates.repository.mapper

import es.jaimedearcos.templates.domain.User
import es.jaimedearcos.templates.repository.model.UserEntity
import es.jaimedearcos.templates.service.model.UserCreateCmd
import es.jaimedearcos.templates.service.model.UserUpdateCmd
import org.mapstruct.*

@Mapper(componentModel = "spring")
interface UserDbMapper {
    fun toDomain(user: UserEntity) : User
    fun toEntity(source: User): UserEntity
    fun toEntity(source: UserCreateCmd): UserEntity
    fun toEntity(cmd: UserUpdateCmd, @MappingTarget target: UserEntity)
}