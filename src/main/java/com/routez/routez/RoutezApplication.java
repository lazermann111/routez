package com.routez.routez;

import com.routez.routez.entity.Request;
import com.routez.routez.enums.RequestStatus;
import com.routez.routez.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

@SpringBootApplication
@EnableJpaAuditing
@EnableScheduling
public class RoutezApplication {

    public static void main(String[] args) {
        SpringApplication.run(RoutezApplication.class, args);
    }

    @Autowired
    RequestRepository requestRepository;

    @PostConstruct
    private void fillDb() {
        for (long i = 0; i < 100; i++) {
            requestRepository.save(new Request(i, "Route" + i,
                    LocalDate.now(), i, RequestStatus.STARTED));
        }
    }

}
