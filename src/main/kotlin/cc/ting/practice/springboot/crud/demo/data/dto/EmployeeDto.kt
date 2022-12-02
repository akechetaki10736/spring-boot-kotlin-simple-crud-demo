package cc.ting.practice.springboot.crud.demo.data.dto

import cc.ting.practice.springboot.crud.demo.data.`do`.Employee
import cc.ting.practice.springboot.crud.demo.data.enu.Gender
import java.math.BigDecimal
import java.time.LocalDateTime

data class EmployeeDto(
    val id: Long?,
    val name: String,
    val age: Int,
    val language: String,
    val gender: Gender,
    val balance: BigDecimal,
    val createTime: LocalDateTime?
) {
    fun toDo(): Employee =
        run {
            val names = name.split(",")
            Employee(
                id = id,
                firstName = names.first().trim(),
                lastName = names.last().trim(),
                age = age,
                language = language,
                gender = gender,
                balance = balance,
                createTime = createTime
            )
        }
}
