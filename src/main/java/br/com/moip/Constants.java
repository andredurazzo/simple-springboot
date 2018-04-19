package br.com.moip;

import java.nio.charset.Charset;

import org.springframework.http.MediaType;

public class Constants {
	public static final String ENVIRONMENT_DB_USER_PARAM = "DATABASE_USERNAME";
	public static final String ENVIRONMENT_DB_PASSWORD_PARAM = "DATABASE_PASSWORD";
	public static final String ENVIRONMENT_DB_USER_INFO = "/etc/moip/username";
	public static final String ENVIRONMENT_DB_PASSWORD_INFO = "/etc/moip/password";

	public static final String ENVIRONMENT_MONGODB_USER_PARAM = "MONGODB_USERNAME";
	public static final String ENVIRONMENT_MONGODB_PASS_PARAM = "MONGODB_PASSWORD";
	public static final String ENVIRONMENT_MONGODB_DATABASE_PARAM = "MONGODB_DATABASE";
	public static final String ENVIRONMENT_MONGODB_USER_INFO = "/etc/credentials/mongo-db/username";
	public static final String ENVIRONMENT_MONGODB_PASS_INFO = "/etc/credentials/mongo-db/password";
	public static final String ENVIRONMENT_MONGODB_DATABASE_INFO = "MOIP-CHALLENGE";

	public static final Integer CODE_SUCCESS = 0;
	public static final Integer CODE_NOT_FOUND = 1;
	public static final Integer CODE_MISSING_PARAMETER = 8;
	public static final Integer CODE_INVALID_DATA = 9;
	public static final Integer CODE_FATAL_ERROR = 500;

	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

}
