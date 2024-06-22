package com.ms.lib.employee.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "t_employee")
@Entity
public class Employee {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "country")
    private String country;

    @Column(name = "age")
    private int age;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name= "department_id", referencedColumnName = "id")
    private Department department;


}
