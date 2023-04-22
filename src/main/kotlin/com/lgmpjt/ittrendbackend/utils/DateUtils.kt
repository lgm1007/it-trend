package com.lgmpjt.ittrendbackend.utils

import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Component
class DateUtils {
    /**
     * 날짜 포맷 변환 메서드
     * 변환할 날짜 문자열과 입력한 문자열(date)의 날짜 포맷, 실제로 변환할 날짜 포맷을 파라미터로 받는다.
     * @param date
     * @param inputFormat
     * @author lgm1007
     */
    fun formatDate(date: String, inputFormat: String, outputFormat: String): String {
        val currentFormat = DateTimeFormatter.ofPattern(inputFormat)
        val afterFormat = DateTimeFormatter.ofPattern(outputFormat)

        val dateTime: LocalDateTime = LocalDateTime.parse(date, currentFormat)

        return dateTime.format(afterFormat)
    }

}