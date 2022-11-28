package cc.ting.practice.springboot.crud.demo.data.`do`

import cc.ting.practice.springboot.crud.demo.data.dto.EmployeeDto
import jakarta.persistence.*

@Entity
@Table
data class Employee(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long?,

    @Column
    var firstName: String,

    @Column
    var lastName: String,

    @Column
    var language: String,

    @Column
    var age: Int
) {
    fun toDto(): EmployeeDto = EmployeeDto(
        id = id,
        name = "$firstName, $lastName",
        age = age,
        language = language
    )
}
