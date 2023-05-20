package com.example.vuespring2.config.Jwt.filter;
import com.example.vuespring2.config.Jwt.JwtServices.JwtServices;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter{
  private  final JwtServices jwtServices;
  private final UserDetailsService userDetailsService;

   /* @Autowired
    public JwtAuthenticationFilter(JwtServices jwtServices,UserDetailsService userDetailsService){
        _jwtServices=jwtServices;
        _userDetailsService=userDetailsService;
    }*/
    @Override
    protected void doFilterInternal(
           @NotNull HttpServletRequest request,
           @NotNull  HttpServletResponse response,
           @NotNull  FilterChain filterChain) throws ServletException, IOException {
        System.out.println("wakjdkjhasjkghdjash");
        final String authHeader=request.getHeader("Authorization");
        final String jwt;
        final String userEmail;
        if(authHeader==null ||!authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request,response);
            return;
        }
        jwt=authHeader.substring(7);
     //   this.jwtServices=
        userEmail=this.jwtServices.exstractUsername(jwt);

        if(userEmail!=null && SecurityContextHolder.getContext().getAuthentication()==null){
            UserDetails userDetails=this.userDetailsService.loadUserByUsername(userEmail);
            if(jwtServices.isTokenIsValid(jwt,userDetails)){
                UsernamePasswordAuthenticationToken authToken=new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request,response);

    }
}
