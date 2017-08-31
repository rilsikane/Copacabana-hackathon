package com.hackathon.gateway;

import java.security.spec.AlgorithmParameterSpec;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hackathon.utils.GWSConstants;
import com.hackathon.dto.DataResponse;
import com.hackathon.dto.GetRequest;
import com.hackathon.utils.URLUtils;

@RestController
@RequestMapping("/gateway/service")
public class GatewayWebService extends AbstractBaseWebService {
	
	protected static final Logger LOG = Logger.getLogger(GatewayWebService.class);
	
	@ResponseBody
	@RequestMapping(value = "/post.service", method = { RequestMethod.POST }, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE + GWSConstants.SEMI_COLON + GWSConstants.CHARSET_UTF_8)
	public Object post(@RequestBody String params, @RequestHeader HttpHeaders headers, HttpServletRequest request) throws Exception {
		String decodedParam = params;
		
		LOG.info("Param : " + decodedParam);
		Object objParam = mapper.readValue(decodedParam, Object.class);
		final String target = BeanUtilsBean.getInstance().getProperty(objParam, GWSConstants.TARGET);
		final String url = URLUtils.getEnpointService(target);
		return super.makePostRequest(url, decodedParam, headers, String.class);
	}

	@ResponseBody
	@RequestMapping(value = "/get.service", method = { RequestMethod.POST }, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE + GWSConstants.SEMI_COLON + GWSConstants.CHARSET_UTF_8)
	public Object get(@RequestBody GetRequest params, @RequestHeader HttpHeaders headers, HttpServletRequest request) throws Exception {
		final String url = URLUtils.getEnpointService(params.getTarget());
		return super.makeGetRequest(url, params, headers, String.class);
	}

	@ResponseBody
	@RequestMapping(value = "/get-file.service", method = { RequestMethod.POST }, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Object getFile(@RequestBody Object params, @RequestHeader HttpHeaders headers, HttpServletRequest request) throws Exception {
		final String target = BeanUtilsBean.getInstance().getProperty(params, GWSConstants.TARGET);
		final String url = URLUtils.getEnpointService(target);
		
		DataResponse result = super.makePostRequest(url, params, headers, String.class);
		return result;
	}

	@ResponseBody
	@RequestMapping(value = "/post-file.service", method = { RequestMethod.POST }/*, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}*/)
	public Object postFile(@RequestParam("files") MultipartFile multipartFile, @RequestParam Map<String, String> params, @RequestHeader HttpHeaders headers, HttpServletRequest request) throws Exception {
		final String target = BeanUtilsBean.getInstance().getProperty(params, GWSConstants.TARGET);
		final String url = URLUtils.getEnpointService(target);
		return super.makePostUploadRequest(url, params, multipartFile, headers, String.class);
	}
	
	
	public static String encrypt(String key, String initVector, String value) {
        try {
            IvParameterSpec iv = new IvParameterSpec(DatatypeConverter.parseHexBinary("62c8d81002290b805401d0d5e64fb93a"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

            byte[] encrypted = cipher.doFinal(value.getBytes());
            System.out.println("encrypted string: "
                    + Base64.encodeBase64String(encrypted));

            return Base64.encodeBase64String(encrypted);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public static String decrypt(String encrypted) {
    	 try {
 		 	SecretKey key = new SecretKeySpec(Base64.decodeBase64("u/Gu5posvwDsXUnV5Zaq4g=="), "AES");
 	        AlgorithmParameterSpec iv = new IvParameterSpec(Base64.decodeBase64("5D9r9ZVzEYYgha93/aUK2w=="));
 	        byte[] decodeBase64 = Base64.decodeBase64(encrypted);

 	        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
 	        cipher.init(Cipher.DECRYPT_MODE, key, iv);

 	        return (new String(cipher.doFinal(decodeBase64), "UTF-8"));
 	    } catch (Exception e) {
 	        throw new RuntimeException("This should not happen in production.", e);
 	    }

    }
    
}
