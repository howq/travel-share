package personal.zhou.travelshare.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import personal.zhou.travelshare.util.ResponseUtil;

@RestController
@RequestMapping("/hello-world")
public class HelloWorldController {

    @GetMapping
    public Object hello() {
        return ResponseUtil.ok("Hello World");
    }
}
