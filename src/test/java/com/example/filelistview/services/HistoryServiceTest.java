package com.example.filelistview.services;

import com.example.filelistview.data.History;
import com.example.filelistview.data.HistoryRepository;
import jakarta.annotation.Resource;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HistoryServiceTest {
    @Mock
    private HistoryRepository historyRepository;

    // Testing instance, mocked `resource` should be injected here
    @InjectMocks
    @Resource
    private HistoryService historyService;

    private static final History history = new History();
    private static final History history1 = new History();
    private static final History history2 = new History();
    private static final History history3 = new History();
    private static final List<History> histories = new ArrayList<>();

    @BeforeEach()
    public void setUp() throws Exception {
        history1.setWho("1");
        history2.setWho("2");
        history3.setWho("3");
        histories.clear();
        histories.add(history1);
        histories.add(history2);
        histories.add(history3);
        // Initialize mocks created above
        MockitoAnnotations.openMocks(this);
        // Change behaviour of `resource`
        Mockito.when(historyRepository.save(history)).then(h->{histories.add(history); return history;});
        Mockito.when(historyRepository.findAll()).thenReturn(histories);
    }


    @Test
    void getAllHistory() {
        List<History> historylist = historyService.getAllHistory();
        assertEquals(3,historylist.size());
        assertEquals(history1, historylist.get(0));
        assertEquals(history2, historylist.get(1));
        assertEquals(history3, historylist.get(2));
    }
        @Test
    void createHistory() {
            long date = new Date().getTime();
            history.setRequestTime(date);
            history.setWho("testHistory");
            history.setFileNum(1);
            history.setMistakeNum(1);
            history.setResult("result");
        List<History> historylist = historyService.getAllHistory();

        assertFalse(historylist.contains(history));
        History resulthistory = historyService.CreateHistory(history);

        historylist = historyService.getAllHistory();
        assertTrue(historylist.contains(resulthistory));
    }

}