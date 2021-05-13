package com.tytanisukcesu.copiers.service;

import com.tytanisukcesu.copiers.entity.Contract;
import com.tytanisukcesu.copiers.entity.CopierSettlement;
import com.tytanisukcesu.copiers.entity.Counter;
import com.tytanisukcesu.copiers.entity.Device;
import com.tytanisukcesu.copiers.repository.CopierSettlementRepository;
import com.tytanisukcesu.copiers.repository.CounterRepository;
import com.tytanisukcesu.copiers.service.exception.ModelNotFoundException;
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
        Contract contract = contractService.save(copierSettlement.getContract());
        Device device = contract.getDevice();
        if (!isExists(device)) {
            CopierSettlement copierSettlementToSave = new CopierSettlement();
            LocalDate dateOfSettlement;
            if (getDateLastSettlement(device) != null) {
                dateOfSettlement = getDateLastSettlement(device).plusMonths(1);
                copierSettlementToSave.setDateOfSettlement(dateOfSettlement);
            } else {
                dateOfSettlement = device.getContract().getStartDate().plusMonths(1).with(TemporalAdjusters.firstDayOfMonth());
                copierSettlementToSave.setDateOfSettlement(dateOfSettlement);
            }
            copierSettlementToSave.setStartingColourCounter(getLastSettlementColourCounter(device));
            copierSettlementToSave.setStartingMonoCounter(getLastSettlementMonoCounter(device));
            copierSettlementToSave.setClosingColourCounter(getLastCounterColourCounter(device, dateOfSettlement));
            copierSettlementToSave.setClosingMonoCounter(getLastCounterMonoCounter(device, dateOfSettlement));
            copierSettlementToSave.setMonoAmount(getMonoAmount(device.getContract().getMonoPagePrice(), copierSettlementToSave.getStartingMonoCounter(), copierSettlementToSave.getClosingMonoCounter()));
            copierSettlementToSave.setColourAmount(getColourAmount(device.getContract().getColorPagePrice(), copierSettlementToSave.getStartingColourCounter(), copierSettlementToSave.getClosingColourCounter()));
            copierSettlementToSave.setTotalAmount(getTotalAmount(device.getContract().getLeasePrice(), copierSettlementToSave.getMonoAmount(), copierSettlementToSave.getColourAmount()));
            copierSettlementToSave.setContract(contract);
            CopierSettlement copierSettlementSaved = copierSettlementRepository.save(copierSettlementToSave);
            LOGGER.info("A new row has been added.");
            return copierSettlementSaved;
        } else {
            return copierSettlementRepository.getTopByContract_DeviceOrderByDateOfSettlementDesc(device).get();
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

    public Integer getLastCounterMonoCounter(Device device, LocalDate dateOfSettlement) {
        Integer lastCounter = counterMapProvider(device.getCounters(), false).get(dateOfSettlement);
        if (lastCounter != null) {
            return lastCounter;
        } else {
            Optional<Counter> counterOptional = counterRepository.getTopByCounterDateBeforeAndDevice_SerialNumberOrderByCounterDateDesc(dateOfSettlement, device.getSerialNumber());
            lastCounter = counterOptional.get().getMonoCounter();
            return lastCounter;
        }
    }

    //jezeli istnieje ostatni to wyrzuc, w przeciwnym wypadku zwroc najbrdziej aktualny
    public Integer getLastCounterColourCounter(Device device, LocalDate dateOfSettlement) {
        Integer lastCounter = counterMapProvider(device.getCounters(), true).get(dateOfSettlement);
        if (lastCounter != null) {
            return lastCounter;
        } else {
            Optional<Counter> counterOptional = counterRepository.getTopByCounterDateBeforeAndDevice_SerialNumberOrderByCounterDateDesc(dateOfSettlement, device.getSerialNumber());
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
        return copierSettlementRepository.findById(id).orElseThrow(() -> new ModelNotFoundException(id,"copier settlement"));
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

    public LocalDate getDateLastSettlement(Device device) {
        Optional<CopierSettlement> copierSettlementOptional = copierSettlementRepository.getTopByContract_DeviceOrderByDateOfSettlementDesc(device);
        return copierSettlementOptional.map(CopierSettlement::getDateOfSettlement).orElse(null);
    }

    public boolean delete(Long id) {
        Optional<CopierSettlement> optionalCopierSettlement = copierSettlementRepository.findById(id);
        if (optionalCopierSettlement.isPresent()) {
            copierSettlementRepository.delete(optionalCopierSettlement.get());
            LOGGER.info("Copier Settlement for id " + id + " has been deleted");
            return true;
        } else{
            LOGGER.warning("Copier Settlement for id " + id + " has not been deleted");
            return false;
        }
    }

    public boolean isExists(Device device) {
        boolean exists = false;
        Optional<CopierSettlement> copierSettlementOptional = copierSettlementRepository.getTopByContract_DeviceOrderByDateOfSettlementDesc(device);
        if (copierSettlementOptional.isPresent()) {
            if (copierSettlementOptional.get().getDateOfSettlement().getMonth().equals(LocalDate.now().getMonth())) {
                exists = true;
            }
        }
        return exists;
    }

    @Transactional
    public CopierSettlement update(Long id, CopierSettlement copierSettlement){
        Optional<CopierSettlement> copierSettlementOptional = copierSettlementRepository.findById(id);
        if(copierSettlementOptional.isPresent()){
            CopierSettlement copierSettlementUpdated = copierSettlementOptional.get();
            copierSettlementUpdated.setStartingColourCounter(copierSettlement.getStartingColourCounter());
            copierSettlementUpdated.setStartingMonoCounter(copierSettlement.getStartingMonoCounter());
            copierSettlementUpdated.setClosingColourCounter(copierSettlement.getClosingColourCounter());
            copierSettlementUpdated.setClosingMonoCounter(copierSettlement.getClosingMonoCounter());
            copierSettlementUpdated.setMonoAmount(copierSettlement.getMonoAmount());
            copierSettlementUpdated.setColourAmount(copierSettlement.getColourAmount());
            copierSettlementUpdated.setTotalAmount(copierSettlement.getTotalAmount());
            copierSettlementUpdated.setContract(copierSettlement.getContract());
            LOGGER.info("Copier Settlement" + " for id " + copierSettlementUpdated.getId() + " has been updated.");
            return copierSettlementUpdated;
        }else{
            LOGGER.warning("Copier settlement for id " + id + " has not been found");
            throw new ModelNotFoundException(id,"copier settlement");
        }
    }

}
