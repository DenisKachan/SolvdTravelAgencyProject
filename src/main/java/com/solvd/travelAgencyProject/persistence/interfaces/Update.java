package com.solvd.travelAgencyProject.persistence.interfaces;

public interface Update<T> {

    void updateById(T newValue,int id);
}
