package com.broberson4.futurestracker.service;
// Service class to handle business logic related to trades, including CSV import
import com.broberson4.futurestracker.model.Trade;
import com.broberson4.futurestracker.repository.TradeRepository;

//import org.springframework.cglib.core.Local;                   THIS IS A NOTE FOR NOW
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
// For CSV parsing
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
// For reading the file
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.*;
import java.time.format.DateTimeFormatter;

@Service
public class TradeService {

    private final TradeRepository tradeRepository;

    public TradeService(TradeRepository tradeRepository) {
        this.tradeRepository = tradeRepository;
    }

    public void importCsv(MultipartFile file) throws Exception {

        CSVParser parser = CSVFormat.DEFAULT.builder()
                .setHeader()
                .setSkipHeaderRecord(true)
                .setIgnoreHeaderCase(true)
                .setTrim(true)
                .build()
                .parse(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8));

        for (CSVRecord record : parser) {
            Trade trade = new Trade();

            trade.setName(record.get("Name"));
            trade.setSymbol(record.get("Symbol"));
            trade.setSide(record.get("Side"));
            trade.setStatus(record.get("Status"));

            System.out.println("Parsing row...");

            if (!record.get("Filled").isEmpty()) {
                trade.setFilled(Integer.parseInt(record.get("Total Qty")));
            }
            
            if (!record.get("Total Qty").isEmpty()) {
                trade.setTotalQty(Integer.parseInt(record.get("Total Qty")));
            }

            String placedTimeStr = record.get("Placed Time");
            if (placedTimeStr != null && !placedTimeStr.isEmpty()) {
                try {
                    String cleaned = placedTimeStr.replace(" EST", "").trim();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
                    LocalDateTime dateTime = LocalDateTime.parse(cleaned, formatter);
                    trade.setPlacedTime(dateTime);
                }
                catch (Exception e) {
                    System.err.println("Error parsing placed time: " + placedTimeStr);
                }
            }

            String filledTimeStr = record.get("Filled Time");
            if (filledTimeStr != null && !filledTimeStr.isEmpty()) {
                try {
                    String cleaned = filledTimeStr.replace(" EST", "").trim();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
                    LocalDateTime dateTime = LocalDateTime.parse(cleaned, formatter);
                    trade.setFilledTime(dateTime);
                }
                catch (Exception e) {
                    System.err.println("Error parsing filled time: " + filledTimeStr);
                }
            }

            System.out.println(parser.getHeaderMap());

            System.out.println("Placed Time Raw: " + record.get("Placed Time"));
            System.out.println("Filled Time Raw: " + record.get("Filled Time"));

            System.out.println("PlacedTime being saved: " + trade.getPlacedTime());

            tradeRepository.save(trade);
        }
    }
}