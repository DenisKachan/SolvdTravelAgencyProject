package com.solvd.travelAgencyProject.persistence.interfaces;

import com.solvd.travelAgencyProject.domain.TourType;
import org.apache.ibatis.annotations.Param;

import java.sql.Connection;
import java.sql.SQLException;

public interface TourTypeRepository {

    public Connection create(TourType tourType) throws SQLException;

    public void deleteById(int id);

    public void updateById(@Param("tourType") TourType tourType, @Param("id") int id);

    public TourType getById(int id);
}
