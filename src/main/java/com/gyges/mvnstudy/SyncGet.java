package com.gyges.mvnstudy;

import java.io.IOException;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

public class SyncGet {

	public static void main(String[] args) throws IOException  {
		// TODO Auto-generated method stub
		//创建一个 OkHttpClient 类的对象，该对象是使用 OkHttp 的入口。
		OkHttpClient client = new OkHttpClient();
		//创建的是表示 HTTP 请求的 Request 对象。通过 Request.Builder 这个构建帮助类可以快速的创建出 Request 对象。
		//这里指定了 Request 的 url 为 http://www.baidu.com。
		Request req = new Request.Builder()
				.url("http:\\www.baidu.com")
				.build();
	   //通过 OkHttpClient 的 newCall 方法来从 Request 对象中创建一个 Call 对象
		//再调用 execute 方法来执行该调用，所得到的结果是表示 HTTP 响应的 Response 对象。
		Response res = client.newCall(req).execute();
		if (!res.isSuccessful())
		{
			throw new IOException("服务器端错误"+res);
		}
		//通过 Response 对象中的不同方法可以访问响应的不同内容。
		//如 headers 方法来获取 HTTP 头，body 方法来获取到表示响应主体内容的 ResponseBody 对象。
		
		Headers responseHeaders = res.headers();
		for (int i = 0; i < responseHeaders.size();i++)
		{
			System.out.println(responseHeaders.name(i) + ": "+responseHeaders.value(i));
			
		}
		
		System.out.println(res.body().string());
	}

	}
	
