package com.hackathon.utils;

public interface GWSConstants {
	public static interface CONFIG_FILE {
		public static final String APP_CONF_IN_FILE = "/app.properties";
	}

	public static interface RESULT_KEY {
		public static final int SUCCESS = 1;
		public static final int ERROR = 0;
	}

	public static interface CONFIG_KEY {
		public static final String CONFIGURATION_DIR = "configuration.dir";

		public static final String ENPOINT_SERVICE_URL = "jndi/endpoint";
	}

	public static interface FLAG {

		public static final String A = "A";
		public static final String Y = "Y";
		public static final String N = "N";
		public static final String C = "C";
		public static final String S = "S";
		public static final String E = "E";
		public static final String D = "D";
	}

	public static interface ENCODING {

		public final static String UTF_8 = "UTF-8";
		public final static String TIS_620 = "TIS-620";
	}

	public static interface FILE_EXTENSION {

		public final static String PDF = ".pdf";
		public final static String JASPER = ".jasper";
	}

	public static interface FILE_TYPE {

		public final static String PDF = "PDF";
	}

	public static final String TRUE_STRING = "TRUE";
	public static final String FALSE_STRING = "FALSE";
	public static final String CHARSET_UTF_8 = "charset=UTF-8";

	public static final String PIPE = "|";
	public static final String DOT = ".";
	public static final String SLASH = "/";
	public static final String COMMA = ",";
	public static final String COLON = ":";
	public static final String SEMI_COLON = ";";
	public static final String UNDER_SCORE = "_";
	public static final String BETWEEN_DATA = "[#]";
	public static final String SEMICOLON = ";";

	public static final String TARGET = "target";

	public static interface MESSAGE {

		public static final String INTERNAL_SERVICE_ERROR = "Internal Service Error!.";
		public static final String REQUEST_TO_DESCTINATION_SERVICE_ERROR = "Request to Desctination Service Error!.";
	}

	public static interface RESPONSE_STATUS {

		public static final String SUCCESS = "S";
		public static final String ERROR = "E";
	}

	public static class FILE_RETURN_TYPE {
		public final static String STREAM = "stream";
		public final static String STRING_BASE_64 = "stringBase64";
	}

	public static String FILE_RESPONSE = "fileResponse";
	public static String TOKEN = "token";
	public static String USER = "userId";
	public static String STATUS = "loginStatus";

	public static final String LOGIN_TARGET = "login";
	public static final String GET_KEY_TARGET = "login-service/get-key.service";
	public static final String ANNOUNCEMENT = "announcement-service/newannouncement.service";

	public static interface HEADER {

		public static final String APPLICATION_JSON = "Content-type=application/json";
	}

}
