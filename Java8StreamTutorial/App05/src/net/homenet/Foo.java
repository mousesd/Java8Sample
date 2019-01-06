package net.homenet;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"unused", "WeakerAccess"})
public class Foo {
    private String name;
    private List<Bar> bars = new ArrayList<>();

    public Foo(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Bar> getBars() {
        return bars;
    }

    public void setBars(List<Bar> bars) {
        this.bars = bars;
    }

    @Override
    public String toString() {
        return "Foo{" +
            "name='" + name + '\'' +
            ", bars=" + bars +
            '}';
    }
}
