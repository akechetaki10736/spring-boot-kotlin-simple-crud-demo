package cc.ting.practice.springboot.crud.demo.web.controller

import cc.ting.practice.springboot.crud.demo.data.dto.EmployeeDto
import cc.ting.practice.springboot.crud.demo.data.vo.EmployeeVo
import cc.ting.practice.springboot.crud.demo.service.EmployeeService
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
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
    
    @GetMapping("/firstName/{firstName}")
    fun queryEmployeeByFirstName(@PathVariable firstName: String): List<EmployeeDto> = employeeService.queryEmployeeByFirstName(firstName)

    @GetMapping
    fun queryEmployeeByLastName(
        @RequestParam lastName: String,
        @RequestParam page: Int,
        @RequestParam size: Int,
        @RequestParam column: String,
        @RequestParam direction: Sort.Direction
    ): Page<EmployeeDto> = employeeService.queryEmployeeByLastName(lastName, PageRequest.of(page, size, direction, column))
    
    
    @PutMapping
    fun modifyEmployee(@RequestBody employeeDto: EmployeeDto): EmployeeDto = employeeService.modifyEmployee(employeeDto)

    @PatchMapping
    fun modifyEmployeeAgeById(@RequestBody employeeVo: EmployeeVo) = employeeService.modifyEmployeeAgeById(employeeVo.age, employeeVo.id)

    @DeleteMapping("/{id}")
    fun removeEmployeeById(@PathVariable id: Long) = employeeService.removeEmployeeById(id)
}