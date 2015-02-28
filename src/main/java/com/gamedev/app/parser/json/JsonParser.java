package com.gamedev.app.parser.json;

import com.gamedev.app.parser.excel.ExcelParser;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.Field;

/**
 * Created by MainUser on 28/02/2015.
 */
public class JsonParser {
    public GameEvent decodeJsonToEvent(String text){
        GameEvent gameEvent = new GameEvent();
        for(Field property : gameEvent.getClass().getDeclaredFields() ){
            JSONParser parser = new JSONParser();
            KeyFinder finder = new KeyFinder();
            finder.setMatchKey(property.getName());
            try{
                parser.parse(text, finder, true);
                while(!finder.isEnd()){
                    if(finder.isFound()){
                        finder.setFound(false);
                        property.setAccessible(true);
                        property.set(gameEvent,finder.getValue());
                        break;
                    }
                }
                continue;
            }
            catch(ParseException pe){
                pe.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return gameEvent;
    }

    public String encodeJson(GameEvent gameEvent){
        String resultJson = "";
        JSONObject obj=new JSONObject();
        StringWriter out = new StringWriter();
        for(Field property : gameEvent.getClass().getDeclaredFields() ){
            try {
                property.setAccessible(true);
                obj.put(property.getName(), property.get(gameEvent));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        try {
            obj.writeJSONString(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
        resultJson = out.toString();
        return resultJson;
    }

    public static void main (String[] args){
//        String fileContent = Utils.readFile("D:\\test.json");
//        JsonParser jsonParser = new JsonParser();
//        Event event = jsonParser.decodeJsonToEvent(fileContent);
//        String jsonFile = jsonParser.encodeJson(event);
//        Utils.writeFile("D:\\testCopy.json", jsonFile);
//        System.out.println(jsonFile);

        ExcelParser excelParser = new ExcelParser();
        excelParser.parseExcel("C:\\Users\\MainUser\\Desktop\\table.xlsx");
    }
}
