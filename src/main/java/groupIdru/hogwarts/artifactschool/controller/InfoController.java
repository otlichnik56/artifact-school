package groupIdru.hogwarts.artifactschool.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InfoController {

    @Value("${server.port}")
    private int numberPort;

    @GetMapping("/getPort")
    public int getPort() {
        return numberPort;
    }
}
