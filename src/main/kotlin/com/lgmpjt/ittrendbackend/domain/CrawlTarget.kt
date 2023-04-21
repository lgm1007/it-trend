package com.lgmpjt.ittrendbackend.domain

enum class CrawlTarget(val service: String, val url: String) {
    BRUNCH("brunch", "https://brunch.co.kr/"),
    CWN("cwn", "https://www.cwn.kr/news/articleList.html?sc_section_code=S1N2&view_type=sm"),
    CWNTECH("cwntech", "https://www.cwn.kr/news/articleList.html?sc_section_code=S1N3&view_type=sm"),
    YOZM("yozm", "https://yozm.wishket.com/magazine/list/develop/"),
}