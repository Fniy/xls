package com.baixuegu.sign.beans;

import java.util.ArrayList;
import java.util.List;

public class A {
    public String shop;
    public List<User> users = new ArrayList<>();

    public static class User {
        public String name;
        public String url;
    }
}