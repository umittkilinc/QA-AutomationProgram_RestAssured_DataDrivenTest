package TestFramework;

import org.testng.annotations.Test;
import util.DataFromExcel;
import util.ReusableMethods;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class DeleteDog {

    @Test
    public void deleteRequest () throws IOException {

        DataFromExcel d = new DataFromExcel();

        ArrayList data = d.getData("src//main//resources//ExcelFiles//dogoperationsdata.xlsx", "UpdateDogs", "UpdateDog");

        HashMap<String, Object> map = new HashMap<>();

        for (int i=1; i<data.size(); i++){

            if(i % 9 == 1) {

                map.put("id", data.get(i));
                ReusableMethods.sendDeleteDogRequest("https://petstore3.swagger.io/api/v3", "/pet/", Integer.parseInt(map.get("id").toString()));

            }

        }

    }
}
