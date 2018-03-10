package com.my.course.clock.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
public class QrcordController {
    @RequestMapping(value = "/signrecord/{signruleId}",method = RequestMethod.GET)
    public String qrcord(@PathVariable("signruleId") Integer signruleId,
                         @ModelAttribute Model modelAttribute){
        modelAttribute.addAttribute("signruleId",signruleId);
        return "qrcord";
    }
}
