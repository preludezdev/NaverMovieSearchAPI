package com.example.pen.navermoviesearchapi.VO;

import java.util.List;

public class MovieListVO {
    private String lastBuildDate;
    private String total;
    private String start;
    private String display;
    private List<MovieVO> items;

    public MovieListVO(String lastBuildDate, String total, String start, String display, List<MovieVO> items) {
        this.lastBuildDate = lastBuildDate;
        this.total = total;
        this.start = start;
        this.display = display;
        this.items = items;
    }

    public String getLastBuildDate() {
        return lastBuildDate;
    }

    public void setLastBuildDate(String lastBuildDate) {
        this.lastBuildDate = lastBuildDate;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public List<MovieVO> getItems() {
        return items;
    }

    public void setItems(List<MovieVO> items) {
        this.items = items;
    }
}
