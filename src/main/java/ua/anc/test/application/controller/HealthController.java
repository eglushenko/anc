package ua.anc.test.application.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * If application run success expected status 200 OK
 */

@RestController
@RequestMapping("/api/v1")
public class HealthController {

    @GetMapping("/health")
    public void health(){

    }

}
