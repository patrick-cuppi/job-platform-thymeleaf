package br.com.patrickcuppi.job_platform_thymeleaf.modules.company.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.patrickcuppi.job_platform_thymeleaf.modules.candidate.dto.JobDTO;

@Service
public class ListAllJobsCompanyService {

  @Value("${hostApiJobPlatform}")
  private String hostApiJobPlatform;

  public List<JobDTO> execute(String token) {

    RestTemplate rt = new RestTemplate();

    HttpHeaders headers = new HttpHeaders();
    headers.setBearerAuth(token);

    HttpEntity<String> entity = new HttpEntity<>(headers);

    ParameterizedTypeReference<List<JobDTO>> responseType = new ParameterizedTypeReference<List<JobDTO>>() {
    };

    var url = hostApiJobPlatform.concat("/company/job/");

    var result = rt.exchange(url, HttpMethod.GET, entity, responseType);
    return result.getBody();
  }
}
