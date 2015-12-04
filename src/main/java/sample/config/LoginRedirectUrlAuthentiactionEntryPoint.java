package sample.config;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.web.util.UriComponentsBuilder;

public class LoginRedirectUrlAuthentiactionEntryPoint extends
		LoginUrlAuthenticationEntryPoint {

	String targetUrlParameter;

	public LoginRedirectUrlAuthentiactionEntryPoint(String loginFormUrl,
			String targetUrlParameter) {
		super(loginFormUrl);
		this.targetUrlParameter = targetUrlParameter;
	}

	@Override
	protected String determineUrlToUseForThisRequest(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) {
		String encodedUri = "";
		try {
			boolean hasQuery = request.getQueryString() != null && !request.getQueryString().isEmpty();
			encodedUri = UriComponentsBuilder.fromUriString(getLoginFormUrl() + "?" + targetUrlParameter + "={url}")
			  .buildAndExpand(request.getRequestURL()+ (hasQuery ? "?"+request.getQueryString() : ""))
			  .encode("UTF-8")
			  .toString();
		} catch (UnsupportedEncodingException e) {}
		
		return encodedUri;
	}
}