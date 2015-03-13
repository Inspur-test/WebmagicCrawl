package com.inspur.chrome.tieba;

import java.io.Closeable;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.inspur.chrome.IMethod.ICookie;
import com.inspur.cookie.TiebaCookie;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.downloader.Downloader;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.PlainText;
import us.codecraft.webmagic.utils.UrlUtils;

public class TiebaDownloader implements Downloader, Closeable{

	private volatile WebDriverPool webDriverPool;
	private String login_url="http://passport.baidu.com/";
    private Logger logger = Logger.getLogger(getClass());
    private int sleepTime = 0;
    private int poolSize = 1;
    private static  Set<Cookie> cookies;
    /**
     * 新建
     *
     * @param chromeDriverPath
     */
    public TiebaDownloader(String chromeDriverPath,String cookieFilePath) {
        System.getProperties().setProperty("webdriver.chrome.driver", chromeDriverPath); 
        ICookie cookie=new TiebaCookie();
         cookies=cookie.getCookieFromFile(cookieFilePath);
    }
    /**
     * set sleep time to wait until load success
     *
     * @param sleepTime
     * @return this
     */
    public TiebaDownloader setSleepTime(int sleepTime) {
        this.sleepTime = sleepTime;
        return this;
    }
    @SuppressWarnings("deprecation")
	@Override
    public Page download(Request request, Task task) {
        checkInit();
        WebDriver webDriver;
        try {
            webDriver = webDriverPool.get();
        } catch (Exception e) {
            logger.warn("interrupted", e);
            return null;
        }
        logger.info("downloading page " + request.getUrl());
        webDriver.get(login_url);
        for (Cookie cookie : cookies) {
        	 webDriver.manage().addCookie(cookie);
            }
        try {
            Thread.sleep(sleepTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
        webDriver.get(request.getUrl());
        for(int i=0;i<100;i++){
            webDriver.findElement(By.tagName("body")).sendKeys(Keys.SPACE);
            }
        WebElement webElement = webDriver.findElement(By.xpath("/html"));
        String content = webElement.getAttribute("outerHTML");
        Page page = new Page();
        page.setRawText(content);
        page.setHtml(new Html(UrlUtils.fixAllRelativeHrefs(content, request.getUrl())));
        page.setUrl(new PlainText(request.getUrl()));
        page.setRequest(request);
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriverPool.returnToPool(webDriver);
        return page;
    }

    private void checkInit() {
        if (webDriverPool == null) {
            synchronized (this){
                webDriverPool = new WebDriverPool(poolSize);
            }
        }
    }

    @Override
    public void setThread(int thread) {
        this.poolSize = thread;
    }

    @Override
    public void close() throws IOException {
        webDriverPool.closeAll();
    }

	public String getLogin_url() {
		return login_url;
	}

	public void setLogin_url(String login_url) {
		this.login_url = login_url;
	}
}
