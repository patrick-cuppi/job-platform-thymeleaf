package br.com.patrickcuppi.job_platform_thymeleaf.modules.company.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCompanyDTO {
  private String name;
  private String username;
  private String email;
  private String description;
  private String website;
  private String password;
}
