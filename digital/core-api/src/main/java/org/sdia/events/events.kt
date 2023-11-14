package org.sdia.events


abstract class BaseEvent<T>(
    open val id : T
)
data class CreateCustomerEvent(
        override val id : String,
        val name : String,
        val email: String
):BaseEvent<String>(id)

data class updateCustomerEvent(
        override val id : String,
        val name : String,
        val email: String
):BaseEvent<String>(id)