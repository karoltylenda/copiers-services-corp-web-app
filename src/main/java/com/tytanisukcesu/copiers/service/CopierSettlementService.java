package com.tytanisukcesu.copiers.service;

import com.tytanisukcesu.copiers.entity.Contract;
import com.tytanisukcesu.copiers.entity.CopierSettlement;
import com.tytanisukcesu.copiers.entity.Counter;
import com.tytanisukcesu.copiers.entity.Device;
import com.tytanisukcesu.copiers.repository.CopierSettlementRepository;
import com.tytanisukcesu.copiers.repository.CounterRepository;
import com.tytanisukcesu.copiers.repository.DeviceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CopierSettlementService {

    private final CopierSettlementRepository copierSettlementRepository;
    private final ContractService contractService;
    private final CounterRepository counterRepository;
    private static final Logger LOGGER = Logger.getLogger(CopierSettlementService.class.getName());

    /***
     * ASSUMPTIONS
     *provide last day of the previous month and if exits - assign, if not - find and get latest counters (mono and colour)
     * convert set to map as faster tool to get
     * set starting counter as closing on previous settlement
     */

    @Transactional
    public CopierSettlement save(CopierSettlement copierSettlement) {

        if (isSettlementInThisMonth(copierSettlement)) {
            return new CopierSettlement();
        } else {
            Contract contract = contractService.save(copierSettlement.getContract());

            Device device = contract.getDevice();

            CopierSettlement copierSettlementToSave = new CopierSettlement();

            copierSettlementToSave.setDateOfSettlement(LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), 1));
            copierSettlementToSave.setStartingColourCounter(getLastSettlementColourCounter(device));
            copierSettlementToSave.setStartingMonoCounter(getLastSettlementMonoCounter(device));
            copierSettlementToSave.setClosingColourCounter(getLastCounterColourCounter(device));
            copierSettlementToSave.setClosingMonoCounter(getLastCounterMonoCounter(device));
            copierSettlementToSave.setMonoAmount(getMonoAmount(device.getContract().getMonoPagePrice(), copierSettlementToSave.getStartingMonoCounter(), copierSettlementToSave.getClosingMonoCounter()));
            copierSettlementToSave.setColourAmount(getColourAmount(device.getContract().getColorPagePrice(), copierSettlementToSave.getStartingColourCounter(), copierSettlementToSave.getClosingColourCounter()));
            copierSettlementToSave.setTotalAmount(getTotalAmount(device.getContract().getLeasePrice(), copierSettlementToSave.getMonoAmount(), copierSettlementToSave.getColourAmount()));
            copierSettlementToSave.setContract(contract);

            CopierSettlement copierSettlementSaved = copierSettlementRepository.save(copierSettlementToSave);
            return copierSettlementSaved;
        }
    }

    private boolean isSettlementInThisMonth(CopierSettlement copierSettlement) {
        Optional<CopierSettlement> optionalLastSettlement = copierSettlementRepository.getTopByDateOfSettlementBeforeAndContract_Device_SerialNumberOrderByDateOfSettlementDesc(LocalDate.now().plusDays(1), copierSettlement.getContract().getDevice().getSerialNumber());
        if (optionalLastSettlement.isEmpty()) {
            return false;
        } else {
            CopierSettlement lastSettlement = optionalLastSettlement.get();
            if (lastSettlement.getDateOfSettlement().getMonth().getValue() == LocalDate.now().getMonth().getValue()) {
                return true;
            } else {
                return false;
            }
        }
    }

    private BigDecimal getTotalAmount(BigDecimal leasePrice, BigDecimal monoAmount, BigDecimal colourAmount) {
        return leasePrice.add(monoAmount).add(colourAmount);
    }

    public BigDecimal getMonoAmount(BigDecimal monoPagePrice, Integer startingMonoCounter, Integer closingMonoCounter) {
        return monoPagePrice.multiply(new BigDecimal(closingMonoCounter - startingMonoCounter));
    }

    public BigDecimal getColourAmount(BigDecimal colourPagePrice, Integer startingColourCounter, Integer closingColourCounter) {
        return colourPagePrice.multiply(BigDecimal.valueOf(closingColourCounter - startingColourCounter));
    }

    public Integer getLastCounterMonoCounter(Device device) {
        LocalDate lastDay = getLastDayOfPreviousMonth();
        Integer lastCounter = counterMapProvider(device.getCounters(), false).get(lastDay);
        if (lastCounter != null) {
            return lastCounter;
        } else {
            Optional<Counter> counterOptional = counterRepository.getTopByCounterDateBeforeAndDevice_SerialNumberOrderByCounterDateDesc(lastDay, device.getSerialNumber());
            lastCounter = counterOptional.get().getMonoCounter();
            return lastCounter;
        }
    }

    public Integer getLastCounterColourCounter(Device device) {
        LocalDate lastDay = getLastDayOfPreviousMonth();
        Integer lastCounter = counterMapProvider(device.getCounters(), true).get(lastDay);
        if (lastCounter != null) {
            return lastCounter;
        } else {
            Optional<Counter> counterOptional = counterRepository.getTopByCounterDateBeforeAndDevice_SerialNumberOrderByCounterDateDesc(lastDay, device.getSerialNumber());
            lastCounter = counterOptional.get().getColourCounter();
            return lastCounter;
        }
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
        Optional<CopierSettlement> copierSettlementOptional = copierSettlementRepository.getTopByContract_DeviceOrderByDateOfSettlementDesc(device);
        if (copierSettlementOptional.isPresent()) {
            return copierSettlementOptional.get().getClosingMonoCounter();
        } else {
            return device.getContract().getInitialMonoCounter();
        }
    }

    private Integer getLastSettlementColourCounter(Device device) {
        Optional<CopierSettlement> copierSettlementOptional = copierSettlementRepository.getTopByContract_DeviceOrderByDateOfSettlementDesc(device);
        if (copierSettlementOptional.isPresent()) {
            return copierSettlementOptional.get().getClosingColourCounter();
        } else {
            return device.getContract().getInitialColourCounter();
        }
    }

    public LocalDate getLastDayOfPreviousMonth() {
        return LocalDate.now().minusMonths(1).with(TemporalAdjusters.lastDayOfMonth());
    }

    public boolean delete(Long id) {
        Optional<CopierSettlement> optionalCopierSettlement = copierSettlementRepository.findById(id);
        if (optionalCopierSettlement.isPresent()) {
            return true;
        } else
            return false;
    }

}
