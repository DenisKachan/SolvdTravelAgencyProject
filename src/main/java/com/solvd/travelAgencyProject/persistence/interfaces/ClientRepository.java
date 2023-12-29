package com.solvd.travelAgencyProject.persistence.interfaces;

import com.solvd.travelAgencyProject.domain.Client;
import org.apache.ibatis.annotations.Param;

import java.sql.Connection;
import java.sql.SQLException;

public interface ClientRepository {

    public Connection create(Client client) throws SQLException;

    public void deleteById(int id);

    public void updateById(@Param("client") Client client, @Param("id") int id);

    public Client getById(int id);
}
