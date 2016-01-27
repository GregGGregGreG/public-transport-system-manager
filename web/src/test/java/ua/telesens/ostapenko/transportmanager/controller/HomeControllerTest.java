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
import ua.telesens.ostapenko.conf.WebAppContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author root
 * @since 01.12.15
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebAppContext.class})
//@ContextConfiguration(locations = {"classpath:unitTestContext.xml", "classpath:exampleApplicationContext-web.xml"})
@WebAppConfiguration
public class HomeControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webAppContext;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext)
                .build();
    }

    @Test
    public void showHomePage_ShouldRenderHomeView() throws Exception {
        mockMvc.perform(get(HomeController.VIEW_NAME_HOMEPAGE))
                .andExpect(status().isOk())
                .andExpect(view().name(LoginController.VIEW_NAME_LOGINPAGE))
                .andExpect(forwardedUrl("/WEB-INF/layout/classic.jsp"));
    }
}