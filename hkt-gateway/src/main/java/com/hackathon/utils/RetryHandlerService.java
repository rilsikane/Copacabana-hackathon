package com.hackathon.utils;

import org.springframework.stereotype.Component;

@Component
public class RetryHandlerService {

	public <T> T execute(Retry<T> retry) throws Exception {
		return retry.call();
		/*
		final int maxRetry = 3;
		for (int count = 1; count <= maxRetry; count++) {
			try {
				return retry.retry();
			} catch (ConnectException e) {
				Thread.sleep(1000);
				if (count >= maxRetry) {
					throw e;
				}
			} catch (Exception e) {
				throw e;
			}
		}
		return null;
		*/
	}

	public static interface Retry<T> {
		public T call() throws Exception;
	}
}
