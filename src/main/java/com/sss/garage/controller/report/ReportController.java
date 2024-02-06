package com.sss.garage.controller.report;

import com.sss.garage.controller.SssBaseController;
import com.sss.garage.data.report.ReportData;
import com.sss.garage.dto.report.ReportDTO;
import com.sss.garage.facade.report.ReportFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static com.sss.garage.constants.WebConstants.*;

@RestController
@RequestMapping(REPORT_ENDPOINT)
@Tag(name = "Sss Report")
public class ReportController extends SssBaseController {
    private ReportFacade reportFacade;

    @GetMapping("/{id}")
    @Operation(operationId = "getReport", summary = "Get report information")
    @ResponseStatus(HttpStatus.OK)
    public ReportDTO getReport(@PathVariable final Long id) {
        ReportData gameData = reportFacade.getReport(id);

        return mapper.map(gameData, ReportDTO.class);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(operationId = "createReport", summary = "Create new report")
    public void createReport(@RequestBody ReportDTO reportDTO) {
        reportFacade.createReport(mapper.map(reportDTO, ReportData.class));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(operationId = "deleteReport", summary = "Delete report by ID")
    public void deleteReport(@PathVariable final Long id) {
        reportFacade.deleteReport(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(operationId = "getReportsPaginated", summary = "Get list of all reports")
    public Page<ReportDTO> getReportsPaginated(@Parameter(description = "The current result page requested") @RequestParam(value = "currentPage", defaultValue = DEFAULT_CURRENT_PAGE) final int currentPage,
                                                 @Parameter(description = "The number of results returned per page") @RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE) final int pageSize,
                                                 @Parameter(description = "Sorting method applied to the returned results") @RequestParam(value = "sort", defaultValue = "id") final String sort,
                                                 @Parameter(description = "Sorting direction", schema = @Schema(description = "sort", type = "String", allowableValues = "ASC,DESC")) @RequestParam(value = "sortDirection", defaultValue = "ASC") final String sortDirection,
                                                 @Parameter(description = "Optional checked flag to filter by - true returns checked reports, false returns unchecked, null returns all")
                                                      @RequestParam(value = "checked", required = false) final Boolean checked,
                                                 @Parameter(description = "Optional reporting driver ID to filter by") @RequestParam(value = "reportingDriverId", required = false) final String reportingDriverId,
                                                 @Parameter(description = "Optional reported driver ID to filter by") @RequestParam(value = "reportedDriverId", required = false) final String reportedDriverId) {
        Pageable pageable = PageRequest.of(currentPage, pageSize, Sort.by(Sort.Direction.valueOf(sortDirection.toUpperCase()), sort));

        return this.reportFacade.getReportsPaginated(checked, reportingDriverId, reportedDriverId, pageable).map(p -> mapper.map(p, ReportDTO.class));
    }

    @Autowired
    public void setReportFacade(final ReportFacade reportFacade) {
        this.reportFacade = reportFacade;
    }

}
