package cc.ting.practice.springboot.crud.demo.service

import cc.ting.practice.springboot.crud.demo.data.dto.EmployeeDto

interface EmployeeService {
    fun addEmployee(employeeDto: EmployeeDto): EmployeeDto

    fun queryEmployeeById(id: Long): EmployeeDto

    fun modifyEmployee(employeeDto: EmployeeDto): EmployeeDto
    fun modifyEmployeeWithAge(employeeDto: EmployeeDto): EmployeeDto

    fun removeEmployeeById(id: Long)

}