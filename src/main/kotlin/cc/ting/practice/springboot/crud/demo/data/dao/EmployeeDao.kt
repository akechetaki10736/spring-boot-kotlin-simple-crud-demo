package cc.ting.practice.springboot.crud.demo.data.dao

import cc.ting.practice.springboot.crud.demo.data.`do`.Employee
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query

interface EmployeeDao: JpaRepository<Employee, Long>, JpaSpecificationExecutor<Employee> {

    fun findByFirstNameOrderByIdDesc(firstName: String): List<Employee>

    fun findByLastName(lastName: String, pageable: Pageable): Page<Employee>

    @Modifying
    @Query("UPDATE Employee e SET e.age = :age WHERE e.id = :id")
    fun updateEmployeeAgeById(age: Int, id: Long)
}