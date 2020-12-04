package es.jaimedearcos.templates.repository

import es.jaimedearcos.templates.repository.model.UserEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.data.repository.Repository
import java.util.*

interface UserRepository : Repository<UserEntity, Long> {

    fun findById(id: Long): Optional<UserEntity>
    fun findByEmail(email: String): Optional<UserEntity>
    fun findAll(specs: Specification<UserEntity>?, page: Pageable): Page<UserEntity>

    fun save(u: UserEntity): UserEntity
    fun delete(u: UserEntity):Boolean
    fun deleteById(id: Long):Boolean

}
