package com.hackathon.utils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.springframework.http.client.SimpleClientHttpRequestFactory;

public class SSLUtil {

	private static final HostnameVerifier HOST_VERIFIER = new HostnameVerifier() {
		public boolean verify(final String hostname, final SSLSession session) {
			return true;
		}
	};

	public static class MySimpleClientHttpRequestFactory extends SimpleClientHttpRequestFactory {

		@Override
		protected void prepareConnection(final HttpURLConnection connection, final String httpMethod) throws IOException {
			if (connection instanceof HttpsURLConnection) {
				((HttpsURLConnection) connection).setHostnameVerifier(HOST_VERIFIER);
				((HttpsURLConnection) connection).setSSLSocketFactory(getSSLSocketFactory());
			}
			super.prepareConnection(connection, httpMethod);
		}
	}

	public static SimpleClientHttpRequestFactory getSimpleClientHttpRequestFactory() {
		return new MySimpleClientHttpRequestFactory();
	}

	public static SSLContext getSSLContext() {
		try {
			SSLContext ctx = SSLContext.getInstance("TLS");
			X509TrustManager tm = new X509TrustManager() {
				public void checkClientTrusted(X509Certificate[] xcs, String string) throws CertificateException {
				}

				public void checkServerTrusted(X509Certificate[] xcs, String string) throws CertificateException {
				}

				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}
			};
			ctx.init(null, new TrustManager[] { tm }, null);
			return ctx;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public static SSLSocketFactory getSSLSocketFactory() {
		return getSSLContext().getSocketFactory();
	}

}
