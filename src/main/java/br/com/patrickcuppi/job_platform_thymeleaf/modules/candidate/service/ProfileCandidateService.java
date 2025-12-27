package br.com.patrickcuppi.job_platform_thymeleaf.modules.candidate.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpClientErrorException.Unauthorized;
import org.springframework.web.client.RestTemplate;

import br.com.patrickcuppi.job_platform_thymeleaf.modules.candidate.dto.ProfileUserDTO;

@Service
public class ProfileCandidateService {

  @Value("${hostApiJobPlatform}")
  private String hostApiJobPlatform;

  public ProfileUserDTO execute(String token) {

    RestTemplate rt = new RestTemplate();

    HttpHeaders headers = new HttpHeaders();
    headers.setBearerAuth(token);

    HttpEntity<Map<String, String>> request = new HttpEntity<>(headers);

    var url = hostApiJobPlatform.concat("/candidate/");

    try {
      var result = rt.exchange(url,
                HttpMethod.GET,
        request,
        ProfileUserDTO.class);

    return result.getBody();
  } catch (Unauthorized e) {
    throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "Token invalid or expired");
  }
  }
}
