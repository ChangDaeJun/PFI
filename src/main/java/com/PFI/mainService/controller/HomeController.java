package com.PFI.mainService.controller;

import com.PFI.mainService.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private StockService stockService;
    @GetMapping("/")
    public String home(Model model){
        String kospiData = adaptGoogleChartData("kospi");
        String kosdaqData = adaptGoogleChartData("kosdaq");
        String usSpx500Data = adaptGoogleChartData("us-spx-500");
        String nasdaqData = adaptGoogleChartData("nasdaq-composite");
        String appleComputerIncData = adaptGoogleChartData("apple-computer-inc");
        String samsungElectronicsCoLtdData = adaptGoogleChartData("samsung-electronics-co-ltd");
        model.addAttribute("chartA", kospiData);
        model.addAttribute("chartB", kosdaqData);
        model.addAttribute("chartC", usSpx500Data);
        model.addAttribute("chartD", nasdaqData);
        model.addAttribute("chartE", appleComputerIncData);
        model.addAttribute("chartF", samsungElectronicsCoLtdData);
        return "/home";
    }

    private String adaptGoogleChartData(String stockName) {
        StringBuilder sb = new StringBuilder();
        for(List<String> list : stockService.getStock(stockName)){
            sb.append(", [new Date(");
            sb.append(list.get(0).replace("-",", "));
            sb.append("), ");
            sb.append(list.get(1));
            sb.append("]");
        }
        return sb.toString().substring(2);
    }

}
