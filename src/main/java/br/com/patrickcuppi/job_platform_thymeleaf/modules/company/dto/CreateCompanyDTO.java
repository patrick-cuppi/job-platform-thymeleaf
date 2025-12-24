package br.com.patrickcuppi.job_platform_thymeleaf.modules.company.dto;

import lombok.Data;

@Data
public class CreateCompanyDTO {
  private String name;
  private String username;
  private String email;
  private String description;
  private String website;
  private String password;
}
