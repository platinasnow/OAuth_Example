package org.oauth.example.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class LoginController {

	@RequestMapping("/loginFailure")
	public String loginFailure(@RequestParam String error, @RequestParam String url, RedirectAttributes redirectAttributes) {
		
		String errorMessage;
		if ("null".equals(error) ||error == null) {
			errorMessage = "아이디가 존재하지 않습니다";
		}else if ("Bad credentials".equals(error)) {
			errorMessage = "비밀번호가 잘못되었습니다.";
		} else if ("User is disabled".equals(error)) {
			errorMessage = "사용권한이 없습니다.";
		} else {
			errorMessage = error;
		}
		redirectAttributes.addFlashAttribute("errorMessage", errorMessage);
		return "redirect:login";
	}
	
	@RequestMapping("/interceptFailure")
	public String interceptFailure(@RequestParam String error, @RequestParam String url, RedirectAttributes redirectAttributes){
		String errorMessage ="로그인에 실패하였습니다.";
		redirectAttributes.addFlashAttribute("errorMessage", errorMessage);
		return "redirect:login";
	}
	
	@RequestMapping("/accessDenied")
	public String accessDenied(@RequestParam String error, @RequestParam String url, RedirectAttributes redirectAttributes) {
		String errorMessage = "권한이 없습니다.";
		redirectAttributes.addFlashAttribute("errorMessage", errorMessage);
		return "redirect:login";
	}
	
	@RequestMapping("/loginSuccess")
	public String loginSuccess(@RequestParam String error, @RequestParam String url, RedirectAttributes redirectAttributes) {
		String message = "로그인하였습니다.";
		redirectAttributes.addFlashAttribute("errorMessage", message);
		return "redirect:login";
	}
	
}
