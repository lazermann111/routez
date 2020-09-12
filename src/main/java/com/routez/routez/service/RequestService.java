package com.routez.routez.service;

import com.routez.routez.entity.Request;
import com.routez.routez.enums.RequestStatus;
import com.routez.routez.repository.RequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RequestService {

    private final RequestRepository requestRepository;

    public List<Request> getByStatus(RequestStatus status) {
        return requestRepository.findAllByStatus(status);
    }

}
