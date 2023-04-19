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
            "brunch" -> crawlBrunchPost(driver)
            "cwn" -> crawlCwnPostTest(driver)
            "cwntech" -> crawlBrunchPost(driver)
            else -> crawlBrunchPost(driver)
        }
    }

    fun crawlBrunchPost(driver: WebDriver) {
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

            val postElementList: List<WebElement> = driver.findElements(By.cssSelector(".wrap_article_list .list_article .list_has_image a.link_post"))
            for (pe in postElementList) {
                // 초기에는 게시글 내용을 그대로 가져와서 화면에 출력해주는 형태로 구상하였으나, 해당 사이트의 서버에 저장된 이미지로 게시글을 작성하여 이미지를 가져오기 어려운 상황이 있는 등 여러 문제가 존재하다.
                // 따라서 게시글의 링크를 크롤링하여 링크의 리스트를 화면에 출력해주는 형태의 앱으로 기획을 변경한다.
                // 게시글을 구별하기 위해 게시글의 제목과 등록일자를 수집한다.
                // brunch의 경우 등록일자가 없고, O분 전 형태로 시간을 출력해주기 때문에 등록일자를 따로 수집하지 않는다.
                var childElement: WebElement = pe.findElement(By.cssSelector(".post_title strong.tit_subject"))

                var postLink: String = pe.getAttribute("href")
                var postTitle: String = childElement.text
                println("---------")
                println(postLink)
                println(postTitle)
            }
        } catch (e: InterruptedException) {
            println("Catch InterruptedException in crawlBrunchPost(): " + e.message)
        } finally {
            driver.close()
            driver.quit()
        }
    }

    fun crawlCwnPostTest(driver: WebDriver) {
        val targetUrl = CrawlTarget.CWN.url
        try {
            driver.get(targetUrl)
            // 브라우저 로딩 대기
            Thread.sleep(2000)

            val targetElementList: List<WebElement> = driver.findElements(By.cssSelector("#section-list .type2 li .view-cont h4.titles a"))
            for (te in targetElementList) {
                var postLink: String = te.getAttribute("href")
                var postTitle: String = te.text
                println("---------")
                println(postLink)
                println(postTitle)
            }
        } catch (e: InterruptedException) {
            println("Catch InterruptedException in crawlCwnPostTest(): " + e.message)
        } finally {
            driver.close()
            driver.quit()
        }
    }
}