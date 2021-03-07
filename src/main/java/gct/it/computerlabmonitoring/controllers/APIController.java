package gct.it.computerlabmonitoring.controllers;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController @RequestMapping("/api")
public class APIController {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/run")
    public String getLangs(@RequestParam("language") String language, 
    @RequestParam("code") String code,
    @RequestParam("input") String input) throws JsonMappingException, JsonProcessingException {

        String url = "https://codexweb.netlify.app/.netlify/functions/enforceCode";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> map = new HashMap<>();
        map.put("code", code);
        map.put("language", language);
        map.put("input", input);
        
        HttpEntity<Map<String, String>> req = new HttpEntity<>(map, headers);
        ResponseEntity<String> res = restTemplate.postForEntity(url, req, String.class);
        
        return res.getBody();
    }
}
