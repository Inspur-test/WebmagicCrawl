package com.inspur.chrome.IMethod;

import java.util.Set;

import org.openqa.selenium.Cookie;

/**   
*    
* 项目名称：WebmagicCrawl   
* 类名称：ICookie   
* 类描述： 获取cookie并保存  
* 创建人：Diablo   
* 创建时间：2015年3月5日 上午11:04:11   
* 修改人：Diablo   
* 修改时间：2015年3月5日 上午11:04:11   
* 修改备注：   
* @version    
*    
*/
public interface ICookie {
  Set<Cookie> getCookie(String login_url,String user_name,String password);
}
