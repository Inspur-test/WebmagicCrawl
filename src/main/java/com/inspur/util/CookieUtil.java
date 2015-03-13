package com.inspur.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.Set;

import org.openqa.selenium.Cookie;

public class CookieUtil {
	// 保存cookie到文件
	public void SaveCookieFile(Set<Cookie> cookies, String SavePath) {
		try {
			FileOutputStream fs = new FileOutputStream(SavePath);
			ObjectOutputStream os = new ObjectOutputStream(fs);
			os.writeObject(cookies);
			os.flush();
			os.close();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
//得到set<cookie>的序列化结果
	public static byte[] serializeObject(Object object) {
		ByteArrayOutputStream saos = new ByteArrayOutputStream();
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(saos);
			oos.writeObject(object);
			oos.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return saos.toByteArray();

	}
	 @SuppressWarnings("unchecked")
	 //反序列化set<cookie>
	public static Set<Cookie>  deserializeObject(byte[] buf) throws IOException, ClassNotFoundException{
		  HashSet<Cookie> object=null;
	       ByteArrayInputStream sais=new ByteArrayInputStream(buf);
	       ObjectInputStream ois = new ObjectInputStream(sais);
	       object=(HashSet<Cookie>) ois.readObject();
	       return object;
	    }

	@SuppressWarnings("unchecked")
	public Set<Cookie> getCookieFromFile(String CookieFilePath) {
		// TODO Auto-generated method stub
		HashSet<Cookie> cookies = new HashSet<Cookie>();
		try {
			FileInputStream fs = new FileInputStream(CookieFilePath);
			ObjectInputStream ois = new ObjectInputStream(fs);
			cookies = (HashSet<Cookie>) ois.readObject();
			ois.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return cookies;
	}
}
