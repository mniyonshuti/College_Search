package niyo.moses.demo.controller;

import com.google.gson.*;
import niyo.moses.demo.dto.CollegeDTO;
import niyo.moses.demo.dto.SearchParametersWrapper;
import niyo.moses.demo.service.CollegeDTOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/college")
public class CollegeController {


    @Autowired
    private CollegeDTOService collegeDTOService;
    @Autowired
    private RestTemplate restTemplate;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @PostMapping("/search")
    public ResponseEntity<String> collegeSearchResults(@RequestBody SearchParametersWrapper searchParametersWrapper) {
        if(searchParametersWrapper == null){
            return new ResponseEntity<String>("Invalid search parameters", HttpStatus.BAD_REQUEST);
        }
        List<CollegeDTO> searchResults = new ArrayList<>();

        //Getting url
        String url = collegeDTOService.uriConstructor(searchParametersWrapper, 0);
        System.out.println(url);
        String collegesAsString = restTemplate.getForObject(url, String.class);

        Gson gson = new Gson();
        JsonParser jsonParser = new JsonParser();

        //Getting the number of pages
        JsonObject jsonObject = (JsonObject) jsonParser.parse(collegesAsString);
        JsonElement metadata = jsonObject.get("metadata");
        JsonObject metadata2 = (JsonObject) jsonParser.parse(String.valueOf(jsonObject.get("metadata")));
        int totalRecords = gson.fromJson(metadata2.get("total"), Integer.class);
        int numberOfPages = totalRecords / 100;

        //Search results
        JsonArray colleges = jsonObject.get("results").getAsJsonArray();
        collegeDTOService.convertJsonToList(searchResults, colleges, searchParametersWrapper.getYear());
        int i = 1;

        //Calling API multiple times
        while (i <= numberOfPages) {
            System.out.println("while executed");
            url = collegeDTOService.uriConstructor(searchParametersWrapper, i);
            String collegesAsString2 = restTemplate.getForObject(url, String.class);
            JsonObject jsonObject2 = (JsonObject) jsonParser.parse(collegesAsString2);
            JsonArray colleges2 = jsonObject2.get("results").getAsJsonArray();
            collegeDTOService.convertJsonToList(searchResults, colleges2, searchParametersWrapper.getYear());
            i++;
        }

        collegeDTOService.sortColleges(searchResults);

        return new ResponseEntity<>(searchResults.toString(), HttpStatus.OK);
    }

}
