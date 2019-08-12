package com.test.crud.CustomerCRUD.bootsstrap;

import com.test.crud.CustomerCRUD.persistence.User;
import com.test.crud.CustomerCRUD.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class SpringJPABootstrap implements ApplicationListener<ContextRefreshedEvent> {
    private UserRepository userRepository;

    @Autowired
    public SpringJPABootstrap(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        loadUsers();

    }

    private void loadUsers() {
        User mUser = new User();
        mUser.setEmail("syed@affanhamdani.me");
        mUser.setFirstName("syed");
        mUser.setLastName("LastName");
        userRepository.save(mUser);

        User mUser2 = new User();
        mUser2.setEmail("syed2@affanhamdani.me");
        mUser2.setFirstName("syed2");
        mUser2.setLastName("LastName2");
        userRepository.save(mUser2);

        User mUser3 = new User();
        mUser3.setEmail("syed3@affanhamdani.me");
        mUser3.setFirstName("syed3");
        mUser3.setLastName("LastName3");
        userRepository.save(mUser3);

    }
}
