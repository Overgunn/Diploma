package frontend.helpers

import backend.controllers.Controllers
import backend.helpers.GarbageCollector
import io.qameta.allure.Step

class OrderHelperFE: Controllers() {

    @Step("Find order by id and add it to GC")
    fun ordersForGC(orderId: Int) {
        GarbageCollector.order.add(orderId)
        println("Added to GC order: $orderId")
    }
}