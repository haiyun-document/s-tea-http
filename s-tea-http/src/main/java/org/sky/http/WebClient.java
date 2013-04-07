package org.sky.http;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpParams;

public class WebClient implements IWebClient{
	
	private final HttpClient client;
	private final PoolingClientConnectionManager clientManager;
	private HttpUriRequest method;
	//private long timeout;
	WebClient(){
		SchemeRegistry schemeRegistry = new SchemeRegistry();
		schemeRegistry.register(
		new Scheme("http",80,PlainSocketFactory.getSocketFactory()));
		schemeRegistry.register(
		new Scheme("https", 443, SSLSocketFactory.getSocketFactory()));
		this.clientManager  = new PoolingClientConnectionManager(schemeRegistry);
		this.clientManager.setMaxTotal(200);
		this.clientManager.setDefaultMaxPerRoute(20);
		HttpParams params = new BasicHttpParams();
		params.setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT,20000);
		params.setParameter(CoreConnectionPNames.SO_TIMEOUT, 60000);
		this.client = new DefaultHttpClient(clientManager,params);
	}

	@Override
	public HttpClient creatClient() {
		return client;
	}

	@Override
	public void shutdown() {
		this.client.getConnectionManager().shutdown();
		
	}

	@Override
	public HttpResponse interview(HttpUriRequest method) {
		try {
			return client.execute(method);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void setHttpGetMethod(String url) {
		this.method=HttpMethod.GET.createMethod(url);
	}
	
	public void setHttpPostMethod(String url){
		this.method=HttpMethod.POST.createMethod(url);
	}
	
	public void setHttpDeleteMethod(String url){
		this.method=HttpMethod.DELETE.createMethod(url);
	}
	
	public void setHttpHeadMethod(String url){
		this.method=HttpMethod.HEAD.createMethod(url);
	}
	
	public HttpResponse interview(){
		try {
			this.client.execute(this.method);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
