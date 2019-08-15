package com.hamdani.controller;

import com.hamdani.persistence.User;
import com.hamdani.persistence.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Optional;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class UserControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserController userController;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        when(userRepository.findAll()).thenReturn(loadUsers());

    }

    @Test
    public void testHomePage() throws Exception{

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attribute("users",hasSize(3)));

    }

    @Test
    public void testDelete() throws Exception{
        Integer id = 1;

        when(userRepository.findById(Long.valueOf(id))).thenReturn(getUser());
        mockMvc.perform(get("/delete/1"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("index"));


        Mockito.verify(userRepository, Mockito.times(1)).delete(any(User.class));
    }

    private Optional<User> getUser() {
        User mUser = new User();
        mUser.setEmail("syed@affanhamdani.me");
        mUser.setFirstName("syed");
        mUser.setLastName("LastName");
        return Optional.of(mUser);
    }

    private ArrayList<User> loadUsers() {

        ArrayList<User> list = new ArrayList<>();
        User mUser = new User();
        mUser.setEmail("syed@affanhamdani.me");
        mUser.setFirstName("syed");
        mUser.setLastName("LastName");
        list.add(mUser);

        User mUser2 = new User();
        mUser2.setEmail("syed2@affanhamdani.me");
        mUser2.setFirstName("syed2");
        mUser2.setLastName("LastName2");
        list.add(mUser2);

        User mUser3 = new User();
        mUser3.setEmail("syed3@affanhamdani.me");
        mUser3.setFirstName("syed3");
        mUser3.setLastName("LastName3");
        list.add(mUser3);

        return list;

    }
}
