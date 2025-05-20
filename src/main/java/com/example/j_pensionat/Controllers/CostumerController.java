package com.example.j_pensionat.Controllers;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CostumerController {

    @RequestMapping("Costumer/add")
    public String addCostumer() {
        // TODO
        return null;
    }

    @RequestMapping("Costumer/change")
    public String changeCostumer() {
        // TODO
        return null;
    }

    @RequestMapping("Costumer/delete") // endast när kunden inte har bokningar
    public String deleteCostumer() {
        //TODO
        return null;
    }

}
