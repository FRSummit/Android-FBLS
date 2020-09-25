package com.frskynet.femalebodylanguagesecrets;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by F R Summit on 24th September,2020
 * Simplexhub Limited
 * frsummit@simplexhub.com
 */
public class JSONParser {

    public String parseItems(String jsonResponse, String activity) {
        String description = "";
        String column = "";
        try {
            JSONObject jobj = new JSONObject(jsonResponse);
            JSONArray jarray = jobj.getJSONArray("items");
            for (int i = 0; i < jarray.length(); i++) {
                JSONObject jo = jarray.getJSONObject(i);
                switch (activity) {
                    case "Introduction": {
                        column = jo.getString("Introduction");
                        break;
                    }
                    case "Distance": {
                        column = jo.getString("Distance");
                        break;
                    }
                    case "EyesAndFemaleBodyLanguage": {
                        column = jo.getString("EyesAndFemaleBodyLanguage");
                        break;
                    }
                    case "HandTalk": {
                        column = jo.getString("HandTalk");
                        break;
                    }
                    case "Touching": {
                        column = jo.getString("Touching");
                        break;
                    }
                    case "Smile": {
                        column = jo.getString("Smile");
                        break;
                    }
                    case "LegsAndFeet": {
                        column = jo.getString("LegsAndFeet");
                        break;
                    }
                    case "Mirror": {
                        column = jo.getString("Mirror");
                        break;
                    }
                    case "DanceFloor": {
                        column = jo.getString("DanceFloor");
                        break;
                    }
                    case "Signals": {
                        column = jo.getString("Signals");
                        break;
                    }
                    case "Conclusion": {
                        column = jo.getString("Conclusion");
                        break;
                    }
                    default:{
                        break;
                    }
                }
//                String date = jo.getString("Conclusion");

                description += "    " + column + "\n";
            }
//            desc.setText(description);
        } catch (JSONException e) {
            e.printStackTrace();
        }
//        loading.dismiss();
        return description;
    }
}
