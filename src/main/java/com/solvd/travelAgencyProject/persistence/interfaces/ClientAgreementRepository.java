package com.solvd.travelAgencyProject.persistence.interfaces;

import com.solvd.travelAgencyProject.domain.ClientAgreement;
import org.apache.ibatis.annotations.Param;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public interface ClientAgreementRepository {

    public Connection create(ClientAgreement clientAgreement) throws SQLException;

    public void deleteById(int id);

    public void updateById(@Param("clientAgreement") ClientAgreement clientAgreement, @Param("id") int id);

    public ClientAgreement getById(int id);
}
