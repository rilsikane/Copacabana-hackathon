package com.hackathon.auth;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hackathon.cache.CacheConstants;
import com.hackathon.cache.CacheManager;
import com.hackathon.utils.GWSConstants;
import com.hackathon.dto.AuthDto;
import com.hackathon.utils.CacheUtils;
import com.hackathon.utils.DateUtil;
import com.hackathon.utils.EncryptionUtils;
import com.hackathon.utils.RestClient;
import com.hackathon.utils.URLUtils;

@Component
public class AuthticationManager {

	@Autowired
	private RestClient restClient;

	public String getKey() throws Exception {
		String key = (String) CacheUtils.getAuthKey().getCache(CacheConstants.ENCRYPT_KEY);
		if (StringUtils.isEmpty(key)) {
			key = loadKey();
		}
		return key;
	}

	private String loadKey() throws Exception {
		//String url = URLUtils.getEnpointService(GWSConstants.GET_KEY_TARGET);
		String key = "hackathon";
		if (StringUtils.isNotEmpty(key)) {
			CacheUtils.getAuthKey().putCache(CacheConstants.ENCRYPT_KEY, key);
		}
		return key;
	}

	private int randomValue() {
		Random rand = new Random();
		int value = rand.nextInt(10000);
		return value;
	}

	public String createTokenKey(String userName) throws Exception {
		String key = getKey();
		Date currentDate = DateUtil.getCurrentDate();
		String dateToString = DateUtil.toStringEngDateBySimpleFormat(currentDate, DateUtil.ALL_DATETIME_PATTERN);

		int value = randomValue();
		String token = EncryptionUtils.encrypt(userName + GWSConstants.BETWEEN_DATA + dateToString + value, key);
//		CacheManager.getInstance().putCache(getFqnAuth(userName), userName, new AuthDto(userName, token));
		CacheUtils.getAuthKey().putCache(userName, new AuthDto(userName, token));
		return token;
	}

	public  void removeToken(String userName) {
		CacheUtils.getAuthKey().remove(userName);
	}

	public boolean checkUserAuthData(String token) throws Exception {
		AuthDto cacheData = getUserAuthDataByToken(token);
		if (cacheData != null && StringUtils.equals(cacheData.getToken(), token)) {
			return true;
		}
		return false;
	}

	public AuthDto getUserAuthDataByToken(String token) throws Exception {
		try {
			if (StringUtils.isNotEmpty(token)) {
				String key = (String) CacheUtils.getAuthKey().getCache(CacheConstants.ENCRYPT_KEY);
				String decoded = EncryptionUtils.decrypt(token, key);
				if (StringUtils.isNotEmpty(decoded)) {
					String[] dataArray = StringUtils.split(decoded, GWSConstants.BETWEEN_DATA);
					if (!ArrayUtils.isEmpty(dataArray)) {
						String userName = dataArray[0];
						return getUserAuthDataByUserName(userName);
					}
				}
			}
			return null;
		} catch (Exception e) {
			throw e;
		}
	}

//	public void initAuthenData(String userName, AuthDto authDto) {
//		CacheManager.getInstance().putCache(getFqnAuth(userName), userName, authDto);
//	}

	public AuthDto getUserAuthDataByUserName(String userName) {
		return (AuthDto) CacheUtils.getAuthKey().getCache( userName);
	}


	public long toMilliSeconds(long day) {
		return TimeUnit.DAYS.toMillis(day); // return day * 24 * 60 * 60 * 1000;
	}

//	public void printAuthNode() {
//		Node<String, Object> nodes = CacheManager.getInstance().getCache().getNode(Fqn.fromString(CacheConstants.TOKEN_ROOT_KEY));
//
//		for (Object key : nodes.getChildrenNames()) {
//			AuthDto cacheData = (AuthDto) CacheManager.getInstance().getCache().get(getFqnAuth(key.toString()), key.toString());
//			System.out.println(cacheData.getUserName() + ", " + cacheData.getToken());
//		}
//
//	}

}
