package vip.ifmm.rolesystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
@MapperScan(basePackages = {"vip.ifmm.rolesystem.mapper"})
public class RoleSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(RoleSystemApplication.class, args);
    }

}

