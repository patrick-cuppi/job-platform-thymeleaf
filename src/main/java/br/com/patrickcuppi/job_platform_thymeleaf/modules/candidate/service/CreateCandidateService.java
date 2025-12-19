package br.com.patrickcuppi.job_platform_thymeleaf.modules.candidate.service;

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

  public void execute(CreateCandidateDTO candidate) {

    try {
      RestTemplate rt = new RestTemplate();

      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_JSON);

      HttpEntity<CreateCandidateDTO> request = new HttpEntity<>(candidate, headers);

      var result = rt.postForObject(
          "http://localhost:8080/candidate/",
          request,
          String.class);
    } catch (HttpClientErrorException e) {
      throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Erro ao criar candidato");
    }

  }
}
