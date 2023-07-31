package com.lePractice.BudgetApp.services;
import com.lePractice.BudgetApp.models.User;
import com.lePractice.BudgetApp.repos.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecordService {

    private final RecordRepository recordRepository;

    @Autowired
    public RecordService(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    public Record createRecord(Record record) {
        return recordRepository.save(record);
    }

    public List<Record> getAllRecordsByUser(User user) {
        return recordRepository.findByUser(user);
    }

    public void deleteRecord(Long recordId) {
        recordRepository.deleteById(recordId);
    }

    public Optional<Record> getRecordById(Long recordId) {
        return recordRepository.findById(recordId);
    }

    public Record editRecord(Record record) {
        return recordRepository.save(record);
    }
}