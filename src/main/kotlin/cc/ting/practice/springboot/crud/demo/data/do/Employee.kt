package cc.ting.practice.springboot.crud.demo.data.`do`

import cc.ting.practice.springboot.crud.demo.data.dto.EmployeeDto
import cc.ting.practice.springboot.crud.demo.data.enu.Gender
import jakarta.persistence.*
import org.hibernate.annotations.DynamicUpdate
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table
@DynamicUpdate
data class Employee(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long?,

    @Column(length = 50)
    var firstName: String,

    @Column(length = 50)
    var lastName: String,

    @Column
    var language: String,

    @Column
    var age: Int,

    @Column(length = 10)
    @Enumerated(EnumType.STRING)
    var gender: Gender,

    @Column(precision = 20, scale = 4)
    var balance: BigDecimal,

    @Column
    var createTime: LocalDateTime

) {
    fun toDto(): EmployeeDto = EmployeeDto(
        id = id,
        name = "$firstName, $lastName",
        age = age,
        language = language,
        gender = gender,
        balance = balance,
        createTime = createTime
    )
}
