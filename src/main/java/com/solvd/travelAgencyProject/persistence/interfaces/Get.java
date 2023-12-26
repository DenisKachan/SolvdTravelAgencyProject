package com.solvd.travelAgencyProject.persistence.interfaces;

import java.sql.SQLException;

public interface Get<T> {

    T getById(int id) throws SQLException;
}
