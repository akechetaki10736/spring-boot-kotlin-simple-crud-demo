package cc.ting.practice.springboot.crud.demo.web.controller

import cc.ting.practice.springboot.crud.demo.data.dto.EmployeeDto
import cc.ting.practice.springboot.crud.demo.data.enu.Gender
import cc.ting.practice.springboot.crud.demo.data.vo.EmployeeVo
import cc.ting.practice.springboot.crud.demo.service.EmployeeService
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
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
        BDDMockito.given(employeeService.addEmployee(employeeDto)).willReturn(employeeDto.copy(id = 1))

        mockMvc.perform(
            post("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employeeDto))
        )
            .andDo(print())
            .andExpect(status().isOk)
            .andExpect(content().string(objectMapper.writeValueAsString(employeeDto.copy(id = 1))))
    }

    @Test
    fun queryEmployeeById() {
        val employeeDto = EmployeeDto(1, "Ting, Ki", 26, "Kotlin", Gender.Male, BigDecimal(1000), null)
        BDDMockito.given(employeeService.queryEmployeeById(1)).willReturn(employeeDto)

        mockMvc.perform(
            get("/employees/{id}", "1")
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andDo(print())
            .andExpect(status().isOk)
            .andExpect(content().string(objectMapper.writeValueAsString(employeeDto)))
    }

    @Test
    fun queryEmployeeByFirstName() {
        val employeeDto = EmployeeDto(1, "Ting, Ki", 26, "Kotlin", Gender.Male, BigDecimal(1000), null)
        BDDMockito.given(employeeService.queryEmployeeByFirstName("Ting")).willReturn(listOf(employeeDto))

        mockMvc.perform(
            get("/employees/firstName/{firstName}", "Ting")
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andDo(print())
            .andExpect(status().isOk)
            .andExpect(content().string(objectMapper.writeValueAsString(listOf(employeeDto))))
    }

    @Test
    fun queryEmployeeByLastName() {
        val employeeDto = EmployeeDto(1, "Ting, Ki", 26, "Kotlin", Gender.Male, BigDecimal(1000), null)
        BDDMockito.given(
            employeeService.queryEmployeeByLastName("Ki", PageRequest.of(1, 1, Sort.Direction.DESC, "id"))
        ).willReturn(PageImpl(listOf(employeeDto)))

        mockMvc.perform(
            get("/employees")
                .param("lastName", "Ki")
                .param("page", "1")
                .param("size", "1")
                .param("column", "id")
                .param("direction", "DESC")
        )
            .andDo(print())
            .andExpect(status().isOk)
            .andExpect(content().string(objectMapper.writeValueAsString(PageImpl(listOf(employeeDto)))))
    }

    @Test
    fun modifyEmployee() {
        val newEmployeeDto = EmployeeDto(1, "Ting, Ki", 30, "Kotlin", Gender.Male, BigDecimal(3333), null)
        BDDMockito.given(employeeService.modifyEmployee(newEmployeeDto)).willReturn(newEmployeeDto.copy())

        mockMvc.perform(
            put("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newEmployeeDto))
        )
            .andDo(print())
            .andExpect(status().isOk)
            .andExpect(content().string(objectMapper.writeValueAsString(newEmployeeDto)))
    }

    @Test
    fun modifyEmployeeAgeById() {
        val employeeVo = EmployeeVo(1, 100)
        BDDMockito.willDoNothing().given(employeeService).modifyEmployeeAgeById(100, 1)

        mockMvc.perform(
            patch("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employeeVo))
        )
            .andDo(print())
            .andExpect(status().isOk)
    }

    @Test
    fun removeEmployeeById() {
        BDDMockito.willDoNothing().given(employeeService).removeEmployeeById(1)

        mockMvc.perform(
            delete("/employees/{id}", 1)
        )
            .andDo(print())
            .andExpect(status().isOk)
    }


}