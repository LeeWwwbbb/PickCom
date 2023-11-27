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
    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public ModelAndView postrecommend(@RequestParam(name = "option") String option, @RequestParam(name = "priceRange") int priceRange) {
        JSONParser parser = new JSONParser();
        String money = Integer.toString(priceRange/10);

        Reader reader = null;
        if (option.equals("S")){
            reader = new FileReader("C:/Users/byung/Desktop/JSP Project/PickCom/src/main/resources/python/get_build/Samu/" +option+"_result_"+money+".json");
        }else if (option.equals("G")){
            reader = new FileReader("C:/Users/byung/Desktop/JSP Project/PickCom/src/main/resources/python/get_build/Game/" +option+"_result_"+money+".json");
        }

        Object jsonData = parser.parse(reader);
        JSONArray jsonArray = (JSONArray) jsonData;

        System.out.println(jsonArray);

        ModelAndView mv = new ModelAndView("/recommend/recommend_Result");
        mv.addObject("jsonObject", jsonArray);
        return mv;
    }
}
