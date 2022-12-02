package cc.ting.practice.springboot.crud.demo.service.impl

import cc.ting.practice.springboot.crud.demo.data.dao.EmployeeDao
import cc.ting.practice.springboot.crud.demo.data.dto.EmployeeDto
import cc.ting.practice.springboot.crud.demo.service.EmployeeService
import jakarta.transaction.Transactional
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import org.springframework.data.domain.Pageable

@Service
class EmployeeServiceImpl(
    val employeeDao: EmployeeDao
) : EmployeeService {


    override fun addEmployee(employeeDto: EmployeeDto): EmployeeDto = employeeDao.save(employeeDto.toDo()).toDto()

    override fun queryEmployeeById(id: Long): EmployeeDto =
        employeeDao.findById(id)
            .map { it.toDto() }
            .orElseThrow { RuntimeException() }

    override fun queryEmployeeByFirstName(firstName: String): List<EmployeeDto> =
        employeeDao.findByFirstNameOrderByIdDesc(firstName)
            .map { it.toDto() }

    override fun queryEmployeeByLastName(lastName: String, pageable: Pageable): Page<EmployeeDto> =
        employeeDao.findByLastName(lastName, pageable)
            .map { it.toDto() }

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

    @Transactional
    override fun modifyEmployeeAgeById(age: Int, id: Long): Unit = employeeDao.updateEmployeeAgeById(age, id)

    override fun removeEmployeeById(id: Long) = employeeDao.deleteById(id)

}