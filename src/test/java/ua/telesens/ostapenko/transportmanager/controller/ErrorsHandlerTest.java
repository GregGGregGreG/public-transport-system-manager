package ua.telesens.ostapenko.transportmanager.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ua.telesens.ostapenko.conf.WebAppConfig;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author root
 * @since 01.12.15
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebAppConfig.class})
//@ContextConfiguration(locations = {"classpath:unitTestContext.xml", "classpath:exampleApplicationContext-web.xml"})
@WebAppConfiguration
public class ErrorsHandlerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webAppContext;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext)
                .build();
    }

    @Test
    public void testShow404Page() throws Exception {
        mockMvc.perform(get("/error/404"))
                .andExpect(status().isOk())
                .andExpect(view().name(ErrorsHandler.VIEW_NAME_P404))
                .andExpect(forwardedUrl("/WEB-INF/layout/classic.jsp"));
    }

    @Test
    public void testShowError() throws Exception {
        mockMvc.perform(get("/"+ErrorsHandler.VIEW_NAME_ERROR))
                .andExpect(status().isOk())
                .andExpect(view().name(ErrorsHandler.VIEW_NAME_ERROR))
                .andExpect(forwardedUrl("/WEB-INF/layout/classic.jsp"));
    }
}