package com.distributed.reservation_system.filter;

import com.distributed.common.exception.AuthException;
import com.distributed.reservation_system.util.CustomUserDetailsServiceImpl;
import com.distributed.reservation_system.util.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class JWTAuthenticationFilter extends OncePerRequestFilter {
    private final CustomUserDetailsServiceImpl customUserDetailsService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try{
            String jwtToken = getJwtFromHeader(request);
            if(jwtToken!=null){
                Claims claims = JwtUtil.validateToken(jwtToken);
                String userId = claims.getSubject();

                UserDetails userDetails = customUserDetailsService.loadUserByUsername(userId);
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request)); //Question: what is this?

                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

            }
            filterChain.doFilter(request, response);
        }catch (AuthException ex){
//            log.info("Errrorr:"+ex.getMessage());
            throw ex;
        }
        catch (Exception e) {
            throw new AuthException("Could not set authentication",e);
        }

    }

    public String getJwtFromHeader(HttpServletRequest request){
        String headerAuth = request.getHeader("Authorization");
        if(headerAuth !=null && headerAuth.startsWith("Bearer ")){
            return headerAuth.substring(7);
        }
        return null;
    }
}
