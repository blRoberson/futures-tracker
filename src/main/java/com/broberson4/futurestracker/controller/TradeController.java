package com.broberson4.futurestracker.controller;

import com.broberson4.futurestracker.model.Trade;
import com.broberson4.futurestracker.service.TradeService;
import com.broberson4.futurestracker.repository.TradeRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@RestController
@RequestMapping("/api/trades")
public class TradeController {

    private final TradeService tradeService;
    private final TradeRepository tradeRepository;

    public TradeController(TradeService tradeService, TradeRepository tradeRepository) {
        this.tradeService = tradeService;
        this.tradeRepository = tradeRepository;
    }

    @PostMapping("/upload")
    public String uploadCsv(@RequestParam("file") MultipartFile file) throws Exception {
        tradeService.importCsv(file);
        return "CSV uploaded successfully";
    }

    @GetMapping
    public List<Trade> getAllTrades() {
        return tradeRepository.findAll();
    }
}