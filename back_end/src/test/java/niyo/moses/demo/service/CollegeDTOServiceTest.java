package niyo.moses.demo.service;

import niyo.moses.demo.dto.CollegeDTO;
import niyo.moses.demo.dto.SearchParametersWrapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CollegeDTOServiceTest {

    private CollegeDTOService collegeDTOService;

    @BeforeEach
    void setUp() {
        collegeDTOService = new CollegeDTOService();
    }

    @AfterEach
    void tearDown() {
        collegeDTOService = null;
    }

    @Test
    void sortColleges() {
        CollegeDTO collegeDTO = new CollegeDTO("Hudson County Community College", 7984, 184995);
        CollegeDTO collegeDTO1 = new CollegeDTO("Camden County College", 9055, 183938);
        CollegeDTO collegeDTO2 = new CollegeDTO("Raritan Valley Community College", 6856, 186645);

        List<CollegeDTO> collegeDTOS = Arrays.asList(collegeDTO, collegeDTO1, collegeDTO2);
        List<CollegeDTO> sortedColleges = Arrays.asList(collegeDTO2, collegeDTO, collegeDTO1);

        assertThat(collegeDTOService.sortColleges(collegeDTOS), is(sortedColleges));

    }

    @Test
    void uriConstructor() {
        SearchParametersWrapper searchParametersWrapper = new SearchParametersWrapper(52557, 100, "2", 2017);
        String uri = "https://api.data.gov/ed/collegescorecard/v1/schools.json?" +
                "school.degrees_awarded.predominant=2&fields=id,school.name,"+ searchParametersWrapper.getYear()+".student.size" +
                "&per_page=100&zip=52557&distance=1000mi&api_key=leQg9UgJNRHjgbwhhZGzRk8R1N589zuXVz9GRnQw&page=4";

        assertTrue(collegeDTOService.uriConstructor(searchParametersWrapper, 4).equals(uri));
    }
}