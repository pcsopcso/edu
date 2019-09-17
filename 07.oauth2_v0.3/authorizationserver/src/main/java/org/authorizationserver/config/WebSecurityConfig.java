package org.authorizationserver.config;

import java.io.PrintWriter;
import java.util.Arrays;

import org.authorizationserver.security.CustomAuthenticationFailureHandler;
import org.authorizationserver.security.CustomAuthenticationSuccessHandler;
import org.authorizationserver.security.ResourceOwnerAuthenticationFilter;
import org.authorizationserver.security.ResourceOwnerAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * 
 * @author daniel park
 *
 */
// 1. 스프링 시큐리티을 사용하겠다는 선언
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private PasswordEncoder passwordEncoder;

	/*
     * 2. 인증을 담당할 프로바이더 구현체를 설정하는 메소드이다
     */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		// AuthenticationProvider 구현체을 인자로 넘겨준다.
		auth.authenticationProvider(authenticationProvider());
	}

	/*
     * 3. 스프링 시큐리티 룰을 무시하게 하는 Url 규칙 설정
     */
	@Override
	public void configure(WebSecurity web) throws Exception {

		web.ignoring().antMatchers("/css/**").antMatchers("/vendor/**").antMatchers("/js/**")
				.antMatchers("/favicon*/**").antMatchers("/img/**").antMatchers("/console/**");
	}


	/*
     * 4. 요청 URI에 대한 권한 설정 (가장 중요한 메소드)
	 *    특정 결과에 대한 Handler 등록
	 *    Custom Filter 등록(ex. AuthenticaionFilter 요정의)
	 *    예외 핸요러 등을 등록 하는 메소드
     */
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.cors().and().authorizeRequests()
				// 보호된 리소스 URI에 접근할 수 있는 권한을 설정해주는 설정
				// AccessDecisionManager에 설정되는 access 정보
				// 추후 FilterSecurityInterceptor에서 권한 인증에 사용될 정보
				.antMatchers("/login*/**").permitAll()
				.antMatchers("/error**").permitAll()
				.anyRequest().authenticated()
				// csrf().disable()을 통하여 csrf 보안 설정을 비활성화한다.
				// 해당 기능을 사용하기 위해서는 프론트에서 csrf토큰값을 보내주어야함
				.and().csrf().disable()
				// Form Login에 사용되는 custom AuthenticationFilter 구현체를 등록
				.addFilter(authenticationFilter())
				// 인증되지 않은 사용자가 보호된 리소스에 접근하였을 때 수행되는 핸들러(EntryPoin)
				// securitycontext에 내용이 없을 경우
				.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint())
				// accessDeniedHandler()는 권한 체크에서 실패할 때 수행되는 핸들러를 등록
				.accessDeniedHandler((request, response, exception) -> {
					response.setContentType("application/json;charset=UTF-8");
					response.setHeader("Cache-Control", "no-cache");

					PrintWriter writer = response.getWriter();
					writer.println(new AccessDeniedException("access denied !"));
				});
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("*"));
		configuration.setAllowedMethods(Arrays.asList("*"));
		configuration.setAllowedHeaders(Arrays.asList("*"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);

		return source;
	}

	/*
	 *  2.1 AuthenticationProvider 구현체(DaoAuthenticationProvider)
	 */	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService);
		authenticationProvider.setPasswordEncoder(passwordEncoder);

		return authenticationProvider;
	}

	/*
	*  5 AuthenticationFilter를 설정 하는 메소드
	*  AuthenticationManager에게 위임한다(AuthenticationManager를 생성자로 전달)
	*  setFilterProcessesUrl()을 통해 로그인 요청 URI를 정의(스프링이 제공하므로 따로 컨트롤러 등록할 필요 없음)
	*  logout URI도 동일하게 스프링에서 제공(SuccessHandler나 FailureHandler,EntryPoint는 컨트롤러에 등록된 URI로 설정) 
	*  setUsernameParameter(),setPasswordParameter()를 통해 폼으로 넘어오는 사용자 아이디,패스워드 변수값을 설정(<input>의 name속성)
	*  setAuthenticationSuccess,FailureHandler()를 통해 결과에 대해 수행할 핸들러 등록
	*/	
	@Bean
	public ResourceOwnerAuthenticationFilter authenticationFilter() throws Exception {
		ResourceOwnerAuthenticationFilter filter = new ResourceOwnerAuthenticationFilter(authenticationManager());
		filter.setFilterProcessesUrl("/login");
		filter.setUsernameParameter("username");
		filter.setPasswordParameter("password");

		filter.setAuthenticationSuccessHandler(authenticationSuccessHandler());
		filter.setAuthenticationFailureHandler(authenticationFailureHandler());

		filter.afterPropertiesSet();

		return filter;
	}

	/*
	 * 인증된 이후의 요청에 대해 Header 인증
	*/
	@Bean
    public ResourceOwnerAuthorizationFilter authorizationFilter() throws Exception {
        ResourceOwnerAuthorizationFilter authorizationFilter = new ResourceOwnerAuthorizationFilter(authenticationManager());
        return authorizationFilter;
    }

	/*
	 *	 SecurityContext에 존재하지도 않고, 인증되지 않은 익명의 사용자가 보호된 리소스에 접근했을 때 수행되는 EntryPoint
	 */

	@Bean
	public AuthenticationEntryPoint authenticationEntryPoint() {
		return new LoginUrlAuthenticationEntryPoint("/loginPage");
	}

	// 성공시 인덱스 페이지로 리다리렉트 (굳이 구현 하지 않아도 됨)
	@Bean
	public AuthenticationSuccessHandler authenticationSuccessHandler() {
		CustomAuthenticationSuccessHandler successHandler = new CustomAuthenticationSuccessHandler();
		successHandler.setDefaultTargetUrl("/index");

		return successHandler;
	}

	@Bean
	public AuthenticationFailureHandler authenticationFailureHandler() {
		CustomAuthenticationFailureHandler failureHandler = new CustomAuthenticationFailureHandler();
		failureHandler.setDefaultFailureUrl("/loginPage?error=loginfail");

		return failureHandler;
	}
}
