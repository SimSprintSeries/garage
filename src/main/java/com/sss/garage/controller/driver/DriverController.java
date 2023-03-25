package com.sss.garage.controller.driver;

import com.sss.garage.controller.SssBaseController;
import com.sss.garage.dto.driver.SimpleDriverDTO;
import com.sss.garage.facade.driver.DriverFacade;
import com.sss.garage.model.driver.Driver;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.sss.garage.constants.WebConstants.DRIVER_ENDPOINT;

@RestController
@RequestMapping(DRIVER_ENDPOINT)
@Tag(name = "Sss User")
public class DriverController extends SssBaseController {
    private DriverFacade driverFacade;

    @GetMapping
    @Operation(operationId = "getAllDrivers", summary = "Get list of all drivers")
    @ResponseStatus(HttpStatus.OK)
    public List<SimpleDriverDTO> getAllDrivers() {
        return mapAsList(this.driverFacade.getAllDrivers(), SimpleDriverDTO.class);
    }

    @GetMapping("/{id}")
    @Operation(operationId = "getDriver", summary = "Get driver information")
    @ResponseStatus(HttpStatus.OK)
    public SimpleDriverDTO getDriver(@PathVariable final Long id) {
        Driver driver = driverFacade.getDriver(id);

        return mapper.map(driver, SimpleDriverDTO.class);
    }

    @Autowired
    public void setDriverFacade(DriverFacade driverFacade) {
        this.driverFacade = driverFacade;
    }
}
