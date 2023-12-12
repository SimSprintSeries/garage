package com.sss.garage.controller.pointcategory;

import com.sss.garage.controller.SssBaseController;
import com.sss.garage.data.pointcategory.PointCategoryData;
import com.sss.garage.dto.pointcategory.PointCategoryDTO;
import com.sss.garage.facade.pointcategory.PointCategoryFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.sss.garage.constants.WebConstants.POINT_CATEGORY_ENDPOINT;

@RestController
@RequestMapping(POINT_CATEGORY_ENDPOINT)
@Tag(name = "Sss Point Category")
public class PointCategoryController extends SssBaseController {
    private PointCategoryFacade pointCategoryFacade;

    @GetMapping
    @Operation(operationId = "getAllPointCategories", summary = "Get point categories", description = "Get all point categories")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponse(responseCode = "200", description = "List of PointCategoryDTO", content = @Content(schema = @Schema(implementation = PointCategoryDTO.class)))
    public List<PointCategoryDTO> getAllPointCategories() {
        return pointCategoryFacade.getAllPointCategories().stream().map(p -> mapper.map(p, PointCategoryDTO.class)).toList();
    }

    @GetMapping(path = "/{id}")
    @Operation(operationId = "getPointCategory", summary = "Get info about a specific point category")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = PointCategoryDTO.class)))
    public PointCategoryDTO getPointCategory(@PathVariable final Long id) {
        return mapper.map(pointCategoryFacade.getPointCategory(id), PointCategoryDTO.class);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(operationId = "createPointCategory", summary = "Create new point category")
    public void createPointCategory(@RequestBody final PointCategoryDTO pointCategoryDTO) {
        pointCategoryFacade.createPointCategory(mapper.map(pointCategoryDTO, PointCategoryData.class));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(operationId = "deletePointCategory", summary = "Delete point category by ID")
    public void deletePointCategory(@PathVariable final Long id) {
        pointCategoryFacade.deletePointCategory(id);
    }

    @Autowired
    public void setPointCategoryFacade(PointCategoryFacade pointCategoryFacade) {
        this.pointCategoryFacade = pointCategoryFacade;
    }
}
