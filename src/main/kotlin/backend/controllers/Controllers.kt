package backend.controllers

open class Controllers {
    val auth get() = AuthController()

    val users get() = UsersController()

    val products get() = ProductController()

    val orders get() = OrderController()
}
