package br.com.patrickcuppi.job_platform_thymeleaf.modules.company.dto;

import lombok.Data;

@Data
public class CreateJobsDTO {
  private String description;
  private String level;
  private String benefits;
}
