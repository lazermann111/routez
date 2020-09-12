package com.routez.routez.repository;

import com.routez.routez.entity.Request;
import com.routez.routez.enums.RequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
    List<Request> findAllByStatus(RequestStatus status);
    List<Request> findALlByClientId(Long clientId);
}
