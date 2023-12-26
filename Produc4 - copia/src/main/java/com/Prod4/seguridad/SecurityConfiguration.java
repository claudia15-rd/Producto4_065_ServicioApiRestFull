package com.Prod4.seguridad;

import com.Prod4.jwt.JWTAuthorizationFilter;
import com.Prod4.servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity

public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired(required = true)
    private UsuarioServicio usuarioServicio;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(usuarioServicio);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());

    }

    String[] resources = new String[]{
            "/include/**", "/css/**", "/icons/**", "/img/**", "/js/**", "/layer/**"
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()

                //No va
                .antMatchers("/v1/authenticate").permitAll()
                .antMatchers("/v3/api-docs/**").permitAll()
                .antMatchers("/swagger-ui/**").permitAll()
                .antMatchers("/swagger-ui.html").permitAll()
                .antMatchers("/v3/api-docs/swagger-ui*").permitAll()
        // .requestMatchers("/v1/authenticate","/v3/api-docs/**","/swagger-ui/**","/swagger-ui.html").permitAll()
                //////////////////////////////////////////////

                .antMatchers(HttpMethod.POST, "/loginSecure").permitAll()

                .antMatchers("/registro*").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/index*").permitAll()
                .antMatchers("/login*").permitAll()


                .antMatchers("/clientes*").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/vehiculos*").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/alquileres*").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")


                //Estas son las que se puede entrar sin segurdad
                .antMatchers("/api/listarClientes*").permitAll()
                .antMatchers("/api/grabarClientes*").permitAll()
                .antMatchers("/api/borrarCliente*").permitAll()

                .antMatchers("/api/listarAlquilerVehiculos*").permitAll()
                .antMatchers("/api/grabarAlguileres*").permitAll()
                .antMatchers("/api/borrarAlquiler*").permitAll()

                //El resto se tienen que validar
                .anyRequest().authenticated();
    }
}






   /* @Override
  protected void configure(HttpSecurity http) throws Exception {

      http
              .authorizeRequests()
              .antMatchers(resources).permitAll()
              .antMatchers("/registro*").access("hasRole('ROLE_ADMIN')")
              .antMatchers("/index*").permitAll()
           //   .antMatchers("/login*").permitAll()

              .antMatchers(HttpMethod.POST,"/loginSecure*").permitAll()

              .antMatchers("/api/listarClientes*").permitAll()
              .antMatchers("/api/grabarClientes*").permitAll()
              .antMatchers("/api/borrarCliente*").permitAll()

              .antMatchers("/api/listarAlquilerVehiculos*").permitAll()
              .antMatchers("/api/grabarAlguileres*").permitAll()
              .antMatchers("/api/borrarAlquiler*").permitAll()

              .antMatchers("/clientes*").access("hasRole('ROLE_ADMIN')")
              .antMatchers("/vehiculos*").access("hasRole('ROLE_ADMIN')")
              .antMatchers("/alquileres*").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")

              .anyRequest().authenticated()
             //   .and()



           //   .formLogin().
          //    .loginPage("/login")
          //    .permitAll()
           //   .and()

            //  .logout()
           //   .clearAuthentication(true)
          //    .permitAll()
         //     .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
         //     .logoutSuccessUrl("/login?logout");

      /////////////////////////////////////////////////////////////7












}*/
