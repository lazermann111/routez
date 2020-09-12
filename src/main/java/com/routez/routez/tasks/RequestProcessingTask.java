package com.routez.routez.tasks;


import com.routez.routez.entity.Request;
import com.routez.routez.enums.RequestStatus;
import com.routez.routez.model.ProcessedRequestResult;
import com.routez.routez.repository.RequestRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;


@Slf4j
@Component
public class RequestProcessingTask {

    private final RequestRepository requestRepository;
    private RestTemplate restTemplate;

    @Autowired
    public RequestProcessingTask(RequestRepository requestRepository, RestTemplateBuilder builder) {
        this.requestRepository = requestRepository;
        this.restTemplate = builder
                .rootUri("http://localhost:8080")
                .build();
    }

    @Scheduled(fixedDelay = 60000L)
    @Transactional
    @SuppressWarnings("unchecked")
    public void execute() {
        log.info("Starting process request task");
        var entities = requestRepository.findAllByStatus(RequestStatus.STARTED);

        var processingResult = restTemplate.postForEntity("/process",
                        entities.stream().map(Request::getId).collect(toList()),
                List.class);
        //todo parametrize response
        List<ProcessedRequestResult> response = (List<ProcessedRequestResult>) processingResult.getBody()
                .stream()
                .map(el -> {
                        var map = ((Map)el);
                        return  new ProcessedRequestResult((Long.valueOf(map.get("id").toString())), (RequestStatus.valueOf(map.get("status").toString()) ));
                    })
                .collect(toList());

        var map = entities.stream().collect(groupingBy(Request::getId));
        for(ProcessedRequestResult res : response)
        {
            map.get(res.getId()).forEach(a -> a.setStatus(res.getStatus()));
        }
        requestRepository.saveAll(entities);

        log.info("Finishing process request task");
    }

}
