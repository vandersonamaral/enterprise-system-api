package com.companies.enterprise.dtos.out;

import com.companies.enterprise.domain.entities.Project;
import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProjectDto {
    private Long id;
    private String name;
    private Double cost;
    private LocalDate startDate;
    private LocalDate endDate;
    private Double value;

    public ProjectDto(Project project) {
        this.id = project.getId();
        this.name = project.getName();
        this.cost = project.getCost();
        this.startDate = project.getStartDate();
        this.endDate = project.getEndDate();
        this.value = project.getValue();

    }

}
