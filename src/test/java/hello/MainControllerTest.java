package hello;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;
import main.Application;

import java.net.URL;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest({ "server.port=0" })
public class MainControllerTest {

	@Value("${local.server.port}")
	private int port;

	private URL base;
	private RestTemplate template;

	@Before
	public void setUp() throws Exception {
		this.base = new URL("http://localhost:" + port + "/");
		template = new TestRestTemplate();
	}

	@Test
	public void getLanding() throws Exception {
		ResponseEntity<String> response = template.getForEntity(base.toString(), String.class);
		assertNotNull(response);
		//assertThat(response.getBody(), containsString("Login"));
	}

	@Test
	public void getUserHome() throws Exception {
		String userURI = base.toString() + "/user/home";
		ResponseEntity<String> response = template.getForEntity(userURI, String.class);
		assertThat(response.getBody(), containsString("Participation System"));
	}

	@Test
	public void getAdmin() throws Exception {
		String userURI = base.toString() + "/admin";
		ResponseEntity<String> response = template.getForEntity(userURI, String.class);
		assertThat(response.getBody(), containsString("Administrator"));
	}

	@Test
	public void getAddForm() throws Exception {
		String userURI = base.toString() + "/user/addForm";
		ResponseEntity<String> response = template.getForEntity(userURI, String.class);
		assertThat(response.getBody(), containsString("Add your proposal"));
	}

	@Test
	public void getMensajeError() throws Exception {
		String userURI = base.toString() + "/mensajeError";
		ResponseEntity<String> response = template.getForEntity(userURI, String.class);
		assertThat(response.getBody(), containsString("DON'T USE BAD WORDS!!!"));
	}
	
	@Test
	public void getAddCategory() throws Exception {
		String userURI = base.toString() + "/addCategory";
		ResponseEntity<String> response = template.getForEntity(userURI, String.class);
		assertThat(response.getBody(), containsString("Add category"));
	}
	
	@Test
	public void getProposal() throws Exception {
		String userURI = base.toString() + "/user/viewProposal/1";
		ResponseEntity<String> response = template.getForEntity(userURI, String.class);
		assertThat(response.getBody(), containsString("Proposal details"));
	}
	
	@Test
	public void getVoteProposal() throws Exception {
		String userURI = base.toString() + "/user/voteProposal/1";
		ResponseEntity<String> response = template.getForEntity(userURI, String.class);
		assertNotNull(response);
		//assertThat(response.getBody(), containsString("Votes"));
	}
	
	@Test
	public void getVoteComment() throws Exception {
		String userURI = base.toString() + "/user/voteComment/1";
		ResponseEntity<String> response = template.getForEntity(userURI, String.class);
		assertNotNull(response);
		//assertThat(response.getBody(), containsString("Votes"));
	}
	
	@Test
	public void getOrderCommentsDate() throws Exception {
		String userURI = base.toString() + "/user/orderCommentsDate/1";
		ResponseEntity<String> response = template.getForEntity(userURI, String.class);
		assertThat(response.getBody(), containsString("Votes"));
	}
	
	
	@Test
	public void getOrderCommentsPopularity() throws Exception {
		String userURI = base.toString() + "/user/orderCommentsPopularity/1";
		ResponseEntity<String> response = template.getForEntity(userURI, String.class);
		assertThat(response.getBody(), containsString("Votes"));
	}
	
	
}