package controller;

import model.CarCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import service.CarCheckService;

import javax.annotation.PostConstruct;
import java.util.Calendar;
import java.util.List;

/**
 * Created by phongpham on 1/4/16.
 */
@Controller
public class CarCheckController {

    @Autowired
    protected CarCheckService carCheckService;

    @RequestMapping(value = {"index", "/"}, method = RequestMethod.GET)
    public String index(ModelMap modelMap){
        System.out.println("request index page");
        Calendar cal = Calendar.getInstance();
        modelMap.addAttribute("startYear", 1900);
        modelMap.addAttribute("endYear", cal.get(Calendar.YEAR));
        return "index";
    }

    @RequestMapping(value = "car-check", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody()
    public ModelMap doCarCheck(@RequestParam Integer year,
                               @RequestParam String make,
                               @RequestParam String model,
                               ModelMap modelMap){
        try{
            List<CarCheck> carChecks = carCheckService.doCarCheck(year, make, model);
            modelMap.addAttribute("data", carChecks);
            modelMap.addAttribute("success", true);
            modelMap.addAttribute("count", carChecks.size());
        }catch (Exception ex){
//            ex.printStackTrace();
            modelMap.addAttribute("success", false);
            modelMap.addAttribute("message", ex.getMessage());
        }

        return modelMap;
    }
}
