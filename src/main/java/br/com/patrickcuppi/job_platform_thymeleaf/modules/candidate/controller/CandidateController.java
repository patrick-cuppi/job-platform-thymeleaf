package br.com.patrickcuppi.job_platform_thymeleaf.modules.candidate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.patrickcuppi.job_platform_thymeleaf.modules.candidate.service.CandidateService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/candidate")
public class CandidateController {

  @Autowired
  private CandidateService candidateService;

  @GetMapping("/login")
  public String login() {
    return "candidate/login";
  }

  @PostMapping("/signIn")
  public String signIn(RedirectAttributes redirectAttributes, HttpSession session, String username, String password) {

    try {
      var token = this.candidateService.login(username, password);
      var grants = token
          .getRoles()
          .stream()
          .map(role -> new SimpleGrantedAuthority("ROLE_" + role.toUpperCase())).toList();

      UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(null, null,
          grants);
      authToken.setDetails(token);

      SecurityContextHolder.getContext().setAuthentication(authToken);
      SecurityContext securityContext = SecurityContextHolder.getContext();
      session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
      session.setAttribute("token", token);

      return "redirect:/candidate/profile";

    } catch (HttpClientErrorException e) {
      redirectAttributes.addFlashAttribute("error_message", "Usuário ou senha incorretos, tente novamente.");
      return "redirect:/candidate/login";
    }
  }

  @GetMapping("/profile")
  @PreAuthorize("hasRole('CANDIDATE')")
  public String profile() {
    return "candidate/profile";
  }
}
