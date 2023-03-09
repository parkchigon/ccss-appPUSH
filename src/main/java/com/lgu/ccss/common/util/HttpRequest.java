package com.lgu.ccss.common.util;

import java.io.IOException;
import java.net.Socket;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509ExtendedTrustManager;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.lgu.ccss.apppush.config.PushConf;
import com.lgu.ccss.common.exception.PushException;

@Component
public class HttpRequest {

	private static final Logger logger = LoggerFactory.getLogger(HttpRequest.class);

	@Autowired
	private PushConf pushConf;

//	@Value("${push.auth}")
//	private String pushAuth;
	
	public String post(String url, String params, String apiId, String accessKey) throws KeyManagementException, NoSuchAlgorithmException, ParseException, IOException, PushException {
		HttpClient client = createDefaultHttpClient();
		try{
			logger.debug("url = " + url);
			logger.debug("param = " + params);


			HttpPost post = new HttpPost("http://" + url);
			post.addHeader("Content-Type","application/json;charset=UTF-8");
			post.addHeader("Accept","application/json");
			post.addHeader("Authorization","auth=" + apiId + ";" + accessKey);
		

			StringEntity entity = new StringEntity(params,"UTF-8");
			post.setEntity(entity);

			HttpResponse response = client.execute(post);
			String result = EntityUtils.toString(response.getEntity());
			post.abort();
			
			if(response.getStatusLine().getStatusCode() == 200){
				//성공
				return result;
			}else{
				//실패
				logger.error(result);
				return result;
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}finally{
			try {
				client.getConnectionManager().shutdown();
			} catch (Exception e) {
				logger.error("e() =" +  e);
				logger.error(e.getMessage(), e);
				throw new PushException();
			}
			
		}
	}
	
	public String postProxy(String url, String params, String apiId, String accessKey, String proxyHost, String proxySubHost, int proxyPort) throws KeyManagementException, NoSuchAlgorithmException, ParseException, IOException, PushException {
		HttpClient client = createDefaultHttpClient();
		try{
			logger.debug("url = " + url);
			logger.debug("param = " + params);


			HttpPost post = new HttpPost("http://" + url);
			post.addHeader("Content-Type","application/json;charset=UTF-8");
			post.addHeader("Accept","application/json");
			post.addHeader("Authorization","auth=" + apiId + ";" + accessKey);
			
			HttpHost proxy = null;
			
			if( proxyHost != null)
			{
				proxy = new HttpHost(proxyHost, proxyPort, HttpHost.DEFAULT_SCHEME_NAME);
				post.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY,proxy);
			}
			
			StringEntity entity = new StringEntity(params,"UTF-8");
			post.setEntity(entity);
			
			HttpResponse response = null;
			try {
				response = client.execute(post);
			}catch (Exception e) {
				logger.error("Proxy Master Server die.");
				proxy = new HttpHost(proxySubHost, proxyPort, HttpHost.DEFAULT_SCHEME_NAME);
				post.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY,proxy);
				response = client.execute(post);
			}
			String result = EntityUtils.toString(response.getEntity());
			post.abort();
			
			if(response.getStatusLine().getStatusCode() == 200){
				//성공
				return result;
			}else{
				//실패
				logger.error(result);
				return result;
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}finally{
			try {
				client.getConnectionManager().shutdown();
			} catch (Exception e) {
				logger.error("e() =" +  e);
				logger.error(e.getMessage(), e);
				throw new PushException();
			}
			
		}
	}
	
	public JsonObject jsonResultParse(String resultJson) throws PushException {
		try {
			JsonObject jsonObject = new JsonParser().parse(resultJson).getAsJsonObject();
			return jsonObject;
		} catch (Exception e) {
			throw new PushException(resultJson);
		}
	}
	
	private HttpClient createDefaultHttpClient() throws KeyManagementException, NoSuchAlgorithmException {
		HttpClient httpClient = new DefaultHttpClient();
		ClientConnectionManager ccm = httpClient.getConnectionManager();
		SSLContext sslContext = SSLContext.getInstance( "SSL" );
		final X509Certificate[] certificates = new X509Certificate[]{};
		TrustManager[] trustAllCerts = new TrustManager[] { new X509ExtendedTrustManager() {
			@Override
			public java.security.cert.X509Certificate[] getAcceptedIssuers() {
				return certificates;
			}
			@Override
			public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
			}
			@Override
			public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
			}
			@Override
			public void checkClientTrusted(X509Certificate[] arg0, String arg1, Socket arg2) throws CertificateException {
			}
			@Override
			public void checkClientTrusted(X509Certificate[] arg0, String arg1, SSLEngine arg2) throws CertificateException {
			}
			@Override
			public void checkServerTrusted(X509Certificate[] arg0, String arg1, Socket arg2) throws CertificateException {
			}
			@Override
			public void checkServerTrusted(X509Certificate[] arg0, String arg1, SSLEngine arg2) throws CertificateException {
			}
	    } };
		
		X509HostnameVerifier verifier = new X509HostnameVerifier() {
			@Override
			public boolean verify(String arg0, SSLSession arg1) {
				return true;
			}
			@Override
			public void verify(String host, String[] cns, String[] subjectAlts) throws SSLException {
			}
			@Override
			public void verify(String host, java.security.cert.X509Certificate cert) throws SSLException {
			}
			@Override
			public void verify(String host, SSLSocket ssl) throws IOException {
			}
		};

	    sslContext.init( null, trustAllCerts, new java.security.SecureRandom() );
	    org.apache.http.conn.ssl.SSLSocketFactory ssl = new org.apache.http.conn.ssl.SSLSocketFactory(sslContext, verifier);
		
		SchemeRegistry sr = ccm.getSchemeRegistry();
		sr.register(new Scheme("https", 443, ssl));
		httpClient.getParams().setParameter("http.protocol.expect-continue", false);
		int time = Integer.parseInt(pushConf.getConnectionTimeout());
		httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, time);
		httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, time);
		return httpClient;
	}
}
