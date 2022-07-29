package com.PFI.mainService;

import com.PFI.mainService.repository.JdbcUserRepository;
import com.PFI.mainService.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {
    private final DataSource dataSource;

    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public UserRepository userRepository(){
        return new JdbcUserRepository(dataSource);
    }
}
