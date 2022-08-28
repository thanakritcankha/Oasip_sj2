package oasip.backend.Service;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import oasip.backend.DTOs.Authentication.LoginDTO;
import oasip.backend.DTOs.User.UserListAllDto;
import oasip.backend.Enitities.User;
import oasip.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AuthenticationService {
    @Autowired
    UserRepository userRepository;

//    private Argon2PasswordEncoder passwordEncoder = new Argon2PasswordEncoder(16, 26, 1, 65536, 10);
    private Argon2PasswordEncoder passwordEncoder = new Argon2PasswordEncoder();

    public boolean match(LoginDTO oldUser) {
        User user = userRepository.findByEmail(oldUser.getEmail());
        if(user == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "A user with the specified email DOES NOT exist");
        }
        if(!(passwordEncoder.matches(oldUser.getPassword(),user.getPassword()))){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Password NOT Matched");
        }
        return true;
    }

//    public boolean match() {
//        Argon2 argon2 = Argon2Factory.create(
//                Argon2Factory.Argon2Types.ARGON2id,
//                10,
//                32);
//        String hash = argon2.hash(30, 65536, 1,"password");
////        System.out.println(hash);
////        Argon2PasswordEncoder passwordEncoder = new Argon2PasswordEncoder();
////        String s = passwordEncoder.encode("password");
//        boolean success = argon2.verify(hash, "password");
////        System.out.println(success);
//
////        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        Argon2PasswordEncoder encoder = new Argon2PasswordEncoder(16, 26, 1, 65536, 10);
//        String result = encoder.encode("ram123sdadsadsadsa");
//        System.out.println(result);
//        System.out.println(encoder.matches("ram123", result));
////        System.out.println(result);
//        return true;
//    }
}
