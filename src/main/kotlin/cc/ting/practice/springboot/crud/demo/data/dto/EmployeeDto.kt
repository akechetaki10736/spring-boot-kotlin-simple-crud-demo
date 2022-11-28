package cc.ting.practice.springboot.crud.demo.data.dto

import cc.ting.practice.springboot.crud.demo.data.`do`.Employee

data class EmployeeDto(
    val id: Long?,
    val name: String,
    val age: Int,
    val language: String
) {
    fun toDo(): Employee =
        run {
            val names = name.split(",")
            Employee(
                id = id,
                firstName = names.first().trim(),
                lastName = names.last().trim(),
                age = age,
                language = language
            )
        }
}
