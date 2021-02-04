package com.tytanisukcesu.copiers.service;

import com.tytanisukcesu.copiers.entity.CopierSettlement;
import com.tytanisukcesu.copiers.entity.Customer;
import com.tytanisukcesu.copiers.entity.Device;
import com.tytanisukcesu.copiers.repository.CopierSettlementRepository;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.jni.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CopierSettlementService {

    private final CopierSettlementRepository copierSettlementRepository;
    private final CounterService counterService;

    /***
        *jezeli nie ma licznika to sprawdz na 4 dni do przodu lud do tylu, jesli jest to pobierz z bazy i skopiuj
    */

//    public CopierSettlement save(CopierSettlement copierSettlement) {
//        Device device = copierSettlement.getDevice();
//        copierSettlement.setStartingColourCounter(getLastColourCounter(device));
//        copierSettlement.setStartingMonoCounter(getLastMonoCounter(device));
//    }

    public List<CopierSettlement> findAll() {
        List<CopierSettlement> copierSettlements = copierSettlementRepository.findAll();
        return copierSettlements;
    }

    public CopierSettlement findById(Long id) {
        Optional<CopierSettlement> copierSettlementOptional = copierSettlementRepository.findById(id);
        return copierSettlementOptional.orElse(new CopierSettlement());
    }

    private int getLastMonoCounter(Device device) {
        Optional<CopierSettlement> copierSettlementOptional = copierSettlementRepository.getTopByDeviceOrderByDateOfSettlementDesc(device);
        if (copierSettlementOptional.isPresent()) {
            return copierSettlementOptional.get().getClosingMonoCounter();
        } else {
            return 0;
        }
    }

    private int getLastColourCounter(Device device) {
        Optional<CopierSettlement> copierSettlementOptional = copierSettlementRepository.getTopByDeviceOrderByDateOfSettlementDesc(device);
        if (copierSettlementOptional.isPresent()) {
            return copierSettlementOptional.get().getClosingColourCounter();
        } else {
            return 0;
        }
    }

    public LocalDate getLastDayOfPreviousMonth(){
        return LocalDate.now().minusMonths(1).with(TemporalAdjusters.lastDayOfMonth());
    }


}
