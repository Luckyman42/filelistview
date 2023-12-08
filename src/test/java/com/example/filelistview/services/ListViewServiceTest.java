package com.example.filelistview.services;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
class ListViewServiceTest {
    private final static String path = "src/test/resources/testDatas/";

    ListViewService listViewService = new ListViewService();

    List<String> filenames;
    List<String> expecteds;
    @BeforeEach
    public void initTestValues(){
        filenames = new ArrayList<>();
        expecteds = new ArrayList<>();
        filenames.add("00");
        expecteds.add("Mistaken files: \n");
        filenames.add("30");
        expecteds.add("->p.txt\n\t->e.txt\n\t\t->r.txt\nMistaken files: \n");
        filenames.add("03");
        expecteds.add("Mistaken files: \nx.txt\nv.txt\na.txt\n");
        filenames.add("85");
        expecteds.add("->b.txt\n\t->k.txt\n\t\t->f.txt\n\t\t\t->h.txt\n\t\t\t\t->g.txt\n\t\t\t\t\t->v.txt\n\t\t\t\t\t\t->o.txt\n\t\t\t\t\t\t\t->d.txt\nMistaken files: \nl.txt\na.txt\nu.txt\nz.txt\nm.txt\n");
        filenames.add("208");
        expecteds.add("->ka.txt\n\t->gb.txt\n\t\t->nw.txt\n\t\t\t->gr.txt\n\t\t\t\t->xy.txt\n\t\t\t\t\t->ew.txt\n\t\t\t\t\t\t->op.txt\n\t\t\t\t\t\t\t->lx.txt\n\t\t\t\t\t\t\t\t->zy.txt\n\t\t\t\t\t\t\t\t\t->ia.txt\n\t\t\t\t\t\t\t\t\t\t->dp.txt\n\t\t\t\t\t\t\t\t\t\t\t->pz.txt\n\t\t\t\t\t\t\t\t\t\t\t\t->rp.txt\n\t\t\t\t\t\t\t\t\t\t\t\t\t->ax.txt\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t->ue.txt\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t->jy.txt\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t->jb.txt\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t->hj.txt\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t->it.txt\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t->ru.txt\nMistaken files: \nff.txt\ntr.txt\nbd.txt\nsh.txt\nyd.txt\neo.txt\nlm.txt\ngq.txt\n");
    }

    @Test
    void getResult() {
        for (int i = 0; i < 5; i++) {
        String result = listViewService.GetResult(path + filenames.get(i)+"/");
        String expected = expecteds.get(i);
        assertEquals(expected, result);
        }
    }
}