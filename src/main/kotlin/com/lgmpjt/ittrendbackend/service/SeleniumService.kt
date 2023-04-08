package com.lgmpjt.ittrendbackend.service

import com.lgmpjt.ittrendbackend.utils.SeleniumUtils
import org.springframework.stereotype.Service

@Service
class SeleniumService {
    fun processCrawl(service: String) {
        val seleniumUtil = SeleniumUtils()
        seleniumUtil.process(service)
    }
}