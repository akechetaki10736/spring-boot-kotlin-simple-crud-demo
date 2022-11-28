package cc.ting.practice.springboot.crud.demo

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class SpringBootKotlinSimpleCrudDemoApplicationTests {

    @Autowired
    lateinit var springBootKotlinSimpleCrudDemoApplication: SpringBootKotlinSimpleCrudDemoApplication

    @Test
    fun contextLoads() {
        println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!")
        println(springBootKotlinSimpleCrudDemoApplication)
        println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!")
    }

}
