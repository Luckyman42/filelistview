package com.example.filelistview.services;

import com.example.filelistview.data.History;
import com.example.filelistview.data.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryService {
    @Autowired
    private HistoryRepository historyRepository;

    public History CreateHistory(History history){
        return historyRepository.save(history);
    }

    // Get all history
    public List<History> getAllHistory() {
        return historyRepository.findAll();
    }

}
