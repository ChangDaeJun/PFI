package com.PFI.mainService.configuration;

import com.PFI.mainService.repository.CrawlingRepository;
import com.PFI.mainService.repository.JdbcMemberRepository;
import com.PFI.mainService.repository.MemberRepository;
import com.PFI.mainService.repository.StockRepository;
import com.PFI.mainService.service.StockService;
import com.PFI.mainService.service.UserService;
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
    public MemberRepository userRepository(){
        return new JdbcMemberRepository(dataSource);
    }

    @Bean
    public UserService userService(){
        return new UserService(userRepository());
    }

    @Bean
    public StockRepository stockRepository(){
        return new CrawlingRepository();
    }
    @Bean
    public StockService stockService(){
        return new StockService(stockRepository());
    }
}
