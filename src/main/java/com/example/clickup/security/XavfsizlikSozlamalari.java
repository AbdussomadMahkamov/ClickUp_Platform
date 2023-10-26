package com.example.clickup.security;

import com.example.clickup.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Properties;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class XavfsizlikSozlamalari extends WebSecurityConfigurerAdapter {
    @Autowired
    Filter filter;
    @Autowired
    UserService userService;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers(
                        "/users/addUser"
                        ,"/users/userslogin"
                        ,"/users/tasdiqlash"
//                        ,"/ishchilarSoniVaReklama/addIshchilarSoni"
//                        ,"/ishchilarSoniVaReklama/addReklama"
//                        ,"/workspace/addWorkSpace"
//                        ,"/workspace/AddEditRemove/{{workspaceId}}/{{lavozimId}}"
//                        ,"/workspace/joinWorkSpace"
//                        ,"/workspace/signUpForJoin"
//                        ,"/space/addStatus"
//                        ,"/space/addTemplate"
//                        ,"/space/addSpaceApp"
//                        ,"/space/addSpaceView"
//                        ,"/space/addSpace/{{workSpaceId}}/{{templateId}}"
//                        ,"/folder/addFolder/{{spaceId}}/{{templateId}}"
//                        ,"/tasks/addDependecies"
//                        ,"/tasks/addPriority"
//                        ,"/tasks/addTasks/{{priorityId}}/{{dependenciesId}}/{{listsId}}/{{folderId}}"
                ).permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic()
                .disable();
        http
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Bean
    public JavaMailSender getJavaMailSender(){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("mahkamovabdussomad@gmail.com");
        mailSender.setPassword("iglkjogwnljiwbcz");
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol","smtp");
        props.put("mail.smtp.auth","true");
        props.put("mail.smtp.starttls.enable","true");
        props.put("mail.debug","true");
        return mailSender;
    }
    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userService)
                .passwordEncoder(passwordEncoder());
        super.configure(auth);
    }
}

/*
                .csrf() - Post mappinglarni ishlashiga yo'l ochib beradi. Aks holda postdan boshqa barchsi ishlaydi.
                .disable() - .csrf ni o'chirib qo'yganimiz uchun post qilish yoqiladi.
                .authorizeRequests() - lavozimlarni qaysi huquqlari bu apiga kirishga ruxsati borligini tekshiradi.
                .antMatchers("/users/?").permitAll() - url manzilni hech qanday avtorizatsiyalarsiz ochib qo'yadi.
                .anyRequest() - har qanday apilarni mavjudligini tekshiradi.
                .authenticated() - login va parol orqali tasdiqlab tizmga kirgan bo'lishi kerak.
                .and()
                .httpBasic() - biror bir apiga kirmoqchi bo'lganimizda server tomonidan berilgan login va parol
                orqali kirishimiz kerak boladi. Undan keyin esa api larga yo'l ochiladi.
                .disable(); - bu buyruq orqali esa shu login parol terish oynasi yo'qotiladi.
 */