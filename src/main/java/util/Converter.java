package util;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by phongpham on 1/5/16.
 */
public class Converter {

    public static Object convertNoPath(String json, Class clazz) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {
            return mapper.readValue(json, clazz);

        } catch (Exception e) {
            e.printStackTrace();

            throw new RuntimeException("Unable to convert Json [" + json + "] to Class [" + clazz.getName() + "]<br/> Msg: " + e.getMessage()+ "!");
        }
    }

    public static Object convert(String json, Class clazz, String node[]) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {
            JsonNode rootNode = mapper.readValue(json, JsonNode.class);
            JsonNode dataNode = rootNode.path(node[0]);

            for (int x=1; x < node.length; x++) {
                System.out.println("Search for an object using path name: " + node[x]);
                dataNode = dataNode.path(node[x]);
            }

            /** Ensure you have a dataNode. **/
            if (dataNode == null) {
                throw new RuntimeException("Unable to find an object with the path provided!");
            }

            /** Ensure the remainder is an Object. **/
            if (dataNode.isObject()) {
                return mapper.readValue(dataNode.toString(), clazz);
            } else if(dataNode.isArray()){
                List list = new ArrayList();
                for(int i=0; i<dataNode.size(); i++){
                    list.add(mapper.readValue(dataNode.get(i), clazz));
                }
                return list;
            } else {
                System.out.println(dataNode.toString());
                throw new RuntimeException("Data found was not an object suitable for conversion!");
            }

        } catch (Exception e) {
            System.out.println("JsonDataConversionException " + e.getMessage());
            e.printStackTrace();

            throw new RuntimeException("Unable to convert Json [" + json + "] to Class [" + clazz.getName() + "]<br/> Msg: " + e.getMessage()+ "!");
        }
    }
}
