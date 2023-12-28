package com.solvd.travelAgencyProject.persistence.interfaces;

import com.solvd.travelAgencyProject.domain.Country;
import org.apache.ibatis.annotations.Param;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public interface CountryRepository {

    public Connection create(Country country) throws SQLException;

    public void deleteById(int id);

    public void updateById(@Param("country") Country country, @Param("id") int id);

    public Country getById(int id);
}
