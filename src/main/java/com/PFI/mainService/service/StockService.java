package com.PFI.mainService.service;


import com.PFI.mainService.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class StockService {
    Map<String, List<List<String>>> storage = new HashMap<>();

    @Autowired
    public StockService(StockRepository stockRepository) {
        String[] indices = {"us-spx-500", "kosdaq", "nasdaq-composite", "kospi"};
        String[] equities = {"apple-computer-inc", "samsung-electronics-co-ltd", "tesla-motors"};
        String[] currencies = {"usd-krw"};
        for(String name : indices) storage.put(name,stockRepository.findByIndicesName(name));
        for(String name : indices) storage.put(name,stockRepository.findByEquityName(name));
        for(String name : indices) storage.put(name,stockRepository.findByCurrencyName(name));
    }

    public List<List<String>> getStock(String name){
        if(!storage.containsKey(name)) new Exception();
        List<String> addList = new LinkedList<>();
        addList.add("date");
        addList.add(name);

        List<List<String>> result = storage.get(name);
        result.add(0, addList);
        return result;
    }
}
