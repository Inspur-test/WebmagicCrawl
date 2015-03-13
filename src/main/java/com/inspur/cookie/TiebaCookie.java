package com.inspur.cookie;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.chrome.ChromeDriver;

import com.inspur.chrome.IMethod.ICookie;

public class TiebaCookie implements ICookie{
//	public static void main(String... args){
//		TiebaCookie weibocookie=new TiebaCookie();
//		System.getProperties().setProperty("webdriver.chrome.driver", "/Users/Diablo/Downloads/chromedriver"); 
//		Set<Cookie> cookies=weibocookie.getCookie("http://tieba.baidu.com/f/user/passport?jumpUrl=http://tieba.baidu.com/p/3616209590?pn=3&statsInfo=frs_pager#login_anchor","eric414664593@163.com","13455170255");
//		weibocookie.SaveCookieFile(cookies, "/Users/Diablo/Downloads/tiebacookie");
////		Set<Cookie> cookies=weibocookie.getCookieFromFile("/Users/Diablo/Downloads/weibocookie");
////		for(Cookie c:cookies){
////			System.out.println(c.toString());
////		}
//	}
	@Override
	public Set<Cookie> getCookie(String login_url, String user_name,
			String password) {
		// TODO Auto-generated method stub
		ChromeDriver webDriver=new ChromeDriver();
		webDriver.get(login_url);
    	try {
			Thread.sleep(1500L);
			webDriver.findElement(By.id("TANGRAM__PSP_4__userName")).clear();
			webDriver.findElement(By.id("TANGRAM__PSP_4__userName")).sendKeys(user_name);
        	Thread.sleep(1500L);
        	webDriver.findElement(By.id("TANGRAM__PSP_4__password")).clear();
        	webDriver.findElement(By.id("TANGRAM__PSP_4__password")).sendKeys(password);
        	Thread.sleep(1500L);
        	 webDriver.findElement(By.id("TANGRAM__PSP_4__submit")).click();
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
		}finally{
			webDriver.quit();
		}
    	return  null;
	}

}
