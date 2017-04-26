package participationSystem.hello;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import participationSystem.dto.User;
import participationSystem.persistence.Persistence;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/", "/home").authenticated().and().formLogin().loginPage("/login")
				.permitAll().and().logout().permitAll();

		http.csrf().disable();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		// auth
		// .inMemoryAuthentication()
		// .withUser("user").password("password").roles("USER");
		//
		// List<String> emails = Persistence.getUserDao().findAllEmails();
		//
		// for(String email : emails) {
		// auth
		// .inMemoryAuthentication()
		// .withUser(email).password("password").roles("USER");
		// }

		List<User> users = Persistence.getUserDao().findAllUsers();

		for (User u : users) {
			auth.inMemoryAuthentication().withUser(u.getEmail()).password(u.getPassword()).roles("USER");
		}

		auth.inMemoryAuthentication().withUser("admin").password("password").roles("ADMIN");
	}
}