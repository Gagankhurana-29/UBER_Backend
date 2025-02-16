package com.khuragag.project.uber.uber.security;

import com.khuragag.project.uber.uber.entities.User;
import com.khuragag.project.uber.uber.services.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

@Configuration
@RequiredArgsConstructor
public class JWTAuthFilter extends OncePerRequestFilter {
private final JWTService jwtService;
private final UserService userService;

  @Autowired
  @Qualifier("handlerExceptionResolver")
  private final HandlerExceptionResolver handlerExceptionResolver;

  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain){
      try{
          final String requestTokenHeader = request.getHeader("Authorization");
          System.out.println("Gagan token is " + requestTokenHeader);
          if(requestTokenHeader == null || !requestTokenHeader.startsWith("Bearer ")) {
              filterChain.doFilter(request, response);
              return;
          }
          String token = requestTokenHeader.split("Bearer ")[1];
          System.out.println("Token after bearer is " + token);
          Long userId = jwtService.getUserIdFromToken(token);
          if(userId != null && SecurityContextHolder.getContext().getAuthentication() == null){
             User user = userService.getUserById(userId);
              UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                      new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
              usernamePasswordAuthenticationToken.setDetails(
                      new WebAuthenticationDetailsSource().buildDetails(request)
              );
              SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
          }
          filterChain.doFilter(request,response);
      }
      catch (Exception ex){
          System.out.println("Exception occurred in JWTAuthFilter: " + ex.getMessage());
          handlerExceptionResolver.resolveException(request,response,null,ex);
      }
  }
}
