package br.com.patrickcuppi.job_platform_thymeleaf.modules.candidate.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import br.com.patrickcuppi.job_platform_thymeleaf.modules.candidate.dto.CreateCandidateDTO;

@Service
public class CreateCandidateService {

  @Value("${hostApiJobPlatform}")
  private String hostApiJobPlatform;

  public void execute(CreateCandidateDTO candidate) {

    try {
      RestTemplate rt = new RestTemplate();

      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_JSON);

      HttpEntity<CreateCandidateDTO> request = new HttpEntity<>(candidate, headers);

      var url = hostApiJobPlatform.concat("/candidate/");
    
      rt.postForObject(
          url,
          request,
          String.class);
    } catch (HttpClientErrorException e) {
      throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Erro ao criar candidato");
    }

  }
}
