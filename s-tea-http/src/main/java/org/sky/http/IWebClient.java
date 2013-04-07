package org.sky.http;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;

public interface IWebClient {
	
	public HttpClient creatClient();
	
	public void shutdown();
	
	public HttpResponse interview(HttpUriRequest method);
	
	public void setHttpGetMethod(String url);
	
	public HttpResponse interview();
	
	public void setHttpPostMethod(String url);
	
	public void setHttpDeleteMethod(String url);
	
	public void setHttpHeadMethod(String url);
	
}
