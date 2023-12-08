package com.example.filelistview.data;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * It is an ORM for History.
 */
public interface HistoryRepository extends JpaRepository<History, Long> {
}
