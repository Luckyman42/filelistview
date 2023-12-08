package com.example.filelistview.services;

import com.example.filelistview.data.History;
import com.example.filelistview.data.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This class is handle the new histories and get the list of all
 */
@Service
public class HistoryService {
    /**
     * It needed for the DB connection to store and restore Histories
     */
    @Autowired
    private HistoryRepository historyRepository;

    /**
     * Create a new history and store it in DB
     * @param history the new instance of History
     * @return History object with in the DB
     */
    public History CreateHistory(History history){
        return historyRepository.save(history);
    }

    /**
     * Get All the history from the DB
     * @return a List of Histories
     */
    public List<History> getAllHistory() {
        return historyRepository.findAll();
    }

}
