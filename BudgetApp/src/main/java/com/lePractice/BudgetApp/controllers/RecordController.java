package com.lePractice.BudgetApp.controllers;

import com.lePractice.BudgetApp.services.RecordService;
import com.lePractice.BudgetApp.models.Record;
import com.lePractice.BudgetApp.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/records")
public class RecordController {

    private final RecordService recordService;

    @Autowired
    public RecordController(RecordService recordService) {
        this.recordService = recordService;
    }

    @PostMapping
    public ResponseEntity<Record> createRecord(@RequestBody Record record) {
        Record createdRecord = recordService.createRecord(record);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRecord);
    }

    @GetMapping
    public ResponseEntity<List<java.lang.Record>> getAllRecordsByUser(@RequestParam Long userId) {
        // Assuming you pass the userId as a query parameter to filter records by user
        User user = new User();
        user.setId(userId);
        List<java.lang.Record> records = recordService.getAllRecordsByUser(user);
        return ResponseEntity.ok(records);
    }

    @GetMapping("/{recordId}")
    public ResponseEntity<java.lang.Record> getRecordById(@PathVariable Long recordId) {
        Optional<java.lang.Record> record = recordService.getRecordById(recordId);
        return record.map(value -> ResponseEntity.ok(value))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{recordId}")
    public ResponseEntity<java.lang.Record> editRecord(@PathVariable Long recordId, @RequestBody Record updatedRecord) {
        Optional<java.lang.Record> existingRecordOptional = recordService.getRecordById(recordId);
        if (existingRecordOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        java.lang.Record existingRecord = existingRecordOptional.get();

        // Apply the changes from the updatedRecord to the existingRecord
        existingRecord.setAmount(updatedRecord.getAmount());
        existingRecord.setDate(updatedRecord.getDate());
        existingRecord.setDescription(updatedRecord.getDescription());

        java.lang.Record savedRecord = recordService.editRecord(existingRecord);
        return ResponseEntity.ok(savedRecord);
    }

    @DeleteMapping("/{recordId}")
    public ResponseEntity<Void> deleteRecord(@PathVariable Long recordId) {
        recordService.deleteRecord(recordId);
        return ResponseEntity.noContent().build();
    }
}
