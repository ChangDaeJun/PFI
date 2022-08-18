package com.PFI.mainService.repository;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class CrawlingRepository implements StockRepository{


    @Override
    public List<List<String>> findByEquityName(String stockName) {
        String webURL = "https://kr.investing.com/equities/"+ stockName + "-historical-data";
        return crawlingStockPrice(webURL);
    }

    private List<List<String>> crawlingStockPrice(String webURL) {
        List<List<String>> result = new LinkedList<>();
        try {
            Document doc = Jsoup.connect(webURL).get();
            Elements contents = doc.select(".border.border-main");
            String[] text = contents.text().split(" ");
            for(int i = 8; i < text.length; i+= 7){
                List<String> list = new LinkedList<>();
                list.add(text[i]);
                list.add(text[i + 1].replace(",",""));
                result.add(list);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<List<String>> findByIndicesName(String stockName) {
        String webURL = "https://kr.investing.com/indices/"+ stockName + "-historical-data";
        return crawlingStockPrice(webURL);
    }

    @Override
    public List<List<String>> findByCurrencyName(String stockName) {
        String webURL = "https://kr.investing.com/currencies/"+ stockName + "-historical-data";
        return crawlingStockPrice(webURL);
    }
}
