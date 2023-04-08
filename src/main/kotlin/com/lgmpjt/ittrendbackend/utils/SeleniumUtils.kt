package com.lgmpjt.ittrendbackend.utils

import com.lgmpjt.ittrendbackend.enums.CrawlTarget
import org.openqa.selenium.By
import org.openqa.selenium.PageLoadStrategy
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.springframework.stereotype.Component
import java.util.stream.Collectors

@Component
class SeleniumUtils {
    fun process(service: String) {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/driver/chromedriver.exe")
        val options = ChromeOptions()
        options.addArguments("--remote-allow-origins=*")
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        val driver:WebDriver = ChromeDriver(options)

        when (service) {
            "brunch" -> crawlBrunchPostTest(driver)
            "cwn" -> crawlBrunchPostTest(driver)
            "cwntech" -> crawlBrunchPostTest(driver)
            else -> crawlBrunchPostTest(driver)
        }
    }

    fun crawlBrunchPostTest(driver: WebDriver) {
        val targetUrl = CrawlTarget.BRUNCH.url
        try {
            driver.get(targetUrl)
            // 브라우저 로딩 대기
            Thread.sleep(1000)

            var keywordElementList: List<WebElement> = driver.findElements(By.cssSelector(".keyword_list_wrap .keyword_list a.keyword_item"))
            var subjectLink = ""
            for (ke in keywordElementList) {
                var text = ke.text
                if (text.contains("IT")) {
                    subjectLink = ke.getAttribute("href")
                    driver.get(subjectLink)
                    // 브라우저 로딩 대기
                    Thread.sleep(2000)
                    break
                }
            }

            var postElementList: List<WebElement> = driver.findElements(By.cssSelector(".wrap_article_list .list_article .list_has_image a.link_post"))
            for (pe in postElementList) {
                var postLink: String = pe.getAttribute("href")
                println("---------")
                println(postLink)
                driver.get(postLink)
                Thread.sleep(2000)

                var titleElement: WebElement = driver.findElement(By.cssSelector("h1.cover_title"))
                println("----TITLE----")
                println(titleElement.text)

                var textElementList: List<WebElement> = driver.findElements(By.cssSelector(".wrap_body_frame .wrap_body p.item_type_text"))
                for (te in textElementList) {
                    println("----POST----")
                    // TODO:: post text에서 빈 값이나 <br/>과 같은 개행 문자는 제거해야 한다.
                    println(te.text)
                }
            }
        } catch (e: InterruptedException) {
            println("Catch InterruptedException: " + e.message)
        } finally {
            driver.close()
            driver.quit()
        }
    }
}