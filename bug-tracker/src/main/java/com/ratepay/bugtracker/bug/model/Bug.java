package com.ratepay.bugtracker.bug.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(indexes = @Index(name = "fn_index", columnList = "title"))
public class Bug {

    @Id
    @SequenceGenerator(
            name = "bug_id_sequence",
            sequenceName = "bug_id_sequence",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "bug_id_sequence")
    private Long id;
    @Column(nullable = false)
    private  String title;
    @Column(nullable = false)
    private String assignTo;
    @Column(nullable = false)
    private String createdBy;
    private LocalDateTime createdDate;
    private Boolean isClosed;
    private LocalDateTime lastModifiedDate;
    private  String lastModifiedBy;



}
