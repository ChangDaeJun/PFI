package com.PFI.mainService.repository;

import java.util.List;

public interface StockRepository {
    public List<List<String>> findByEquityName(String stockName);
    public List<List<String>> findByIndicesName(String stockName);
    public List<List<String>> findByCurrencyName(String stockName);
}
