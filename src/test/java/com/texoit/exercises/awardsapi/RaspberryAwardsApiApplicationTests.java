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
        String movieJson = "{\"year\": 2022, \"title\": \"Teste\", \"studios\": \"Teste Studios\", \"producers\": \"Teste Producers\", \"winner\": true}";
        mockMvc.perform(post("/movies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(movieJson))
                .andExpect(status().isOk());
    }

    @Test
    public void getIntervalByProducers() throws Exception {
        String jsonString = "{\"min\": [{\"producer\": \"Bo Derek\",\"interval\": 6,\"previousWin\": 1984,\"followingWin\": 1990},{\"producer\": \"Buzz Feitshans\",\"interval\": 9,\"previousWin\": 1985,\"followingWin\": 1994},{\"producer\": \"Joel Silver\",\"interval\": 1,\"previousWin\": 1990,\"followingWin\": 1991}],\"max\": [{\"producer\": \"Bo Derek\",\"interval\": 6,\"previousWin\": 1984,\"followingWin\": 1990},{\"producer\": \"Buzz Feitshans\",\"interval\": 9,\"previousWin\": 1985,\"followingWin\": 1994},{\"producer\": \"Joel Silver\",\"interval\": 1,\"previousWin\": 1990,\"followingWin\": 1991}]}";
        mockMvc.perform(get("/awards/interval/producers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonString))
                .andExpect(status().isOk());
    }

    @Test
    public void getIntervalByStudios() throws Exception {
        String jsonString = "{\"min\": [{\"producer\": \"Paramount Pictures\",\"interval\": 15,\"previousWin\": 1993,\"followingWin\": 2008},{\"producer\": \"20th Century Fox\",\"interval\": 14,\"previousWin\": 2001,\"followingWin\": 2015},{\"producer\": \"Universal Studios\",\"interval\": 3,\"previousWin\": 1983,\"followingWin\": 1986},{\"producer\": \"Warner Bros.\",\"interval\": 5,\"previousWin\": 1999,\"followingWin\": 2004},{\"producer\": \"Columbia Pictures\",\"interval\": 1,\"previousWin\": 2017,\"followingWin\": 2018},{\"producer\": \"Hollywood Pictures\",\"interval\": 4,\"previousWin\": 1994,\"followingWin\": 1998}],\"max\": [{\"producer\": \"Paramount Pictures\",\"interval\": 15,\"previousWin\": 1993,\"followingWin\": 2008},{\"producer\": \"20th Century Fox\",\"interval\": 14,\"previousWin\": 2001,\"followingWin\": 2015},{\"producer\": \"Universal Studios\",\"interval\": 3,\"previousWin\": 1983,\"followingWin\": 1986},{\"producer\": \"Warner Bros.\",\"interval\": 5,\"previousWin\": 1999,\"followingWin\": 2004},{\"producer\": \"Columbia Pictures\",\"interval\": 1,\"previousWin\": 2017,\"followingWin\": 2018},{\"producer\": \"Hollywood Pictures\",\"interval\": 4,\"previousWin\": 1994,\"followingWin\": 1998}]}";
        mockMvc.perform(get("/awards/interval/studios")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonString))
                .andExpect(status().isOk());
    }

}
