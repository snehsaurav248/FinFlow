package com.finance.dashboard.service;

import com.finance.dashboard.dto.record.*;

import java.util.List;

public interface FinancialRecordService {

    FinancialRecordResponseDTO createRecord(FinancialRecordRequestDTO request);

    List<FinancialRecordResponseDTO> getUserRecords();

    FinancialRecordResponseDTO getRecordById(Long id);

    FinancialRecordResponseDTO updateRecord(Long id, FinancialRecordRequestDTO request);

    void deleteRecord(Long id);
}