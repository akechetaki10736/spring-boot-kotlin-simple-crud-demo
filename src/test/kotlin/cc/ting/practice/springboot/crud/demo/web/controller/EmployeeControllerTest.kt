package cc.ting.practice.springboot.crud.demo.web.controller

import cc.ting.practice.springboot.crud.demo.data.dto.EmployeeDto
import cc.ting.practice.springboot.crud.demo.data.enu.Gender
import cc.ting.practice.springboot.crud.demo.service.EmployeeService
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.MockMvc
import java.math.BigDecimal

@WebMvcTest(EmployeeController::class)
class EmployeeControllerTest {

    @MockBean
    lateinit var employeeService: EmployeeService

    @Autowired
    lateinit var mockMvc: MockMvc

    private val objectMapper = ObjectMapper()

    @Test
    fun addEmployee() {

        val employeeDto = EmployeeDto(null, "Ting, Ki", 26, "Kotlin", Gender.Male, BigDecimal(1000), null)

        mockMvc.perform(
            post("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employeeDto))
        )
    }
}