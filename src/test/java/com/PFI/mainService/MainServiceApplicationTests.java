package com.PFI.mainService;

import com.PFI.mainService.repository.CrawlingRepository;
import com.PFI.mainService.repository.StockRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MainServiceApplicationTests {

	@Test
	void contextLoads() {
		StockRepository stockRepository = new CrawlingRepository();
		stockRepository.findByEquityName("apple-computer-inc");
	}

}
