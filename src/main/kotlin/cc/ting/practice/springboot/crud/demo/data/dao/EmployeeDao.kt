package cc.ting.practice.springboot.crud.demo.data.dao

import cc.ting.practice.springboot.crud.demo.data.`do`.Employee
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface EmployeeDao: JpaRepository<Employee, Long>, JpaSpecificationExecutor<Employee> {
}