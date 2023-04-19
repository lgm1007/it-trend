package com.lgmpjt.ittrendbackend

import com.lgmpjt.ittrendbackend.service.SeleniumService
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ItTrendBackendApplicationTests {

    @Test
    fun contextLoads() {
    }

    @Test
    fun processCrawl() {
        val seleniumService = SeleniumService()
        seleniumService.processCrawl("brunch")
//        seleniumService.processCrawl("cwn")
//        seleniumService.processCrawl("cwntech")
//        seleniumService.processCrawl("yozm")
    }

}
