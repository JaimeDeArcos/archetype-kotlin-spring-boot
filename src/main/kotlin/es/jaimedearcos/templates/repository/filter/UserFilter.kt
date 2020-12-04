package es.jaimedearcos.templates.repository.filter
 
import es.jaimedearcos.templates.controller.model.request.UserSearchCmd
import es.jaimedearcos.templates.domain.Roles
import es.jaimedearcos.templates.repository.model.UserEntity
import es.jaimedearcos.templates.repository.model.UserEntity_
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Component
import org.springframework.util.CollectionUtils


@Component
class UserFilter : PaginableFilter() {

    fun getSpecs(dto: UserSearchCmd): Specification<UserEntity> {
        return Specification
                .where(getSpecById(dto.ids))
                .and(getSpecByEmail(dto.email))
                .and(getSpecByRole(dto.role))
    }

    // ID
    private fun getSpecById(ids: Set<Long>?): Specification<UserEntity> {
        if (CollectionUtils.isEmpty(ids)) return Specification { _,_, cb -> cb.and() }
        return Specification { root,_,_ -> root.get(UserEntity_.id).`in`(ids) }
    }

    // EMAIL
    private fun getSpecByEmail(email: String?): Specification<UserEntity> {
        if (email==null) return Specification { _,_, cb -> cb.and() }
        return Specification { root,_,cb -> cb.like(root.get(UserEntity_.email), "%${email}%") }
    }

    // ROLE
    private fun getSpecByRole(role: Roles?): Specification<UserEntity> {
        if (role==null) return Specification { _,_, cb -> cb.and() }
        return Specification { root,_,cb -> cb.equal(root.get(UserEntity_.role), role) }
    }

}

