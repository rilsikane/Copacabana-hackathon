package com.hackathon.gateway;

import java.io.IOException;
import java.util.Map;

import javax.xml.ws.WebServiceException;

import org.apache.commons.lang.time.StopWatch;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hackathon.dto.DataResponse;
import com.hackathon.dto.GetRequest;
import com.hackathon.utils.GWSConstants;
import com.hackathon.utils.ObjectUtils;
import com.hackathon.utils.RestClient;	
import com.hackathon.utils.RetryHandlerService;
import com.hackathon.utils.RetryHandlerService.Retry;

public abstract class AbstractBaseWebService {
	protected static final Logger LOG = Logger.getLogger(AbstractBaseWebService.class);

	private boolean useWatch = true;

	@Autowired
	protected RestClient restClient;

	@Autowired
	protected ObjectMapper mapper;

	@Autowired
	protected RetryHandlerService retryHandlerService;

	public void onProcessStart(StopWatch stopWatch, Logger logger) {
		this.onProcessStart(stopWatch, true, logger);
	}

	public void onProcessStart(StopWatch stopWatch, boolean useWatch, Logger logger) {
		this.useWatch = useWatch;
		if (this.useWatch) {
			stopWatch.start();
		}
		this.displayInfoStartEnd("START", logger);
	}

	public void onProcessComplete(StopWatch stopWatch, Logger logger) {
		try {
			//
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (useWatch) {
				logger.debug("Total Time : " + stopWatch.getTime() + " ms.");
				stopWatch.stop();
				stopWatch = null;
			}
			this.displayInfoStartEnd("END", logger);
		}
	}

	private void displayInfoStartEnd(String info, Logger logger) {
		logger.info("################################# " + info + " ################################");
	}

	public void onException(String msg, Throwable e, Logger logger) {
		logger.error(msg + " : " + e.getMessage());
		e.printStackTrace();
	}

	public void onException(Throwable e, Logger logger) {
		logger.error(e.getMessage());
	}

	protected DataResponse makePostRequest(final String url, final Object params, final HttpHeaders headers, final Class<?> clazz) throws IOException, Exception {
		StopWatch stopWatch = new StopWatch();
		this.onProcessStart(stopWatch, LOG);
		DataResponse dataResponse = new DataResponse();
		try {
			Object result = retryHandlerService.execute(new Retry<Object>() {
				@Override
				public Object call() throws Exception {
					return restClient.post(url, headers, params, clazz);
				}
			});
			dataResponse.setResult(result);
			dataResponse.setStatus(GWSConstants.RESPONSE_STATUS.SUCCESS);
			return dataResponse;
		} catch (WebServiceException we) {
			dataResponse.setStatus(GWSConstants.RESPONSE_STATUS.ERROR);
			dataResponse.setErrorMsg(GWSConstants.MESSAGE.REQUEST_TO_DESCTINATION_SERVICE_ERROR);
			this.onException(GWSConstants.MESSAGE.REQUEST_TO_DESCTINATION_SERVICE_ERROR, we, LOG);
			return dataResponse;
		} catch (Exception e) {
			e.printStackTrace();
			dataResponse.setStatus(GWSConstants.RESPONSE_STATUS.ERROR);
			dataResponse.setErrorMsg(GWSConstants.MESSAGE.INTERNAL_SERVICE_ERROR);
			this.onException(GWSConstants.MESSAGE.INTERNAL_SERVICE_ERROR, e, LOG);
			return dataResponse;
		} finally {
			dataResponse.setReqTime(stopWatch.getTime());
			this.onProcessComplete(stopWatch, LOG);
			ObjectUtils.destroyObject(dataResponse);
		}
	}

	protected DataResponse makePostUploadRequest(final String url, final Map<String, String> params, final MultipartFile multipartFile, final HttpHeaders headers, final Class<?> clazz) throws IOException, Exception {
		StopWatch stopWatch = new StopWatch();
		this.onProcessStart(stopWatch, LOG);
		DataResponse dataResponse = new DataResponse();
		try {
			Object result = retryHandlerService.execute(new Retry<Object>() {
				@Override
				public Object call() throws Exception {
					return restClient.postUpload(url, headers, params, multipartFile, clazz);
				}
			});
			dataResponse.setResult(result);
			dataResponse.setStatus(GWSConstants.RESPONSE_STATUS.SUCCESS);
			return dataResponse;
		} catch (WebServiceException we) {
			dataResponse.setStatus(GWSConstants.RESPONSE_STATUS.ERROR);
			dataResponse.setErrorMsg(GWSConstants.MESSAGE.REQUEST_TO_DESCTINATION_SERVICE_ERROR);
			this.onException(GWSConstants.MESSAGE.REQUEST_TO_DESCTINATION_SERVICE_ERROR, we, LOG);
			return dataResponse;
		} catch (Exception e) {
			dataResponse.setStatus(GWSConstants.RESPONSE_STATUS.ERROR);
			dataResponse.setErrorMsg(GWSConstants.MESSAGE.INTERNAL_SERVICE_ERROR);
			this.onException(GWSConstants.MESSAGE.INTERNAL_SERVICE_ERROR, e, LOG);
			return dataResponse;
		} finally {
			dataResponse.setReqTime(stopWatch.getTime());
			this.onProcessComplete(stopWatch, LOG);
			ObjectUtils.destroyObject(dataResponse);
		}
	}

	protected DataResponse makeGetRequest(final String url, final GetRequest params, final HttpHeaders headers, final Class<?> clazz) throws Exception {
		StopWatch stopWatch = new StopWatch();
		this.onProcessStart(stopWatch, LOG);
		DataResponse dataResponse = new DataResponse();
		try {
			Object result = retryHandlerService.execute(new Retry<Object>() {
				@Override
				public Object call() throws Exception {
					return restClient.get(url, headers, clazz);
				}
			});
			dataResponse.setResult(result);
			dataResponse.setStatus(GWSConstants.RESPONSE_STATUS.SUCCESS);

			return dataResponse;
		} catch (WebServiceException we) {
			dataResponse.setStatus(GWSConstants.RESPONSE_STATUS.ERROR);
			dataResponse.setErrorMsg(GWSConstants.MESSAGE.REQUEST_TO_DESCTINATION_SERVICE_ERROR);
			this.onException(GWSConstants.MESSAGE.REQUEST_TO_DESCTINATION_SERVICE_ERROR, we, LOG);
			return dataResponse;
		} catch (Exception e) {
			dataResponse.setStatus(GWSConstants.RESPONSE_STATUS.ERROR);
			dataResponse.setErrorMsg(GWSConstants.MESSAGE.INTERNAL_SERVICE_ERROR);
			this.onException(GWSConstants.MESSAGE.INTERNAL_SERVICE_ERROR, e, LOG);
			return dataResponse;
		} finally {
			dataResponse.setReqTime(stopWatch.getTime());
			this.onProcessComplete(stopWatch, LOG);
			ObjectUtils.destroyObject(dataResponse);
		}
	}

}
