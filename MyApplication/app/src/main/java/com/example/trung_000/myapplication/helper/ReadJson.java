package com.example.trung_000.myapplication.helper;

public class ReadJson {
//    public ArrayList<Weather> getlistweather(String hightLighJson) {
//        ArrayList<Weather> listPosts = new ArrayList<Weather>();
//        if (hightLighJson != null) {
//            try {
//                JSONObject data = new JSONObject(hightLighJson);
//                JSONArray info = data.getJSONArray("Data");
//                int leng = info.length();
//                for (int i = 0; i < leng; i++) {
//                    ArrayList<City>city=new ArrayList<City>();
//                    ArrayList<List_Day>listday = new ArrayList<List_Day>();
//
//                    JSONObject channel = info.getJSONObject(i);
//
//                    if (!channel.isNull("city")) {
//                        JSONObject info1 = channel.getJSONObject("city");
//                        int leng1 = info1.length();
//                        if (leng1 > 0) {
//                                String id = "";
//                                String name = "";
//                                String country = "";
//                                JSONObject channel1 = info1.getJSONObject("" + i);
//                                if (!channel1.isNull("id")) {
//                                    id = channel1.getString("id");
//                                }
//                                if (!channel1.isNull("name")) {
//                                    name = channel1.getString("name");
//                                }
//                                if (!channel1.isNull("country")) {
//                                    country = channel1.getString("country");
//                                }
//                                city.add(new City(id, name, country));
//                        }
//                    }
//
//                    if (!channel.isNull("list")) {
//                        JSONArray info1 = channel.getJSONArray("list");
//                        int leng1 = info1.length();
//                        if (leng1 > 0) {
//                            for (int j = 0; j <= leng1; j++) {
//                                ArrayList<Temp> temp = new ArrayList<Temp>();
//                                ArrayList<WeatherDecripstiom> weatherDecripstioms = new ArrayList<WeatherDecripstiom>();
//                                JSONArray channel1 = info1.getJSONArray(j);
//
//
//                                JSONArray info2 = channel.getJSONArray("temp");
//                                int l2 = info2.length();
//                                if (l2 >0){
//                                    String min="";
//                                    String max="";
//
//                                    if (!channel.isNull("min")) {
//                                        min = channel.getString("min");
//                                    }
//                                    if (!channel.isNull("max")) {
//                                        max = channel.getString("max");
//                                    }
//
//                                    temp.add(new Temp(max, min));
//                                }
//
//
//                                String description ="";
//
//                                if (!channel.isNull("description")) {
//                                    description = channel.getString("description");
//                                }
//                                weatherDecripstioms.add(new WeatherDecripstiom(description));
//                            }
//                        }
//                    }
//
//                    listPosts.add(new Weather(city, listday));
//
//                    Log.d("LISTPOST", ""+listPosts);
//
//                }
//
//            } catch (JSONException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//        }
//
//        return listPosts;
//
//    }
}
