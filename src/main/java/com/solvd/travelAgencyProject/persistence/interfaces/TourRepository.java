package com.solvd.travelAgencyProject.persistence.interfaces;

import com.solvd.travelAgencyProject.domain.Tour;
import org.apache.ibatis.annotations.Param;

import java.sql.Connection;
import java.sql.SQLException;

public interface TourRepository {
    public Connection create(Tour tour) throws SQLException;

    public void deleteById(int id);

    public void updateById(@Param("tour") Tour tour, @Param("id") int id);

    public Tour getById(int id);

    public void showToursWithArrangedCost(@Param("costOfTheTour") double costOfTheTour);

    public void showToursWithEstablishedTourType(@Param("nameOfTheTourType") String nameOfTheTourType);
}
