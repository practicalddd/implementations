package com.practicalddd.cargotracker.shareddomain.model;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Represents an edge in a path through a graph, describing the route of a
 * cargo.
 */
public class TransitEdge implements Serializable {

    private String voyageNumber;
    private String fromUnLocode;
    private String toUnLocode;
    private LocalDate fromDate;
    private LocalDate toDate;

    public TransitEdge() {
        // Nothing to do.
    }

    public TransitEdge(String voyageNumber, String fromUnLocode,
            String toUnLocode, LocalDate fromDate, LocalDate toDate) {
        this.voyageNumber = voyageNumber;
        this.fromUnLocode = fromUnLocode;
        this.toUnLocode = toUnLocode;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public String getVoyageNumber() {
        return voyageNumber;
    }

    public void setVoyageNumber(String voyageNumber) {
        this.voyageNumber = voyageNumber;
    }

    public String getFromUnLocode() {
        return fromUnLocode;
    }

    public void setFromUnLocode(String fromUnLocode) {
        this.fromUnLocode = fromUnLocode;
    }

    public String getToUnLocode() {
        return toUnLocode;
    }

    public void setToUnLocode(String toUnLocode) {
        this.toUnLocode = toUnLocode;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    @Override
    public String toString() {
        return "TransitEdge{" + "voyageNumber=" + voyageNumber
                + ", fromUnLocode=" + fromUnLocode + ", toUnLocode="
                + toUnLocode + ", fromDate=" + fromDate
                + ", toDate=" + toDate + '}';
    }
}