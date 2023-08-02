package delivery.management;


import com.fasterxml.jackson.databind.ObjectMapper;
import delivery.management.model.dto.Others.TestData;
import delivery.management.controller.usersControllers.UsersController;
import delivery.management.model.entity.user.Users;
import delivery.management.services.user.UsersServiceTest;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Component
@RequiredArgsConstructor
@WebMvcTest(UsersController.class)
public class userControllerTest {

    private final MockMvc mockMvc;

    private final UsersServiceTest usersServiceTest;

    private final ObjectMapper objectMapper;




  @Test
    public void getUserByIdTest() throws Exception {
        when(usersServiceTest.getUserById(anyInt())).thenReturn(TestData.getListOfUsers().get(anyInt()));
         mockMvc.perform(MockMvcRequestBuilders.get("/users/2"))
                 .andDo(print())
                .andExpect(status().isOk());
    }


    @Test
    public void saveUserTest() throws Exception {

        Users user = new Users();

        user.setId(4L);
        user.setName("John");
        user.setEmail("john@gmail.com");
        user.setAddress("No 4 ola Street");
        user.setUsername("johnny");
        user.setPassword("joshua123");

        when(usersServiceTest.saveUser(any(Users.class))).thenReturn((user));
        mockMvc.perform(MockMvcRequestBuilders.post("/users/add")
                .content(objectMapper.writeValueAsString(user))
                .contentType(TestData.getContentType()))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());

    }



    @Test
    public void saveUserTest2() throws Exception {

        when(usersServiceTest.saveUser(any(Users.class))).thenReturn((Users) TestData.addUser());
        mockMvc.perform(MockMvcRequestBuilders.post("/users/add")
                        .content(objectMapper.writeValueAsString(TestData.getUseRequest()))
                        .contentType(TestData.getContentType()))
                        .andExpect(status().isCreated())
                        .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                        .andDo(print());

    }




}
