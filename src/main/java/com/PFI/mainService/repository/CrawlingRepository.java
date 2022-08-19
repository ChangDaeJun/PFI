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
            Elements contents = doc.select(".border.border-main").select(".datatable_body__3EPFZ").select(".datatable_row__2vgJl");
            List<String> text = contents.eachText();
            for(String data : text){
                List<String> list = new LinkedList<>();
                String[] dataSplit = data.split(" ");
                list.add(dataSplit[0]);
                list.add(dataSplit[1].replace(",",""));
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
