package es.jaimedearcos.templates.repository.model

import java.time.LocalDateTime
import javax.persistence.metamodel.SingularAttribute
import javax.persistence.metamodel.StaticMetamodel

@StaticMetamodel(UserEntity::class)
object UserEntity_ {

    @Volatile
    var id: SingularAttribute<UserEntity, Long>? = null
    @Volatile
    var role: SingularAttribute<UserEntity, String>? = null
    @Volatile
    var company: SingularAttribute<UserEntity, String>? = null
    @Volatile
    var firstName: SingularAttribute<UserEntity, String>? = null
    @Volatile
    var lastName: SingularAttribute<UserEntity, String>? = null
    @Volatile
    var city: SingularAttribute<UserEntity, String>? = null
    @Volatile
    var country: SingularAttribute<UserEntity, String>? = null
    @Volatile
    var pass: SingularAttribute<UserEntity, String>? = null
    @Volatile
    var name: SingularAttribute<UserEntity, String>? = null
    @Volatile
    var phone: SingularAttribute<UserEntity, String>? = null
    @Volatile
    var email: SingularAttribute<UserEntity, String>? = null
    @Volatile
    var enabled: SingularAttribute<UserEntity, Boolean>? = null
    @Volatile
    var verified: SingularAttribute<UserEntity, Boolean>? = null
    @Volatile
    var changedPassword: SingularAttribute<UserEntity, Boolean>? = null

}
