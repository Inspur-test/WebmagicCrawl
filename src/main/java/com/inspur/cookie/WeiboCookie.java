package com.inspur.cookie;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.inspur.chrome.IMethod.ICookie;

/**
 * 
 * 项目名称：WebmagicCrawl 类名称：WeiboCookie 类描述： weibo.com的cookie获取 创建人：Diablo
 * 创建时间：2015年3月5日 上午11:16:46 修改人：Diablo 修改时间：2015年3月5日 上午11:16:46 修改备注：
 * 
 * @version
 * 
 */
public class WeiboCookie implements ICookie {
//	public static void main(String... args) {
//		WeiboCookie weibocookie = new WeiboCookie();
//		System.getProperties().setProperty("webdriver.chrome.driver",
//				"/Users/Diablo/Downloads/chromedriver");
//		// Set<Cookie>
//		// cookies=weibocookie.getCookie("http://weibo.com/login.php","wangmaoshuai@163.com","Wang13455170255");
//		// weibocookie.SaveCookieFile(cookies,
//		// "/Users/Diablo/Downloads/weibocookie");
//		Set<Cookie> cookies = weibocookie
//				.getCookieFromFile("/Users/Diablo/Downloads/weibocookie");
//		for (Cookie c : cookies) {
//			System.out.println(c.toString());
//		}
//	}

	// 获取微博的cookie
	public Set<Cookie> getCookie(String login_url, String user_name,
			String password) {
		ChromeDriver webDriver = new ChromeDriver();
		webDriver.get(login_url);
		try {
			List<WebElement> element = webDriver.findElements(By
					.tagName("input"));
			Thread.sleep(1500L);
			element.get(3).clear();
			element.get(3).sendKeys(user_name);
			Thread.sleep(1500L);
			element.get(4).clear();
			element.get(4).sendKeys(password);
			Thread.sleep(1500L);
			List<WebElement> submit = webDriver.findElements(By
					.className("W_btn_g"));
			submit.get(1).click();
			while (true) {
				Thread.sleep(500L);
				if (!webDriver.getCurrentUrl().startsWith(login_url)) {
					break;
				}
			}
			Set<Cookie> cookies = webDriver.manage().getCookies();
			webDriver.quit();
			return cookies;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			webDriver.quit();
		} finally {
			webDriver.quit();
		}
		return null;
	}
}
