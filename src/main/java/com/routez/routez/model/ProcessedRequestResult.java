package com.routez.routez.model;

import com.routez.routez.enums.RequestStatus;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class ProcessedRequestResult {
    Long id;
    RequestStatus status;
}
