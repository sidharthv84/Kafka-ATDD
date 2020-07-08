package com.test.kafka.utils;


import java.lang.reflect.Field;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.io.FileReader;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import org.apache.commons.io.Charsets;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import static sun.tools.jstat.Alignment.keySet;
@Component
public class ResourceLoaderHelper {


    private static final String[] resources = new String[]{"atddinput/"};
    private static final String RESOURCE_PATH = "src/atdd/resources/";
    protected HashMap<String, String> request1 = new HashMap<>();
    Map<String, String> requestMessageMap = new HashMap<>();

   // public List<String> getDocumentIds() {return documentIDs;}

    @PostConstruct
    public void init() throws Exception {
        loadPayloadAsMessages();
    }

    public String getrequest1(String requestId){
        return requestMessageMap.get(requestId);
    }

    protected void loadPayloadAsMessages(){
        Map<String, String> requestMessageMap;
        List<String> files;

        try{
            for (String resource : resources ) {
                if (null!= this.getClass().getClassLoader().getResourceAsStream(resource)) {
                        files = IOUtils.readLines(this.getClass().getClassLoader()
                        .getResourceAsStream(resource), Charsets.UTF_8);
                    } else {
                    files = Files.walk(FileSystems.getDefault().getPath(RESOURCE_PATH + resource))
                            .filter(Files:: isRegularFile )
                            .filter(p -> p.toString().endsWith(".json"))
                            .map(path -> path.toFile().getName())
                            .collect(Collectors.toList());
                }
                for (String file: files) {
                    Field field = this.getClass().getDeclaredField(FilenameUtils.removeExtension(file));
                            requestMessageMap = readFile(file, resource);
                    field.set(this, requestMessageMap);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected Map<String, String> readFile(String file, String resource) throws Exception {
        JSONParser parser = new JSONParser();
        ObjectMapper objectMapper = new ObjectMapper();
        JSONObject jsonObject;
        JSONArray jsonArray;
    //    Map<String, String> requestMessageMap = new HashMap<>();
        byte[] encoded;
        try{
            if (null != this.getClass().getClassLoader().getResourceAsStream(resource + file)) {
                encoded = IOUtils.toByteArray(this.getClass().getClassLoader()
                        .getResourceAsStream(resource+file));
                jsonObject = (JSONObject) parser.parse(new String(encoded,"UTF-8"));
              //  String message = jsonObject.toString();
              //  requestMessageMap.put((String) jsonObject.keySet().iterator().next(),message);
            } else {
                jsonObject = (JSONObject) parser.parse(new String(RESOURCE_PATH + resource + file));
            }

           jsonArray = (JSONArray) jsonObject.get(jsonObject.keySet().iterator().next());
            for  (Object reqObj : jsonArray) {
               jsonObject = (JSONObject) reqObj;
            //    jsonObject.get("afprequest");
              //String message = jsonObject.toString();
              String key = (String) jsonObject.keySet().iterator().next();
                System.out.println(key);
                String message = jsonObject.get(key).toString();
                System.out.println(message);
           //     String message = objectMapper.writeValueAsString(object);
           //     requestMessageMap.put((String) jsonObject.keySet().iterator().next(), message)
            //    Object object = objectMapper.convertValue(jsonObject.get(keySet().iterator().next()), Object.class);
               //
                requestMessageMap.put(key,message);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return requestMessageMap;
    }
}
