package es.jaimedearcos.templates.service

import es.jaimedearcos.templates.controller.model.request.UserSearchCmd
import es.jaimedearcos.templates.domain.User
import es.jaimedearcos.templates.repository.UserRepository
import es.jaimedearcos.templates.repository.filter.UserFilter
import es.jaimedearcos.templates.repository.mapper.UserDbMapper
import es.jaimedearcos.templates.service.exception.AppException
import es.jaimedearcos.templates.service.exception.NotFoundException
import es.jaimedearcos.templates.service.model.ErrorCodes.DUPLICATED_USER
import es.jaimedearcos.templates.service.model.UserCreateCmd
import es.jaimedearcos.templates.service.model.UserUpdateCmd
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service

interface UserService {
    fun search(cmd: UserSearchCmd) : Page<User>
    fun detail(email: String): User
    fun create(cmd: UserCreateCmd): User
    fun remove(id: Long)
    fun update(id: Long, cmd: UserUpdateCmd): User
}

@Service
class UserServiceImpl (
        private val repository : UserRepository,
        private val dbMapper : UserDbMapper,
        private val filter : UserFilter
) : UserService {

    private val log: Logger = LoggerFactory.getLogger(UserServiceImpl::class.java)

    override fun detail(mail: String): User {
        log.debug("Retrieving user by ID - [$mail]")
        return repository.findByEmail(mail)
                .map { dbMapper.toDomain(it) }
                .orElseThrow { NotFoundException("There is no user with email - $mail")}
    }

    override fun create(cmd : UserCreateCmd): User {
        log.debug("Creating new User - [$cmd]")

        // Check if email exists
        repository.findByEmail(cmd.email)
                .ifPresent{ throw AppException(DUPLICATED_USER) }

        val dbUser = repository.save(dbMapper.toEntity(cmd))

        return dbMapper.toDomain(dbUser)
    }

    override fun remove(id: Long) {

        log.debug("Retrieving user by ID - [$id]")

        val exist = repository.deleteById(id);

        if (!exist){
            throw NotFoundException()
        }

        log.debug("User with id [$id] has been removed")
    }


    override fun search(cmd: UserSearchCmd) : Page<User> {

        val specs = filter.getSpecs(cmd)
        val pageable = filter.getPagination(cmd)

        val page = repository.findAll(specs,pageable)

        return page.map { dbMapper.toDomain(it) }
    }

    override fun update(id: Long, cmd: UserUpdateCmd): User {

        log.debug("Updating existing User - [${id}]")

        // Check if course exists
        val dbUser = repository.findById(id)
                .orElseThrow{ NotFoundException() }

        // Map updated values
        dbMapper.toEntity(cmd,dbUser)

        val dbEntity = repository.save(dbUser)

        return dbMapper.toDomain(dbEntity)
    }

}