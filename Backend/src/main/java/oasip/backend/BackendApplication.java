package oasip.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import java.util.Date;

//@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@SpringBootApplication
public class BackendApplication {

    public static void main(String[] args) {

        SpringApplication.run(BackendApplication.class, args);

    }

}
