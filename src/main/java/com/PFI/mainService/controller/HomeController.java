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
        StringBuilder sb = new StringBuilder();
        for(List<String> list : stockService.getStock("kospi")){
            sb.append(", [");
            sb.append(list.get(0));
            sb.append(", ");
            sb.append(list.get(1));
            sb.append("]");
        }
        System.out.println(sb.toString());
        model.addAttribute("kospi", sb.toString());
        model.addAttribute("kospi", sb.toString().substring(2));
        return "/home";
    }
}
