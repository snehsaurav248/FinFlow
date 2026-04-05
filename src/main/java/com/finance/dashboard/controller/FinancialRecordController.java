package com.finance.dashboard.controller;

import com.finance.dashboard.dto.record.*;
import com.finance.dashboard.service.FinancialRecordService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/records")
@RequiredArgsConstructor
public class FinancialRecordController {

    private final FinancialRecordService recordService;

    @PostMapping
    public FinancialRecordResponseDTO create(@Valid @RequestBody FinancialRecordRequestDTO request) {
        return recordService.createRecord(request);
    }

    @GetMapping
    public List<FinancialRecordResponseDTO> getAll() {
        return recordService.getUserRecords();
    }

    @GetMapping("/{id}")
    public FinancialRecordResponseDTO getById(@PathVariable Long id) {
        return recordService.getRecordById(id);
    }

    @PutMapping("/{id}")
    public FinancialRecordResponseDTO update(
            @PathVariable Long id,
            @Valid @RequestBody FinancialRecordRequestDTO request) {
        return recordService.updateRecord(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        recordService.deleteRecord(id);
        return ResponseEntity.ok("Record deleted successfully");
    }
}