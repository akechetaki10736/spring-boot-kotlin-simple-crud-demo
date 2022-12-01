package cc.ting.practice.springboot.crud.demo.service.impl

import cc.ting.practice.springboot.crud.demo.data.dao.EmployeeDao
import cc.ting.practice.springboot.crud.demo.data.dto.EmployeeDto
import cc.ting.practice.springboot.crud.demo.service.EmployeeService
import org.springframework.stereotype.Service

@Service
class EmployeeServiceImpl(
    val employeeDao: EmployeeDao
) : EmployeeService {


    override fun addEmployee(employeeDto: EmployeeDto): EmployeeDto = employeeDao.save(employeeDto.toDo()).toDto()

    override fun queryEmployeeById(id: Long): EmployeeDto =
        employeeDao.findById(id)
            .map { it.toDto() }
            .orElseThrow { RuntimeException() }


    override fun modifyEmployee(employeeDto: EmployeeDto): EmployeeDto =
        employeeDao.findById(employeeDto.id!!)
            .orElseThrow { RuntimeException() }
            .run {
                val names = employeeDto.name.split(",")
                this.firstName = names.first().trim()
                this.lastName = names.last().trim()
                this.age = employeeDto.age
                this.language = employeeDto.language
                employeeDao.save(this).toDto()
            }

    override fun modifyEmployeeWithAge(employeeDto: EmployeeDto): EmployeeDto =
        employeeDao.findById(employeeDto.id!!)
            .orElseThrow { RuntimeException() }
            .run {
                this.age = employeeDto.age
                employeeDao.save(this).toDto()
            }

    override fun removeEmployeeById(id: Long) = employeeDao.deleteById(id)

}