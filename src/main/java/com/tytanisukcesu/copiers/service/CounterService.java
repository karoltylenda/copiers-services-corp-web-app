package com.tytanisukcesu.copiers.service;

import com.tytanisukcesu.copiers.entity.Counter;
import com.tytanisukcesu.copiers.entity.Device;
import com.tytanisukcesu.copiers.repository.CounterRepository;
import com.tytanisukcesu.copiers.repository.DeviceRepository;
import liquibase.pro.packaged.C;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
@Slf4j
public class CounterService {

    private final CounterRepository counterRepository;
    private final DeviceRepository deviceRepository;
    private static final Logger LOGGER = Logger.getLogger(CounterService.class.getName());

    /***
     * TODO
     * Licznik moze sie tylko zwiekszac - jezeli wpiszesz licznik z nową datą to licznik musi byc wiekszy niz poprzedni
     * to samo z datą, jak wpisujesz datę wcześniejszą niż jest ostatnia - to licznik nie może być wyższy
     */


    public Counter findLastCounterByDeviceSerialNumber(String serialNumber) {
        Counter counterList = counterRepository.getFirstByDevice_SerialNumberOrderByCounterDateDesc(serialNumber).get();
        return counterList;
    }

    /***
     * Dodaj logike aby sprawdzil ostatni counter ale do daty ktora chcesz wrzucic
     * Metoda musi rowniez sprawdzic nastepny counter = check
     * */

    private boolean isCounterPresent(Counter counter){
        Optional<Counter> optionalCounter = counterRepository.findCounterByCounterDateAndDeviceSerialNumber(counter.getCounterDate(), counter.getDevice().getSerialNumber());
        if (optionalCounter.isPresent()){
            return true;
        } else {
            return false;
        }
    }

    public Counter save(Counter counter){
        Optional<Device> deviceOptional = deviceRepository.getDeviceBySerialNumber(counter.getDevice().getSerialNumber());
        if (deviceOptional.isPresent() && isCounterPossible(counter)){
            Counter counterSaved = counterRepository.save(counter);
            return counterSaved;
        } else {
            return new Counter();
        }
    }

    private boolean isCounterPossible(Counter counter){
        Optional<Counter> counterBeforeOptional = counterBefore(counter);
        Optional<Counter> counterAfterOptional = counterAfter(counter);
        if (isCounterPresent(counter)){
            return false;
        } else if (counterAfterOptional.isEmpty() && counterBeforeOptional.isEmpty()){
            return true;
        } else if (counterBeforeOptional.isEmpty() && counterAfterOptional.isPresent()){
            if (counter.getMonoCounter()<=counterAfterOptional.get().getMonoCounter() && counter.getColorCounter()<=counterAfterOptional.get().getColorCounter()){
                return true;
            } else {
                return false;
            }
        } else if (counterBeforeOptional.isPresent() && counterAfterOptional.isPresent()){
            if (counter.getMonoCounter()>=counterBeforeOptional.get().getMonoCounter() && counter.getMonoCounter()<=counterAfterOptional.get().getMonoCounter()
                && counter.getColorCounter()>=counterBeforeOptional.get().getColorCounter() && counter.getColorCounter()<=counterAfterOptional.get().getColorCounter()){
                return true;
            } else {
                return false;
            }
        } else if (counterBeforeOptional.isPresent() && counterAfterOptional.isEmpty()){
            if (counter.getMonoCounter()>=counterBeforeOptional.get().getMonoCounter() && counter.getColorCounter()>=counterBeforeOptional.get().getColorCounter()){
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private Optional<Counter> counterBefore(Counter counter){
        Optional<Counter> counterOptional = counterRepository.getTopByCounterDateIsBeforeAndDeviceSerialNumberOrderByCounterDateDesc(counter.getCounterDate(), counter.getDevice().getSerialNumber());
        return counterOptional;
    }

    private Optional<Counter> counterAfter(Counter counter){
        Optional<Counter> counterOptional = counterRepository.getFirstByCounterDateIsAfterAndDeviceSerialNumberOrderByCounterDateAsc(counter.getCounterDate(), counter.getDevice().getSerialNumber());
        return counterOptional;
    }

//    private boolean isCounterGreaterThenLastOne(Counter counter){
//        List<Counter> counters = counterRepository.findAllByDeviceSerialNumberOrderByCounterDateDesc(counter.getDevice().getSerialNumber());
//        Counter lastCounter = counters.get(counters.size()-1);
//
//        if (counter.getCounterDate().isAfter(lastCounter.getCounterDate())
//                && counter.getColorCounter()>=lastCounter.getColorCounter()
//                && counter.getMonoCounter()>=lastCounter.getMonoCounter()
//                && counter.getTotalCounter()>=lastCounter.getTotalCounter()){
//            return true;
//        } else {
//            for (int i = 0; i < counters.size(); i++) {
//                Counter
//                if (counter.getCounterDate().isAfter(counters.get(i).getCounterDate()) && counter.getCounterDate().isBefore(counters.get(i+1).getCounterDate())
//                        && ){
//
//                }
//            }
//        }
//
//    }

    public List<Counter> findAllByDeviceSerialNumber(String serialNumber){
        return counterRepository.findAllByDeviceSerialNumberOrderByCounterDateDesc(serialNumber);
    }

    public Counter findById(Long id) {
        Optional<Counter> counterOptional = counterRepository.findById(id);
        return counterOptional.orElse(new Counter());
    }

    public boolean delete(Long id) {
        Optional<Counter> counter = counterRepository.findById(id);
        if (counter.isPresent()) {
            counterRepository.delete(counter.get());
            return true;
        } else {
            return false;
        }
    }

    //todo UPDATE


}
