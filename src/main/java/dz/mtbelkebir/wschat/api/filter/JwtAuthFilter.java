package dz.mtbelkebir.wschat.api.filter;

import java.io.IOException;

import org.springframework.lang.NonNull;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import dz.mtbelkebir.wschat.api.model.User;
import dz.mtbelkebir.wschat.api.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    JwtService jwtService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        final String jwt = request.getHeader("Authorization");
        if (jwt == null || !jwt.startsWith("Bearer")) {
            filterChain.doFilter(request, response);
            return;
        }

        final String username = jwtService.extractSubject(jwt);
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
        }

    }

}
