package br.com.patrickcuppi.job_platform_thymeleaf.modules.candidate.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpClientErrorException.Unauthorized;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.patrickcuppi.job_platform_thymeleaf.modules.candidate.dto.JobDTO;

@Service
public class FindJobsService {

  @Value("${hostApiJobPlatform}")
  private String hostApiJobPlatform;

  public List<JobDTO> execute(String token, String filter) {

    RestTemplate rt = new RestTemplate();

    HttpHeaders headers = new HttpHeaders();
    headers.setBearerAuth(token);

    HttpEntity<Map<String, String>> request = new HttpEntity<>(headers);

    var url = hostApiJobPlatform.concat("/candidate/job");

    UriComponentsBuilder builder = UriComponentsBuilder
        .fromUriString(url)
        .queryParam("filter", filter);

    ParameterizedTypeReference<List<JobDTO>> responseType = new ParameterizedTypeReference<List<JobDTO>>() {
    };

    try {
      var result = rt.exchange(builder.toUriString(),
          HttpMethod.GET,
          request,
          responseType);

      return result.getBody();
    } catch (Unauthorized e) {
      throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "Token invalid or expired");
    }
  }
}
