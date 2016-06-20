package com.gyges.mvnstudy;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

public class SyncGet {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		OkHttpClient client = new OkHttpClient();
		Request req = new Request.Builder()
				.url("http:\\www.baidu.com")
				.build();
		
		
		
		

	}

}
