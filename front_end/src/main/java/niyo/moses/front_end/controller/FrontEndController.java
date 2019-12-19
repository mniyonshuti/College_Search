package niyo.moses.front_end.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import niyo.moses.front_end.dto.CollegeDTO;
import niyo.moses.front_end.dto.SearchParametersWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.List;

@Controller
//@RequestMapping("/college")
public class FrontEndController {
    @Autowired
    private RestTemplate restTemplate;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @GetMapping(value = {"/", "/home"})
    @ApiOperation(value = "Returns home page")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Call successful")
    })
    public String homePage(Model model) {
        model.addAttribute("searchParams", new SearchParametersWrapper());
        return "index";
    }

    @PostMapping("/search")
    @ApiOperation(value = "Returns List of searched colleges")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Call successful")
    })
    public String search(@Valid @ModelAttribute("searchParams") SearchParametersWrapper searchParametersWrapper, BindingResult bindingResult,
    @RequestParam(defaultValue = "0") Integer pageNo, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "index";
        }
        List<CollegeDTO> collegeDTOList = restTemplate.postForObject("http://localhost:8080/college/search",
                searchParametersWrapper, List.class);
        model.addAttribute("colleges", collegeDTOList);
        model.addAttribute("currentPageNo", pageNo);
        model.addAttribute("zipCode",searchParametersWrapper.getZipCode());
        return "searchResults";
    }

}
