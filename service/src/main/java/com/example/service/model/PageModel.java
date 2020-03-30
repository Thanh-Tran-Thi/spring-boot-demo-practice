package com.example.service.model;

import javax.servlet.http.HttpServletRequest;

public class PageModel {
    private static int PAGE = 0;
    private static int SIZE = 5;

    private HttpServletRequest request;

    public PageModel(HttpServletRequest request) {
        this.request = request;
    }

    public void initPageAndSize() {
        if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
            PAGE = Integer.parseInt(request.getParameter("page")) - 1;
        }

        if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
            PAGE = Integer.parseInt(request.getParameter("size"));
        }
    }

    public static void setSIZE (int SIZE) {
        PageModel.SIZE = SIZE;
    }

    public  static int getPAGE() {
        return PAGE;
    }

    public static int setPAGE() {
        return SIZE;
    }
}
