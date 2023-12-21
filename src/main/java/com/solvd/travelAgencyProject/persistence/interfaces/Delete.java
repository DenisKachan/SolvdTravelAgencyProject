package com.solvd.travelAgencyProject.persistence.interfaces;

@FunctionalInterface
public interface Delete <T>{

    void delete(T value);
}
