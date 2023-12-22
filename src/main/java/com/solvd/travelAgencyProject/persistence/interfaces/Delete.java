package com.solvd.travelAgencyProject.persistence.interfaces;

import java.sql.SQLException;

public interface Delete {

    void deleteById(int id) throws SQLException;
}
