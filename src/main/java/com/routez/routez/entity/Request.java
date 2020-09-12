package com.routez.routez.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.routez.routez.View;
import com.routez.routez.enums.RequestStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Data
@EqualsAndHashCode(of = {"id", "clientId"}, callSuper = false)
@Table(name = "request")
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Request extends BaseEntity {

    @Id
    @GeneratedValue
    @JsonView(View.Id.class)
    private Long id;

    @JsonView(View.Summary.class)
    private String routeNum;

    @JsonView(View.Summary.class)
    private LocalDate startTime;

    @JsonView(View.Summary.class)
    private Long clientId;

    @JsonView(View.Summary.class)
    @Enumerated(EnumType.STRING)
    private RequestStatus status;
}

