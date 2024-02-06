package com.sss.garage.controller.times;

import com.sss.garage.model.times.TimesRepository;
import com.sss.garage.model.times.Times;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class TimesController {
    private TimesRepository repository;


    @RequestMapping("/times")
    public String getTimes(Model model) {
        List<Times> times = repository.findAll();
        model.addAttribute("times", times);

        return "times";
    }

    @Autowired
    public void setRepository(TimesRepository repository) {
        this.repository = repository;
    }
}
