package com.tinybeans.backend.evaluation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinybeans.backend.evaluation.data.entity.Item;
import com.tinybeans.backend.evaluation.data.repository.ItemRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class StripeTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ItemRepository itemRepository;

    @Test
    void httpClientStripeTest() throws Exception {
        Item item = itemRepository.findById(Long.valueOf(1));
        List<Item> items = List.of(item);

        this.objectMapper.configure(MapperFeature.USE_ANNOTATIONS, true);

        ResultActions response = this.mockMvc.perform(
                post("/checkout")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(items)));

        response.andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.orders.id")
                        .isNotEmpty())
                .andExpect(jsonPath("$.data.client_secret")
                        .isNotEmpty());
    }
}
