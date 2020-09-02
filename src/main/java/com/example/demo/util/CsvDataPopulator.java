package com.example.demo.util;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class CsvDataPopulator {

    public Map<String,List<Object>> populateCsv(MultipartFile file){
        BufferedReader br;
        String line;
        boolean header = false;
        Map<String,List<Object>> map = new HashMap<>();
        List<String> headerList = new ArrayList<>();
        try {

            InputStream is = file.getInputStream();
            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                String data[] = line.split(",");
                if(!header){
                    for(String col:data){
                        map.put(col,new ArrayList());
                        headerList.add(col);
                    }
                    header=true;
                }else{
                    for(int i=0;i<data.length;i++){
                        map.get(headerList.get(i)).add(data[i]);
                    }
                }
            }

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return map;
    }

    public double getAvgOfCol(Map<String,List<Object>> map,String column){
        List<Object> values = map.get(column);
        double sum = 0;
        try{
            for(Object obj:values){
                long val = Long.parseLong(String.valueOf(obj));
                sum = sum +val;
            }
        }catch(NumberFormatException e){
                throw new RuntimeException(column+":is not eligible for calculating average.");
        }
        double result = (sum/values.size());
        return result;
    }
}
