package ma.gov.stagiaire;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import java.net.UnknownHostException;
import java.util.logging.Logger;

@SpringBootApplication
@Slf4j
public class MyApplication {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(MyApplication.class);



     public static void main(String[] args) throws UnknownHostException {
        //SpringApplication.run(MyApplication.class);
        SpringApplication app = new SpringApplication(MyApplication.class);
        Environment env = app.run(args).getEnvironment();

        log.info("Access " +
                "URLs:\n-----------------------------------------------------" +
                "-----\n" +
                "Url Swagger: \thttp://127.0.0.1:{}/swagger-ui/index.html \n" +
                "Url H2 Base: \thttp://127.0.0.1:{}/h2-console\n" +
                "----------------------------------------------------------",env.getProperty("server.port"),env.getProperty("server.port"));

       }

}
