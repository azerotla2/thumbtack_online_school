package net.thumbtack.school.shop.mapper;

import net.thumbtack.school.shop.dto.response.KpiDto;
import net.thumbtack.school.shop.model.Kpi;
import org.springframework.stereotype.Component;

@Component
public class KpiMapper {

    public KpiDto kpiResponse(Kpi kpi){
        return new KpiDto(
            kpi.getAvgRating(),
            kpi.getSatisfaction(),
            kpi.getAttraction()
        );
    }
}
