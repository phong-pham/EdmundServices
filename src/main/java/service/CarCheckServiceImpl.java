package service;

import model.CarCheck;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import util.Converter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by phongpham on 1/4/16.
 */
@Service
public class CarCheckServiceImpl implements CarCheckService {

    @Value("${API_URL}")
    protected String apiURL;

    @Value("${API_KEY}")
    protected String apiKey;

    @Override
    /*
     *
     *   @param year checking year, -1 to show all year
     *   @param make checking make
     *   @param model checking model
     *
     *   @return List<CarCheck>
     *
     *   @throws RuntimeException when response from api is not 200
     *   @throws MalformedURLException
     *   @throws IOException
     *   @throws FileNotFoundException
     *
     */
    public List<CarCheck> doCarCheck(int year, String make, String model)throws MalformedURLException, IOException{


        String url = apiURL;
        url += make + "/";
        url += model + "/";
        if(year > 0){
            url += year + "?fmt=json";  //check car by specific year
        }else{
            url += "years?fmt=json";    //check car for all years
        }
        url += "&api_key=" + apiKey;

        System.out.println("\nSending 'GET' request to URL : " + url);

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        int responseCode = con.getResponseCode();

        System.out.println("Response Code : " + responseCode);

        if(responseCode != 200){
            throw new RuntimeException(con.getResponseMessage());
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println(response.toString());
        List<CarCheck> result = new ArrayList<CarCheck>();
        if(year > 0){
            CarCheck carCheck = (CarCheck) Converter.convertNoPath(response.toString(), CarCheck.class);
            System.out.println(carCheck);
            result.add(carCheck);
        }else{
            List<CarCheck> carChecks = (List<CarCheck>)Converter.convert(response.toString(), CarCheck.class, new String[]{"years"});
            for(CarCheck carCheck : carChecks){
                System.out.println(carCheck);
                System.out.println();
            }
            result.addAll(carChecks);
        }

        return result;
    }
}
