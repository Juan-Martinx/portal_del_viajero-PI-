package com.pdv.oauth.controller;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pdv.oauth.commons.PathCommons;

@Controller
@RequestMapping(PathCommons.LOGIN_ENTRYPOINT)
public class LoginController {

	/**
	 * Login de la app
	 * @return
	 */
    @GetMapping("/login")
    public String login() {
        return "login";
    }

	/**
	 * Logout de la app
	 * @return
	 */
    @GetMapping("/logout")
    public String logout() {
        return "logout";
    }

/**
 * Logout exitoso de la app
 * @param http
 * @return
 * @throws Exception
 */
    @PostMapping("/logout")
    public String logoutOK(HttpSecurity http) throws Exception {
    	http.logout(logout -> 
    		logout.logoutSuccessUrl("login?logout")
    			.deleteCookies("JSESSIONID")
    			.invalidateHttpSession(true)
    			.clearAuthentication(true));
    	return "login?logout";
    }

}