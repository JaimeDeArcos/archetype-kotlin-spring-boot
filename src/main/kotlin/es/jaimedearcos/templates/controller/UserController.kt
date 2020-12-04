package es.jaimedearcos.templates.controller

import es.jaimedearcos.templates.controller.mapper.UserApiMapper
import es.jaimedearcos.templates.controller.model.request.UserCreateDto
import es.jaimedearcos.templates.controller.model.request.UserSearchCmd
import es.jaimedearcos.templates.controller.model.request.UserUpdateDto
import es.jaimedearcos.templates.controller.model.response.PageDto
import es.jaimedearcos.templates.controller.model.response.UserDto
import es.jaimedearcos.templates.service.UserService
import io.swagger.annotations.Api
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus.OK
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1/users")
@Api(tags=["Users API"], description = "User Accounts management")
class UserController(
        private val service : UserService,
        private val mapper : UserApiMapper
){

    private val log: Logger = LoggerFactory.getLogger(UserController::class.java)

    @GetMapping("{email}")
    @ResponseStatus(OK)
    @ResponseBody fun detail(@PathVariable("email") email: String): UserDto {
        log.debug("Requesting detail for User : [$email]...")
        val u = service.detail(email)
        return mapper.toDto(u)
    }

    @GetMapping
    @ResponseStatus(OK)
    @ResponseBody fun search(dto : UserSearchCmd): PageDto<UserDto> {
        log.debug("Searching Users : [$dto]")

        val result = service.search(dto)

        val target = PageDto<UserDto>()
        target.content = result.content.map { mapper.toDto(it) }
        target.last = result.isLast
        target.totalResults = result.totalElements.toInt()
        target.pageSize = result.size
        target.pageNumber = result.number
        target.totalPages = result.totalPages
        return target
    }

    @PostMapping
    @ResponseStatus(OK)
    @ResponseBody fun create(@RequestBody @Valid dto : UserCreateDto): UserDto {
        log.debug("Requesting creation of new User : [${dto.email}]...")
        val user = service.create(mapper.toDomain(dto))
        return mapper.toDto(user)
    }

    @PutMapping("{id}")
    @ResponseStatus(OK)
    @ResponseBody fun update(
            @PathVariable("id") id: Long,
            @RequestBody @Valid dto : UserUpdateDto) : UserDto {
        log.debug("Requesting update of User : [${id}]...")
        val result = service.update(id,mapper.toDomain(dto))
        return mapper.toDto(result)
    }

    @DeleteMapping("{id}")
    @ResponseStatus(OK)
    @ResponseBody fun delete(@PathVariable("id") id: Long) {
        log.debug("Requesting deletion of User : [$id]...")
        service.remove(id)
    }

}