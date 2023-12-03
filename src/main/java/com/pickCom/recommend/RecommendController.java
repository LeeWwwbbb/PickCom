package com.pickCom.recommend;

import lombok.SneakyThrows;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.io.FileReader;
import java.io.Reader;

@Controller
public class RecommendController {
    @Resource(name = "recommendServiceImp")
    private RecommendService recommendService;

    @RequestMapping(value = "/recommend")
    public ModelAndView recommend() throws Exception{
        ModelAndView mv = new ModelAndView("/recommend/recommend_Usage");
        return mv;
    }

    @SneakyThrows
    @RequestMapping(value = "/recommend_Result", method = RequestMethod.POST)
    public ModelAndView postrecommend(
            @RequestParam(name = "option") String option,
            @RequestParam(name = "priceRange_S", required = false) Integer priceRange_S, // 수정된 부분
            @RequestParam(name = "priceRange_G", required = false) Integer priceRange_G  // 수정된 부분
    ) {
        JSONParser parser = new JSONParser();
        ModelAndView mv = null;

        if (option.equals("S") && priceRange_S != null) {
            String money = Integer.toString(priceRange_S / 10);
            Reader reader = new FileReader("C:/Users/byung/Desktop/JSP Project/PickCom/src/main/resources/python/get_build/Samu/" + option + "_result_" + money + ".json");
            Object jsonData = parser.parse(reader);
            JSONArray jsonArray = (JSONArray) jsonData;

            mv = new ModelAndView("/recommend/recommend_Result_S");
            mv.addObject("jsonObject", jsonArray);
        } else if (option.equals("G") && priceRange_G != null) {
            String money = Integer.toString(priceRange_G / 10);
            Reader reader = new FileReader("C:/Users/byung/Desktop/JSP Project/PickCom/src/main/resources/python/get_build/Game/" + option + "_result_" + money + ".json");
            Object jsonData = parser.parse(reader);
            JSONArray jsonArray = (JSONArray) jsonData;

            mv = new ModelAndView("/recommend/recommend_Result_G");
            mv.addObject("jsonObject", jsonArray);
        }
        return mv;
    }
}
