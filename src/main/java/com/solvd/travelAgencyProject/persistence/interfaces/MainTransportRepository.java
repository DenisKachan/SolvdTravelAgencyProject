package com.solvd.travelAgencyProject.persistence.interfaces;

import com.solvd.travelAgencyProject.domain.MainTransport;
import org.apache.ibatis.annotations.Param;

import java.sql.Connection;
import java.sql.SQLException;

public interface MainTransportRepository {

    public Connection create(MainTransport mainTransport) throws SQLException;

    public void deleteById(int id);

    public void updateById(@Param("mainTransport") MainTransport mainTransport, @Param("id") int id);

    public MainTransport getById(int id);
}
