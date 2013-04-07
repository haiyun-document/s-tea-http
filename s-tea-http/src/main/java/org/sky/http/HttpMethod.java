package org.sky.http;

import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;

public enum HttpMethod {

	 GET() {
		    @Override
		    HttpUriRequest createMethod(String url) {
		      return new HttpGet(url);
		    }
		  },
	POST() {
		    @Override
		    HttpUriRequest createMethod(String url) {
		      return new HttpPost(url);
		    }
		  },
	DELETE() {
		    @Override
		    HttpUriRequest createMethod(String url) {
		      return new HttpDelete(url);
		    }
		  },
		  
	HEAD(){

		@Override
		HttpUriRequest createMethod(String url) {
			// TODO Auto-generated method stub
			return new HttpHead(url);
		}
	};
		  
	abstract HttpUriRequest createMethod(String url); 
}
