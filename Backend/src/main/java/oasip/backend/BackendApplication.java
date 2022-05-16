package oasip.backend;

import oasip.backend.Validation.Validation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class BackendApplication {

    public static void main(String[] args) {
//        Validation validation = new Validation();
//        validation.Email("somsuan.s@kmutt@ac.th");
//        System.out.println(validation.getTextError());
//        int yy = 121;
//        int mm = 5;
//        int dd = 24;
//        int hrs = 7;
//        int min = 50;
//        int sec = 0;
//        Date date = new Date(yy,mm,dd,hrs,min,sec);
//        System.out.println("date \n"+date);
//        validation.DateFuture(date);
//        System.out.println(validation.getTextError());
//        validation.stringLength("","sdjajkldhjaskdh","dsahnjdajshdjhasjklhd");
//        System.out.println(validation.getTextError());
        SpringApplication.run(BackendApplication.class, args);
    }

}
