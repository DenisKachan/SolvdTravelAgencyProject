package com.solvd.travelAgencyProject.persistence.interfaces;

import java.sql.SQLException;

public interface Update<T> {

    void updateById(T newValue, int id) throws SQLException;
}
