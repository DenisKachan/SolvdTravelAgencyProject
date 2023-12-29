package com.solvd.travelAgencyProject.persistence.interfaces;

import com.solvd.travelAgencyProject.domain.Discount;
import org.apache.ibatis.annotations.Param;

import java.sql.Connection;
import java.sql.SQLException;

public interface DiscountRepository {

    public Connection create(Discount discount) throws SQLException;

    public void deleteById(int id);

    public void updateById(@Param("discount") Discount discount, @Param("id") int id);

    public Discount getById(int id);
}
