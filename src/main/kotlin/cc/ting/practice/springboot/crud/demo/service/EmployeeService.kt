package cc.ting.practice.springboot.crud.demo.service

import cc.ting.practice.springboot.crud.demo.data.dto.EmployeeDto
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface EmployeeService {
    fun addEmployee(employeeDto: EmployeeDto): EmployeeDto

    fun queryEmployeeById(id: Long): EmployeeDto
    
    fun queryEmployeeByFirstName(firstName: String): List<EmployeeDto>

    fun queryEmployeeByLastName(lastName: String, pageable: Pageable): Page<EmployeeDto>

    fun modifyEmployee(employeeDto: EmployeeDto): EmployeeDto
    
    fun modifyEmployeeWithAge(employeeDto: EmployeeDto): EmployeeDto

    fun modifyEmployeeAgeById(age: Int, id: Long): Unit

    fun removeEmployeeById(id: Long)

}