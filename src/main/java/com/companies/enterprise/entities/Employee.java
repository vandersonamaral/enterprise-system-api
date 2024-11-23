package com.companies.enterprise.entities;

import com.companies.enterprise.validation.RequestEmployee;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;


@Entity
@Table(name = "employee")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String cpf;
    private String gender;
    private LocalDate birthdate;
    private Double salary;

    public Employee(RequestEmployee requestEmployee) {
        this.name = requestEmployee.name();
        this.cpf = requestEmployee.cpf();
        this.gender = requestEmployee.gender();
        this.birthdate = requestEmployee.birthdate();
        this.salary = requestEmployee.salary();

    }


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    @ManyToOne
    @JoinColumn(name = "supervisor_id")
    private Employee supervisor;


    @OneToMany(mappedBy = "employee")
    private List<EmployeeProject> employeeProjects;


}
