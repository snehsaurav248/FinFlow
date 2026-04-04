package com.finance.dashboard.service.impl;

import com.finance.dashboard.dto.record.*;
import com.finance.dashboard.entity.FinancialRecord;
import com.finance.dashboard.entity.User;
import com.finance.dashboard.exception.ResourceNotFoundException;
import com.finance.dashboard.repository.FinancialRecordRepository;
import com.finance.dashboard.repository.UserRepository;
import com.finance.dashboard.service.FinancialRecordService;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FinancialRecordServiceImpl implements FinancialRecordService {

    private final FinancialRecordRepository recordRepository;
    private final UserRepository userRepository;

    private User getCurrentUser() {
        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @Override
    public FinancialRecordResponseDTO createRecord(FinancialRecordRequestDTO request) {

        User user = getCurrentUser();

        FinancialRecord record = FinancialRecord.builder()
                .amount(request.getAmount())
                .type(request.getType())
                .category(request.getCategory())
                .date(request.getDate())
                .description(request.getDescription())
                .user(user)
                .build();

        recordRepository.save(record);

        return mapToResponse(record);
    }

    @Override
    public List<FinancialRecordResponseDTO> getUserRecords() {

        User user = getCurrentUser();

        return recordRepository.findByUserId(user.getId())
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public FinancialRecordResponseDTO getRecordById(Long id) {

        User user = getCurrentUser();

        FinancialRecord record = recordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Record not found"));

        if (!record.getUser().getId().equals(user.getId())) {
            throw new ResourceNotFoundException("Record not found");
        }

        return mapToResponse(record);
    }

    @Override
    public FinancialRecordResponseDTO updateRecord(Long id, FinancialRecordRequestDTO request) {

        FinancialRecord record = recordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Record not found"));

        if (!record.getUser().getId().equals(getCurrentUser().getId())) {
            throw new ResourceNotFoundException("Record not found");
        }

        record.setAmount(request.getAmount());
        record.setType(request.getType());
        record.setCategory(request.getCategory());
        record.setDate(request.getDate());
        record.setDescription(request.getDescription());

        recordRepository.save(record);

        return mapToResponse(record);
    }

    @Override
    public void deleteRecord(Long id) {

        FinancialRecord record = recordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Record not found"));

        if (!record.getUser().getId().equals(getCurrentUser().getId())) {
            throw new ResourceNotFoundException("Record not found");
        }

        recordRepository.delete(record);
    }


    private FinancialRecordResponseDTO mapToResponse(FinancialRecord record) {
        return FinancialRecordResponseDTO.builder()
                .id(record.getId())
                .amount(record.getAmount())
                .type(record.getType())
                .category(record.getCategory())
                .date(record.getDate())
                .description(record.getDescription())
                .build();
    }
}