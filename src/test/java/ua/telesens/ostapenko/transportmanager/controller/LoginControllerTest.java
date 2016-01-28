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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * @author root
 * @since 01.12.15
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebAppConfig.class})
//@ContextConfiguration(locations = {"classpath:unitTestContext.xml", "classpath:exampleApplicationContext-web.xml"})
@WebAppConfiguration
public class LoginControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webAppContext;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext)
                .build();
    }

    @Test
    public void showLoginPage_ShouldRenderHomeView() throws Exception {
        mockMvc.perform(get("/" + LoginController.VIEW_NAME_LOGINPAGE))
                .andExpect(status().isFound())
                .andExpect(view().name(EnumView.redirectTo(HomeController.VIEW_NAME_HOMEPAGE)));
    }
}