package ro.ubb.downWork.profilemicro.init;

/**
 * Created by CristianCosmin on 19.10.2017.
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import ro.ubb.downWork.profilemicro.controller.PersonController;

@SpringBootApplication
public class ProfileMicroApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProfileMicroApplication.class, args);
    }
}
