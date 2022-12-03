package cc.ting.practice.springboot.crud.demo.service.impl

import cc.ting.practice.springboot.crud.demo.data.dao.EmployeeDao
import cc.ting.practice.springboot.crud.demo.data.dto.EmployeeDto
import cc.ting.practice.springboot.crud.demo.data.enu.Gender
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito

import org.mockito.Mockito.mock
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
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

    @Test
    fun queryEmployeeById() {
        val employeeDto = EmployeeDto(1, "Ting, Ki", 26, "Kotlin", Gender.Male, BigDecimal(1000), null)
        val employee = employeeDto.toDo()

        BDDMockito.given(employeeDao.findById(1)).willReturn(Optional.of(employee))
        val queryResult = employeeService.queryEmployeeById(1);

        Assertions.assertThat(queryResult.id).isEqualTo(1)
        Assertions.assertThat(queryResult.age).isEqualTo(26)
        Assertions.assertThat(queryResult.name).isEqualTo("Ting, Ki")
        Assertions.assertThat(queryResult.gender).isEqualTo(Gender.Male)
        Assertions.assertThat(queryResult.balance).isEqualTo(BigDecimal(1000))
        Assertions.assertThat(queryResult.language).isEqualTo("Kotlin")
    }

    @Test
    fun queryEmployeeByFirstName() {
        val employeeDto = EmployeeDto(1, "Ting, Ki", 26, "Kotlin", Gender.Male, BigDecimal(1000), null)
        val employee = employeeDto.toDo()

        BDDMockito.given(employeeDao.findByFirstNameOrderByIdDesc("Ting")).willReturn(listOf(employee))

        val queryResult = employeeService.queryEmployeeByFirstName("Ting")
        Assertions.assertThat(queryResult == listOf(employeeDto)).isTrue
    }

    @Test
    fun queryEmployeeByLastName() {
        val employeeDto = EmployeeDto(1, "Ting, Ki", 26, "Kotlin", Gender.Male, BigDecimal(1000), null)
        val employee = employeeDto.toDo()

        BDDMockito.given(
            employeeDao.findByLastName("Ki", PageRequest.of(1, 1, Sort.Direction.DESC, "id"))
        ).willReturn(PageImpl(listOf(employee)))

        val queryResult = employeeService.queryEmployeeByLastName("Ki", PageRequest.of(1, 1, Sort.Direction.DESC, "id"))
        Assertions.assertThat(queryResult == PageImpl(listOf(employeeDto))).isTrue
    }

    @Test
    fun modifyEmployeeWithAge() {
        val employeeDto = EmployeeDto(1, "Ting, Ki", 26, "Kotlin", Gender.Male, BigDecimal(1000), null)
        val employee = employeeDto.toDo()
        val newEmployeeDto = employeeDto.copy(age = 100)
        val newEmployee = newEmployeeDto.toDo()
        
        BDDMockito.given(employeeDao.findById(1)).willReturn(Optional.of(employee))
        BDDMockito.given(employeeDao.save(newEmployee)).willReturn(newEmployee)
        
        val updateResult = employeeService.modifyEmployeeWithAge(newEmployeeDto)
        Assertions.assertThat(updateResult).isEqualTo(newEmployeeDto)
    }

}