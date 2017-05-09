package participationSystem;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import common.dto.User;
import common.persistence.CommonPersistence;

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
		auth.inMemoryAuthentication().withUser("user@example.com").password("password").roles("USER");

		List<User> users = CommonPersistence.getUserDao().findAllUsers();

		for (User u : users) {
			auth.inMemoryAuthentication().withUser(u.getEmail()).password(u.getPassword()).roles("USER");
		}

		auth.inMemoryAuthentication().withUser("admin").password("password").roles("ADMIN");
	}
}