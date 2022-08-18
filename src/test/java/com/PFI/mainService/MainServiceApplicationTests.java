package com.PFI.mainService;

import com.PFI.mainService.repository.CrawlingRepository;
import com.PFI.mainService.repository.StockRepository;
import com.PFI.mainService.service.StockService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MainServiceApplicationTests {

	@Autowired
	private StockService stockService;

	@Test
	void contextLoads() {
		System.out.println(stockService.getStock("kospi").toString());
	}

}
