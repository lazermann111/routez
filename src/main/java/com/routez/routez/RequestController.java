package com.routez.routez;

import com.fasterxml.jackson.annotation.JsonView;
import com.routez.routez.entity.Request;
import com.routez.routez.enums.RequestStatus;
import com.routez.routez.model.ProcessedRequestResult;
import com.routez.routez.service.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

import static java.util.stream.Collectors.toList;

@RestController
@RequiredArgsConstructor
public class RequestController {

    private final RequestService requestService;

    @GetMapping("/{status}")
    @JsonView(View.Summary.class)
    public ResponseEntity<List<Request>> getByCLientId(@PathVariable RequestStatus status) {
        return ResponseEntity.ok(requestService.getByStatus(status));
    }

    @PostMapping("/process")
    public ResponseEntity<List<ProcessedRequestResult>> processPayment(@RequestBody List<Long> ids) {
        return ResponseEntity.ok(ids
                .stream()
                .map(r -> new ProcessedRequestResult(r,
                        new Random().nextInt(2) < 1 ? RequestStatus.STARTED
                                : r % 2 == 0 ? RequestStatus.ERROR
                                : RequestStatus.COMPLETED)).collect(toList()));
    }

    @GetMapping("/short/{status}")
    @JsonView(View.Id.class)
    public ResponseEntity<List<Request>> getByCLientIdShort(@PathVariable RequestStatus status) {
        return ResponseEntity.ok(requestService.getByStatus(status));
    }
}
