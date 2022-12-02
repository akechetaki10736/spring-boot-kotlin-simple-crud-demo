package cc.ting.practice.springboot.crud.demo.service.impl

import cc.ting.practice.springboot.crud.demo.data.dao.EmployeeDao
import cc.ting.practice.springboot.crud.demo.data.dto.EmployeeDto
import cc.ting.practice.springboot.crud.demo.data.enu.Gender
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito

import org.mockito.Mockito.mock
import java.math.BigDecimal
import java.util.*

class EmployeeServiceImplTest {

    private val employeeDao = mock(EmployeeDao::class.java)

    private val employeeService = EmployeeServiceImpl(employeeDao)

    @Test
    fun addEmployee() {
        val employeeDto = EmployeeDto(null, "Ting, Ki", 26, "Kotlin", Gender.Male, BigDecimal(1000), null)
        val employee = employeeDto.toDo()
        BDDMockito.given(employeeDao.save(employee)).willReturn(employee.copy(1))
        
        val addEmployee = employeeService.addEmployee(employeeDto)

        Assertions.assertThat(addEmployee.id).isNotNull
        Assertions.assertThat(addEmployee.name).isEqualTo("Ting, Ki")
        Assertions.assertThat(addEmployee.age).isEqualTo(26)
        Assertions.assertThat(addEmployee.gender).isEqualTo(Gender.Male)
        Assertions.assertThat(addEmployee.balance).isEqualTo(BigDecimal(1000))
    }

    @Test
    fun modifyEmployee() {
        val originalEmployeeDto = EmployeeDto(1, "Ting, Ki", 26, "Kotlin", Gender.Male, BigDecimal(1000), null)
        val originalEmployee = originalEmployeeDto.toDo()

        BDDMockito.given(employeeDao.findById(1)).willReturn(Optional.of(originalEmployee))

        val employeeDto = EmployeeDto(1, "Ting, Ki", 26, "Kotlin", Gender.Male, BigDecimal(2222), null)
        val employee = employeeDto.toDo()
        BDDMockito.given(employeeDao.save(employee)).willReturn(employee)


        val modifyEmployee = employeeService.modifyEmployee(employeeDto)

        Assertions.assertThat(modifyEmployee.id).isEqualTo(1)
        Assertions.assertThat(modifyEmployee.name).isEqualTo("Ting, Ki")
        Assertions.assertThat(modifyEmployee.age).isEqualTo(26)
        Assertions.assertThat(modifyEmployee.gender).isEqualTo(Gender.Male)
        Assertions.assertThat(modifyEmployee.balance).isEqualTo(BigDecimal(2222))
    }
}