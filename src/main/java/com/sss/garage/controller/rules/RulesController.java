package com.sss.garage.controller.rules;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static com.sss.garage.constants.WebConstants.RULES_ENDPOINT;

@Controller
@RequestMapping(RULES_ENDPOINT)
@Tag(name = "Sss Rules")
public class RulesController {
    @GetMapping("/f1")
    @Operation(operationId = "getF1Rules", summary = "Get rules for F1 events")
    @ResponseStatus(HttpStatus.OK)
    public String getF1Rules() {
        return "f1rules";
    }

    @GetMapping("/ac")
    @Operation(operationId = "getACRules", summary = "Get rules for AC and ACC events")
    @ResponseStatus(HttpStatus.OK)
    public String getACRules() {
        return "accrules";
    }
}
