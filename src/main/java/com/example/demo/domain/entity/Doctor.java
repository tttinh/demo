package com.example.demo.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "doctor")
@Getter
@Setter
@NoArgsConstructor
public class Doctor {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String mobile;
    
    @Column(columnDefinition="TIMESTAMP NOT NULL DEFAULT NOW()", updatable = false, insertable = false)
    private Timestamp createdAt;

    @Column(columnDefinition="TIMESTAMP NOT NULL DEFAULT NOW() ON UPDATE NOW()", updatable = false, insertable = false)
    private Timestamp updatedAt;

    @ManyToOne
    @JoinColumn(name = "departmentId")
    private Department department;
}
