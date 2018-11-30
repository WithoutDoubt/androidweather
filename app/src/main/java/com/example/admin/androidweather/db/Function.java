package com.example.admin.androidweather.db;

import org.litepal.crud.DataSupport;

public class Function extends DataSupport {
    private String functionId;
    private String functionName;

    public String getFunctionId() {
        return functionId;
    }

    public void setFunctionId(String functionId) {
        this.functionId = functionId;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }


}
