package br.com.moip;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.moip.configuration.H2ConfigurationTest;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { H2ConfigurationTest.class, Application.class })
@ActiveProfiles("test")
public class ApplicationTest {

	@Test
	public void instance() {
	}

}
