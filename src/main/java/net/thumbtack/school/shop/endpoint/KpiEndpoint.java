package net.thumbtack.school.shop.endpoint;

import net.thumbtack.school.shop.calculator.KpiCalculator;
import net.thumbtack.school.shop.daoImpl.KpiDaoImpl;
import net.thumbtack.school.shop.dto.response.KpiDto;
import net.thumbtack.school.shop.dto.response.StatusDto;
import net.thumbtack.school.shop.mapper.KpiMapper;
import net.thumbtack.school.shop.service.Validation;
import net.thumbtack.school.shop.service.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/products/{ean}/metrics/kpi")
public class KpiEndpoint {

    private final KpiCalculator kpiCalculator;
    private final KpiDaoImpl kpiDao;
    private final KpiMapper kpiMapper;
    private final Validation validation;

    @Autowired
    public KpiEndpoint(KpiCalculator kpiCalculator, KpiDaoImpl kpiDao, KpiMapper kpiMapper, Validation validation){
        this.kpiCalculator = kpiCalculator;
        this.kpiDao = kpiDao;
        this.kpiMapper = kpiMapper;
        this.validation = validation;
    }


    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public StatusDto calcKpi(@PathVariable("ean") String ean) throws ValidationException {
        validation.validationEan(ean);
        return kpiCalculator.calcKpiByIdRest(ean);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public KpiDto getKpi(@PathVariable("ean") String ean) throws ValidationException {
        validation.validationEan(ean);
        return kpiMapper.kpiResponse(kpiDao.findById(ean));
    }
}
