package br.com.patrickcuppi.job_platform_thymeleaf.modules.candidate.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileUserDTO {

  private UUID id;
  private String username;
  private String email;
  private String name;
  private String description;

}
