package com.workshop.zukerjava.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class ParamHttpServletRequestWrapper extends HttpServletRequestWrapper {
    private final Map<String, String[]> params = new HashMap<>();

    public ParamHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
        this.params.putAll(request.getParameterMap());
    }

    public void addParameter(String name, String value) {
        addParameter(name, new String[]{value});
    }

    public void addParameter(String name, String[] values) {
        params.put(name, values);
    }

    @Override
    public String getParameter(String name) {
        String[] values = params.get(name);
        if (values == null || values.length == 0) {
            return null;
        }
        return values[0];
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        return params;
    }

    @Override
    public Enumeration<String> getParameterNames() {
        return ((Vector<String>) params.keySet()).elements();
    }

    @Override
    public String[] getParameterValues(String name) {
        return params.get(name);
    }
}
