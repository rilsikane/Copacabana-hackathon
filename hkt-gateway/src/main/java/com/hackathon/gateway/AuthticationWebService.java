package com.hackathon.gateway;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hackathon.auth.AuthticationManager;
import com.hackathon.dto.DataResponse;
import com.hackathon.utils.GWSConstants;
import com.hackathon.utils.URLUtils;



@RestController
@RequestMapping("/gateway")
public class AuthticationWebService extends AbstractBaseWebService {

	@Autowired
	private AuthticationManager authticationManager;

	@ResponseBody
	@RequestMapping(value = "/authen/login.service", method = { RequestMethod.POST }, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE + GWSConstants.SEMI_COLON + GWSConstants.CHARSET_UTF_8)
	public Object login(@RequestBody Object params, @RequestHeader HttpHeaders headers, HttpServletRequest request) throws Exception {
		final String url = URLUtils.getEnpointService(GWSConstants.LOGIN_TARGET);
		DataResponse dataResponse = makePostRequest(url, params, headers, Map.class);
		if (StringUtils.equalsIgnoreCase(dataResponse.getStatus(), GWSConstants.RESPONSE_STATUS.SUCCESS)) {
			Map<String, String> map = (Map<String, String>) dataResponse.getResult();
			String userID = map.get("userID");
			if (StringUtils.isNotEmpty(userID)) {
				String token = authticationManager.createTokenKey(map.get("userID"));
				map.put(GWSConstants.TOKEN, token);
			}
		}
		return dataResponse;
	}

	@RequestMapping(value = "/services/logout.service", method = { RequestMethod.POST }, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE + GWSConstants.SEMI_COLON + GWSConstants.CHARSET_UTF_8)
	public Object logout(@RequestBody Object params, @RequestHeader HttpHeaders headers, HttpServletRequest request) throws Exception {
		final String user = BeanUtilsBean.getInstance().getProperty(params, GWSConstants.USER);
		DataResponse dataResponse = new DataResponse();
		try {
			authticationManager.removeToken(user);
			dataResponse.setResult(GWSConstants.FLAG.Y);
		} catch (Exception e) {
			dataResponse.setStatus(GWSConstants.RESPONSE_STATUS.ERROR);
			dataResponse.setErrorMsg(GWSConstants.MESSAGE.REQUEST_TO_DESCTINATION_SERVICE_ERROR);
		}
		return dataResponse;
	}
}
