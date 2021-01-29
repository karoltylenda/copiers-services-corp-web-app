package com.tytanisukcesu.copiers.service;

import com.tytanisukcesu.copiers.entity.Counter;
import com.tytanisukcesu.copiers.repository.CounterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CounterService {

    private final CounterRepository counterRepository;

    /***
     * TODO
     * Licznik moze sie tylko zwiekszac - jezeli wpiszesz licznik z nową datą to licznik musi byc wiekszy niz poprzedni
     * to samo z datą, jak wpisujesz datę wcześniejszą niż jest ostatnia - to licznik nie może być wyższy
     */


    public Counter save(Counter counter) {
        return counterRepository.save(counter);
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
