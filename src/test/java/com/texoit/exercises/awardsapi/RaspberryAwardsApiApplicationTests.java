package com.texoit.exercises.awardsapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class RaspberryAwardsApiApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() {
	}


	@Test
	public void getAllFilmes() throws Exception {
		mockMvc.perform(get("/movies"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}

	@Test
	public void createFilme() throws Exception {
		String filmeJson = "{\"year\": 2022, \"title\": \"Teste\", \"studios\": \"Teste Studios\", \"producers\": \"Teste Producers\", \"winner\": true}";
		mockMvc.perform(post("/movies")
				.contentType(MediaType.APPLICATION_JSON)
				.content(filmeJson))
				.andExpect(status().isOk());
	}

}
