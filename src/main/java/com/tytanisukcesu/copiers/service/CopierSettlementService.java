package com.tytanisukcesu.copiers.service;

import com.tytanisukcesu.copiers.entity.CopierSettlement;
import com.tytanisukcesu.copiers.entity.Counter;
import com.tytanisukcesu.copiers.entity.Device;
import com.tytanisukcesu.copiers.repository.CopierSettlementRepository;
import com.tytanisukcesu.copiers.repository.CounterRepository;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.jni.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CopierSettlementService {

    private final CopierSettlementRepository copierSettlementRepository;
    private final CounterService counterService;
    private final DeviceService deviceService;
    private final CounterRepository counterRepository;

    /***
     *jezeli nie ma licznika to sprawdz na 4 dni do przodu lud do tylu, jesli jest to pobierz z bazy i skopiuj
     */

    public CopierSettlement save(CopierSettlement copierSettlement) {
        Device device = copierSettlement.getDevice();
        copierSettlement.setStartingColourCounter(getLastSettlementColourCounter(device));
        copierSettlement.setStartingMonoCounter(getLastSettlementMonoCounter(device));
        copierSettlement.setClosingColourCounter(getLastCounterColourCounter(device));
        copierSettlement.setClosingMonoCounter(getLastCounterMonoCounter(device));
        return null;
    }

    public Integer getLastCounterMonoCounter(Device device) {
        LocalDate lastDay = getLastDayOfPreviousMonth();
        Integer lastCounter = counterMapProvider(device.getCounters(),false).get(lastDay);
        if (lastCounter != null) {
            return lastCounter;
        } else {
            Optional<Counter> counterOptional = counterRepository.getTopByCounterDateIsBeforeAndDeviceSerialNumberOrderByCounterDateDesc(lastDay, device.getSerialNumber());
            lastCounter = counterOptional.get().getMonoCounter();
        }
        return lastCounter;
    }

    public Integer getLastCounterColourCounter(Device device) {
        LocalDate lastDay = getLastDayOfPreviousMonth();
        Integer lastCounter = counterMapProvider(device.getCounters(),true).get(lastDay);
        if (lastCounter != null) {
            return lastCounter;
        } else {
            Optional<Counter> counterOptional = counterRepository.getTopByCounterDateIsBeforeAndDeviceSerialNumberOrderByCounterDateDesc(lastDay, device.getSerialNumber());
            lastCounter = counterOptional.get().getColourCounter();
        }
        return lastCounter;
    }

    private Map<LocalDate, Integer> counterMapProvider(Set<Counter> counters, boolean isColourCounter) {
        if (isColourCounter) {
            return counters.stream()
                    .collect(Collectors.toMap(Counter::getCounterDate, Counter::getColourCounter));
        } else {
            return counters.stream()
                    .collect(Collectors.toMap(Counter::getCounterDate, Counter::getMonoCounter));
        }
    }


    public List<CopierSettlement> findAll() {
        List<CopierSettlement> copierSettlements = copierSettlementRepository.findAll();
        return copierSettlements;
    }

    public CopierSettlement findById(Long id) {
        Optional<CopierSettlement> copierSettlementOptional = copierSettlementRepository.findById(id);
        return copierSettlementOptional.orElse(new CopierSettlement());
    }

    private Integer getLastSettlementMonoCounter(Device device) {
        Optional<CopierSettlement> copierSettlementOptional = copierSettlementRepository.getTopByDeviceOrderByDateOfSettlementDesc(device);
        if (copierSettlementOptional.isPresent()) {
            return copierSettlementOptional.get().getClosingMonoCounter();
        } else {
            return 0;
        }
    }

    private Integer getLastSettlementColourCounter(Device device) {
        Optional<CopierSettlement> copierSettlementOptional = copierSettlementRepository.getTopByDeviceOrderByDateOfSettlementDesc(device);
        if (copierSettlementOptional.isPresent()) {
            return copierSettlementOptional.get().getClosingColourCounter();
        } else {
            return 0;
        }
    }

    public LocalDate getLastDayOfPreviousMonth() {
        return LocalDate.now().minusMonths(1).with(TemporalAdjusters.lastDayOfMonth());
    }


}
