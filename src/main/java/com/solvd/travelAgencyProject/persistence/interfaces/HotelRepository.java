package com.solvd.travelAgencyProject.persistence.interfaces;

import com.solvd.travelAgencyProject.domain.Hotel;
import org.apache.ibatis.annotations.Param;

import java.sql.Connection;
import java.sql.SQLException;

public interface HotelRepository {

    public Connection create(Hotel hotel) throws SQLException;

    public void deleteById(int id);

    public void updateById(@Param("hotel") Hotel hotel, @Param("id") int id);

    public Hotel getById(int id);
}
