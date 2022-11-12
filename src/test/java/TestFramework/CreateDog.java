package TestFramework;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.Test;
import pojo.Category;
import pojo.CreateJson;
import pojo.Tags;
import util.DataFromExcel;
import util.ReusableMethods;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CreateDog {

    @Test
    public void createRequest() throws IOException {

        DataFromExcel d = new DataFromExcel();

        ArrayList data = d.getData("src//main//resources//ExcelFiles//dogoperationsdata.xlsx", "AddDogs", "AddDog");

        HashMap<String, Object> map = new HashMap<>();

        for (int i = 0; i < data.size(); i++) {

            if (i % 9 == 0) {
                i++;
                map.put("id", data.get(i));
            }
            i++;
            map.put("name", data.get(i));
            i++;
            map.put("category_id", data.get(i));
            i++;
            map.put("category_name", data.get(i));
            i++;
            map.put("photoUrls", data.get(i));
            i++;
            map.put("tags_id", data.get(i));
            i++;
            map.put("tags_name", data.get(i));
            i++;
            map.put("status", data.get(i));

            Category category = new Category();
            category.setName(map.get("category_name").toString());
            category.setId(Integer.parseInt(map.get("category_id").toString()));

            Tags tags = new Tags();
            tags.setId(Integer.parseInt(map.get("tags_id").toString()));
            tags.setName(map.get("tags_name").toString());

            List<Tags> tagsList = new ArrayList<>();
            tagsList.add(tags);
            CreateJson createJson = new CreateJson();
            createJson.setCategory(category);
            createJson.setTags(tagsList);

            List<String> PhotoUrls = new ArrayList<>();
            PhotoUrls.add(map.get("photoUrls").toString());

            createJson.setId(Integer.parseInt(map.get("id").toString()));
            createJson.setName(map.get("name").toString());
            createJson.setStatus(map.get("status").toString());
            createJson.setPhotoUrls(PhotoUrls);

            ReusableMethods.sendPostAddBookRequest("https://petstore3.swagger.io/api/v3", createJson, "/pet");

            }

    }

}
