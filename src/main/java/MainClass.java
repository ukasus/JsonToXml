import com.google.gson.Gson;
import javaxmlMap.data;
import javaxmlMap.javatoxml;
import jsonJavaMap.mapClass;
import org.json.JSONObject;
import org.json.XML;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MainClass {
    public static void main(String[] args) throws IOException {

        String jsonFile = System.getProperty("user.dir") + "\\jsonFile.json";
        String xmlFile = System.getProperty("user.dir") + "\\xmlFile.xml";


        //JSON extraction
        String jsonStr = new String(Files.readAllBytes(Paths.get(jsonFile)));
        Gson gson=new Gson();
        mapClass mapped=gson.fromJson(jsonStr,mapClass.class);


        //XML covnversion

        javatoxml xmlobj=new javatoxml();
        xmlobj.RES_Request.Request_Type="UpdateRoomRates";
        data record;

        for(int i=0;i<mapped.rooms.size();i++)
        {
            for(int j=0;j<mapped.rooms.get(i).rateplans.size();j++)
            {
                record=new data();

                record.FromDate=mapped.startDate;
                record.ToDate=mapped.endDate;
                record.Taxinclusive=mapped.taxInclusive;
                record.RateTypeID=mapped.rooms.get(i).rateplans.get(j).rateplanId;
                record.RoomTypeID=mapped.rooms.get(i).roomId;
                record.RoomRate.Base=mapped.rooms.get(i).rateplans.get(j).rate.rackRate;
                xmlobj.RES_Request.RateType.add(record);
            }

        }
         String tt=gson.toJson(xmlobj);

       JSONObject json = new JSONObject(tt);


        try (FileWriter fileWriter = new FileWriter(xmlFile)) {
            fileWriter.write(XML.toString(json));
            System.out.println("Data successfully converted from JSon to Xml and saved in File named xmlFile.xml to the poject root directory.");
       }
    }
}
