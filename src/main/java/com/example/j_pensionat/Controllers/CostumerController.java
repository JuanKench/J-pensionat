package com.example.j_pensionat.Controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/costumer")
public class CostumerController {

    @PostMapping("/add")
    public String addCostumer() {
        // TODO
        return null;
    }

    @PutMapping("/{Id}")
    public String changeCostumer() {
        // TODO
        return null;
    }

    @DeleteMapping("/{Id}") // endast när kunden inte har bokningar
    public String deleteCostumer() {
        //TODO
        return null;
    }

}
