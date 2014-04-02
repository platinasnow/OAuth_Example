package org.oauth.example.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpSession;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.OAuthProvider;
import oauth.signpost.basic.DefaultOAuthConsumer;
import oauth.signpost.basic.DefaultOAuthProvider;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import oauth.signpost.exception.OAuthNotAuthorizedException;

import org.oauth.example.model.Users;
import org.oauth.example.service.UsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.authentication.UserServiceBeanDefinitionParser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	private static final String callbackUrl = "http://121.129.12.229:9090/example/callback";
	private static final String requestTokenUrl = "https://nid.naver.com/naver.oauth?mode=req_req_token";
	private static final String authorizeUrl = "https://nid.naver.com/naver.oauth?mode=auth_req_token";
	private static final String accessTokenUrl = "https://nid.naver.com/naver.oauth?mode=req_acc_token";
	private static final String consumerKey = "SJD8PX6YV2RB";
	private static final String consumerSecret = "525A553FpVysctB2Od6I";

	@Autowired
	private UsersService usersService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		
		return "home";
	}
	
	@RequestMapping(value="/login")
	public String login(Model model){
		return "login";
	}
	
	@RequestMapping(value="/fb")
	public String facebook(Model model){
		return "facebook";
	}
	
	@RequestMapping(value="/json")
	public String json(Model model){
		return "json";
	}
	

	@RequestMapping(value = "/naverLogin")
	public String naverLogin(HttpSession session) throws OAuthMessageSignerException, OAuthNotAuthorizedException, OAuthExpectationFailedException,
			OAuthCommunicationException {
		OAuthConsumer consumer = new DefaultOAuthConsumer(consumerKey, consumerSecret);
		OAuthProvider provider = new DefaultOAuthProvider(requestTokenUrl, accessTokenUrl, authorizeUrl);
		String oAuthUrl = provider.retrieveRequestToken(consumer, callbackUrl);
		String requestToken = consumer.getToken();
		String requestTokenSecret = consumer.getConsumerSecret();

		session.setAttribute("request_token", requestToken);
		session.setAttribute("request_token_secret", requestTokenSecret);
		return "redirect:" + oAuthUrl;
	}

	@RequestMapping("/callback")
	public String callback(@RequestParam String oauth_token, @RequestParam String oauth_verifier, HttpSession session)
			throws OAuthMessageSignerException, OAuthNotAuthorizedException, OAuthExpectationFailedException, OAuthCommunicationException {
		OAuthConsumer consumer = new DefaultOAuthConsumer(consumerKey, consumerSecret);

		String requestToken = (String) session.getAttribute("request_token");
		String requestTokenSecret = (String) session.getAttribute("request_token_secret");
		consumer.setTokenWithSecret(requestToken, requestTokenSecret);

		System.out.println("oauth_verifier :" + oauth_verifier);
		System.out.println("oauth_token :" + oauth_token);
		System.out.println("requestToken :" + requestToken);
		System.out.println("requestTokenSecret :" + requestTokenSecret);

		OAuthProvider provider = new DefaultOAuthProvider(requestTokenUrl, accessTokenUrl, authorizeUrl);
		provider.setOAuth10a(true);
		provider.retrieveAccessToken(consumer, oauth_verifier);
		
		String accessToken = consumer.getToken();
		String aceessTokenSecret = consumer.getConsumerSecret();
		session.setAttribute("access_token", accessToken);
		session.setAttribute("aceess_token_secret", aceessTokenSecret);

		System.out.println("aceessTokenSecret :" + aceessTokenSecret);
		System.out.println("accessToken :" + accessToken);

		return "redirect:/";
	}
	
	@RequestMapping(value = "/ajax.js")
	@ResponseBody
	public Object ajaxTest(Users users){
		Map<String, Object> object = new HashMap<String, Object>();
		object.put("list", users);
		return object;
	} 

}
