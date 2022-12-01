package cc.ting.practice.springboot.crud.demo.web.controller

import cc.ting.practice.springboot.crud.demo.data.dto.EmployeeDto
import cc.ting.practice.springboot.crud.demo.service.EmployeeService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/employees")
class EmployeeController(
    val employeeService: EmployeeService
) {

    @PostMapping
    fun addEmployee(@RequestBody employeeDto: EmployeeDto): EmployeeDto = employeeService.addEmployee(employeeDto)

    @GetMapping("/{id}")
    fun queryEmployeeById(@PathVariable id: Long): EmployeeDto = employeeService.queryEmployeeById(id)

    @PutMapping
    fun modifyEmployee(@RequestBody employeeDto: EmployeeDto): EmployeeDto = employeeService.modifyEmployee(employeeDto)

    @PatchMapping("/age")
    fun modifyEmployeeWithAge(@RequestBody employeeDto: EmployeeDto) = employeeService.modifyEmployeeWithAge(employeeDto)

    @DeleteMapping("/{id}")
    fun removeEmployeeById(@PathVariable id: Long) = employeeService.removeEmployeeById(id)
}