package com.example.pen.navermoviesearchapi.VO;

import java.util.List;

public class MovieListVO {
    private String lastBuildDate;
    private int total;
    private int start;
    private int display;
    private List<MovieVO> items;

    public MovieListVO(String lastBuildDate, int total, int start, int display, List<MovieVO> items) {
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

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getDisplay() {
        return display;
    }

    public void setDisplay(int display) {
        this.display = display;
    }

    public List<MovieVO> getItems() {
        return items;
    }

    public void setItems(List<MovieVO> items) {
        this.items = items;
    }
}
