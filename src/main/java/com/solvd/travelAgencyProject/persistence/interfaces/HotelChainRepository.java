package com.solvd.travelAgencyProject.persistence.interfaces;

import com.solvd.travelAgencyProject.domain.HotelChain;
import org.apache.ibatis.annotations.Param;

import java.sql.Connection;
import java.sql.SQLException;

public interface HotelChainRepository {

    public Connection create(HotelChain hotelChain) throws SQLException;

    public void deleteById(int id);

    public void updateById(@Param("hotelChain") HotelChain hotelChain, @Param("id") int id);

    public HotelChain getById(int id);
}
