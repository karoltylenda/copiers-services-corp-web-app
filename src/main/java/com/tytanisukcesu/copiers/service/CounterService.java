package com.tytanisukcesu.copiers.service;

import com.tytanisukcesu.copiers.entity.Counter;
import com.tytanisukcesu.copiers.repository.CounterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
@Slf4j
public class CounterService {

    private final CounterRepository counterRepository;

    /***
     * TODO
     * Licznik moze sie tylko zwiekszac - jezeli wpiszesz licznik z nową datą to licznik musi byc wiekszy niz poprzedni
     * to samo z datą, jak wpisujesz datę wcześniejszą niż jest ostatnia - to licznik nie może być wyższy
     */


//    public Counter save(Counter counter) {
//        Optional<Counter> counterOptional = counterRepository.getCounterByDevice_SerialNumber(counter.getDevice().getSerialNumber());
//        if (counterOptional.isPresent()) {
//            Counter counterFound = counterOptional.get();
//            LocalDate lastCounterDate = counterFound.getCounterDate();
//            if (counter.getCounterDate().isBefore(lastCounterDate) && (counter.getMonoCounter() <= counterFound.getMonoCounter()) && counter.getColorCounter() <= counterFound.getColorCounter()) {
//                return counterRepository.save(counter);
//            }
//        } else {
//            Counter counterToSave = counterRepository.save(counter);
//            return counterToSave;
//        }
//    }
    public Counter findAllByDeviceSerialNumber(String serialNumber) {
        Counter counterList = counterRepository.getFirstByDevice_SerialNumberOrderByCounterDateDesc(serialNumber).get();
        return counterList;
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
