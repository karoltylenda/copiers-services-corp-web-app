package com.tytanisukcesu.copiers.repository;

import com.tytanisukcesu.copiers.entity.Counter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface CounterRepository extends JpaRepository<Counter, Long> {

    List<Counter> findAllByDeviceSerialNumberOrderByCounterDateDesc(String serialNumber);

    Optional<Counter> findCounterByCounterDateAndDeviceSerialNumber(LocalDate date, String serialNumber);

    Optional<Counter> getTopByCounterDateBeforeAndDevice_SerialNumberOrderByCounterDateDesc(LocalDate localDate, String serialNumber);

    Optional<Counter> getFirstByCounterDateAfterAndDevice_SerialNumberOrderByCounterDateAsc(LocalDate counterDate, String serialNumber);

//    @Query("select p from Counter p where p.id=:id")
//    Optional<Counter> test(@Param("id") Long id);
//
//    @Query("select p from Counter p where p.device.serialNumber=:sn and p.counterDate=:ld")
//    Optional<Counter> test2(@Param("sn") String sn, @Param("ld")LocalDate ld);
//
//    @Query("select p from Counter p where p.device.serialNumber=:sn and p.counterDate<=:ld")
//    Optional<Counter> test3(@Param("sn") String sn, @Param("ld")LocalDate ld);
//
//    Optional<Counter> findFirstByCounterDateBeforeAndDevice_SerialNumberOrderByTotalCounterDesc(LocalDate localDate, String serialNumber);




}
