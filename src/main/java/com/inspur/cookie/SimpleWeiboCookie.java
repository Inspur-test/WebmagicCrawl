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
* 项目名称：WebmagicCrawl   
* 类名称：SimpleWeiboCookie   
* 类描述：  weibo.cn网站的cookie获取并保存
* 创建人：Diablo   
* 创建时间：2015年3月5日 上午11:16:11   
* 修改人：Diablo   
* 修改时间：2015年3月5日 上午11:16:11   
* 修改备注：   
* @version    
*    
*/
public class SimpleWeiboCookie implements ICookie{
	public Set<Cookie> getCookie(String login_url,String user_name,String password){
		ChromeDriver webDriver=new ChromeDriver();
		webDriver.get(login_url);
    	try {
			Thread.sleep(150);
			List<WebElement> element = webDriver.findElements(By.tagName("input"));
        	webDriver.findElement(By.name("mobile")).clear();
        	webDriver.findElement(By.name("mobile")).sendKeys(user_name);
        	Thread.sleep(150);
        	element.get(1).clear();
        	element.get(1).sendKeys(password);
        	Thread.sleep(150);
        	webDriver.findElement(By.name("submit")).click();
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
		}
    	return  null;
	}
}
