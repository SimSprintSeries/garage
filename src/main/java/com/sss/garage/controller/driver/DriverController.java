package com.sss.garage.controller.driver;

import com.sss.garage.controller.SssBaseController;
import com.sss.garage.data.driver.DriverData;
import com.sss.garage.dto.driver.DetailedDriverDTO;
import com.sss.garage.dto.driver.SimpleDriverDTO;
import com.sss.garage.facade.driver.DriverFacade;
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
    public DetailedDriverDTO getDriver(@PathVariable final Long id) {
        DriverData driver = driverFacade.getDriver(id);

        return mapper.map(driver, DetailedDriverDTO.class);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(operationId = "createDriver", summary = "Create new driver")
    public void createDriver(@RequestBody DetailedDriverDTO detailedDriverDTO) {
        driverFacade.createDriver(mapper.map(detailedDriverDTO, DriverData.class));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(operationId = "deleteDriver", summary = "Delete driver by ID")
    public void deleteDriver(@PathVariable final Long id) {
        driverFacade.deleteDriver(id);
    }

    @Autowired
    public void setDriverFacade(DriverFacade driverFacade) {
        this.driverFacade = driverFacade;
    }
}
