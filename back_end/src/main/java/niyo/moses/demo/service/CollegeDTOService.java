package niyo.moses.demo.service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import niyo.moses.demo.dto.CollegeDTO;
import niyo.moses.demo.dto.SearchParametersWrapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CollegeDTOService {

    public List<CollegeDTO> sortColleges(List<CollegeDTO> collegeDTOS) {
        collegeDTOS.sort((a, b) -> {
            return a.getStudentsSize() - b.getStudentsSize();
        });
        return collegeDTOS;
    }

    public String uriConstructor(SearchParametersWrapper searchParametersWrapper, int page) {
        return "https://api.data.gov/ed/collegescorecard/v1/schools.json?" +
                "school.degrees_awarded.predominant="+searchParametersWrapper.getPredominantDegrees()+
                "&fields=id,school.name,"+searchParametersWrapper.getYear()+".student.size&per_page=100&zip=52557" +
                "&distance="+searchParametersWrapper.getDistance()+"mi&api_key=leQg9UgJNRHjgbwhhZGzRk8R1N589zuXVz9GRnQw" +
                "&page="+page;
    }

    public void convertJsonToList(List<CollegeDTO> collegeDTOS, JsonArray jsonArray, int year) {
        Gson gson = new Gson();
        JsonParser jsonParser = new JsonParser();
        for (int i = 0; i < jsonArray.size(); i++) {
            JsonObject jsonObject = (JsonObject) jsonArray.get(i);
            String schoolName = gson.fromJson(jsonObject.get("school.name"), String.class);
            int studentsSize = gson.fromJson(jsonObject.get(year + ".student.size"), Integer.class);
            long id = gson.fromJson(jsonObject.get("id"), Long.class);
            CollegeDTO collegeDTO = new CollegeDTO(schoolName, studentsSize, id);
            collegeDTOS.add(collegeDTO);
        }
    }
}
