package com.solvd.travelAgencyProject.persistence.interfaces;

import com.solvd.travelAgencyProject.domain.Hotel;
import com.solvd.travelAgencyProject.domain.TravelAgent;
import org.apache.ibatis.annotations.Param;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public interface TravelAgentRepository {

    public Connection create(TravelAgent travelAgent) throws SQLException;

    public void deleteById(int id);

    public void updateById(@Param("travelAgent") TravelAgent travelAgent, @Param("id") int id);

    public TravelAgent getById(int id);
}
