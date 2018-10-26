package course;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CrudbootApplication {

    private static final Logger log = LoggerFactory.getLogger(CrudbootApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(CrudbootApplication.class, args);
    }

}
