package com.inspur.webmagic.pipeline;

import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.inspur.mybatis.HtmlUrlMapper;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

public class SaveHtmlUrlPipeline implements Pipeline {
	
	public void process(ResultItems resultitems, Task task) {
		// TODO Auto-generated method stub
		String regex = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]" ;
		Pattern patt = Pattern. compile(regex );
		HtmlUrlMapper htmlurlmapper=new HtmlUrlMapper(); 
		String url=resultitems.getRequest().getUrl();
		Set<Entry<String, Object>> urls=resultitems.getAll().entrySet();
		for(Entry<String, Object> _url:urls){
		String _last_crawl_time=System.currentTimeMillis()+"";
		String child_url=_url.getValue().toString();
		child_url=child_url.substring(child_url.indexOf("[")+1, child_url.indexOf("]"));
		String[] child_url_arr=child_url.split(",");
		for(String s:child_url_arr){
			Matcher matcher = patt.matcher(s.trim());
			boolean isMatch = matcher.matches();
			if(isMatch){
				htmlurlmapper.insertHtmlUrl(s.trim(),url.trim(), "0", _last_crawl_time);
			}
		}
		}
		
	}

}
