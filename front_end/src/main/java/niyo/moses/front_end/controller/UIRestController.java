package niyo.moses.front_end.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UIRestController {
    @GetMapping("/url/{zipCode}")
    public String getUrl(@PathVariable("zipCode") String zipCode){
        return  "https://maps.googleapis.com/maps/api/js?address="+zipCode+"&key=AIzaSyCk-X9zQpYix4tLG82_42AWRtULmcF0QQA&callback=initMap";

    }
}
