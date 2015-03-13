package com.inspur.webmagic.pipeline;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import com.inspur.mybatis.HtmlContentMapper;

/**   
*    
* 项目名称：StormCrawl   
* 类名称：SaveHtmlContentPipeline   
* 类描述： 爬虫保存数据  
* 创建人：Diablo   
* 创建时间：2015年2月25日 下午2:19:54   
* 修改人：Diablo   
* 修改时间：2015年2月25日 下午2:19:54   
* 修改备注：   
* @version    
*    
*/
public class SaveHtmlContentPipeline implements Pipeline{
	public void process(ResultItems resultitems, Task task) {
		// TODO Auto-generated method stub
		HtmlContentMapper HtmlContentMapper=new HtmlContentMapper(); 
		String child_url=resultitems.getRequest().getUrl();
		String content=resultitems.get("content").toString();
		String last_crawl_time=System.currentTimeMillis()+"";
		HtmlContentMapper.insertContent(child_url, content, last_crawl_time);
		HtmlContentMapper.updateHtmlUrl(child_url, "1", last_crawl_time);
	}
}
