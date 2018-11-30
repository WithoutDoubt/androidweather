package com.example.admin.androidweather.db;
import org.litepal.crud.DataSupport;
public class Component extends DataSupport {
    /**
     * 构件项目名
     * 构件编码
     * 构件状态：
     * 模台ID
     * 生产线
     * Alt+Insert 插入get、set
     */
    private String componentCode;
    private String componentStatus;
    private String componentBenchId;
    private String componentLineId;
    private String componentTypeId;
    private String componentTemplateId;

    public String getComponentCode() {
        return componentCode;
    }

    public void setComponentCode(String componentCode) {
        this.componentCode = componentCode;
    }

    public String getComponentStatus() {
        return componentStatus;
    }

    public void setComponentStatus(String componentStatus) {
        this.componentStatus = componentStatus;
    }

    public String getComponentBenchId() {
        return componentBenchId;
    }

    public void setComponentBenchId(String componentBenchId) {
        this.componentBenchId = componentBenchId;
    }

    public String getComponentLineId() {
        return componentLineId;
    }

    public void setComponentLineId(String componentLineId) {
        this.componentLineId = componentLineId;
    }

    public String getComponentTypeId() {
        return componentTypeId;
    }

    public void setComponentTypeId(String componentTypeId) {
        this.componentTypeId = componentTypeId;
    }

    public String getComponentTemplateId() {
        return componentTemplateId;
    }

    public void setComponentTemplateId(String componentTemplateId) {
        this.componentTemplateId = componentTemplateId;
    }
}
