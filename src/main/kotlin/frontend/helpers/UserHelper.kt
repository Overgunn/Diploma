package frontend.helpers

import backend.api.extensions.Extensions.Companion.getAsObject
import backend.controllers.Controllers
import backend.helpers.GarbageCollector
import io.qameta.allure.Step

class UserHelper: Controllers() {


    @Step("Create user via UI and add it to GC")
    fun usersForGC(email: String) {
        users.getAllUsers()
            .getAsObject()
            .firstOrNull { it.email == email }
            ?.let {
                GarbageCollector.user.add(it.id)
                println("User added to GC: ${it.id}, email: ${it.email}")
            }
    }
}