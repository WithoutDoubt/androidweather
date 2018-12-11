package com.example.admin.androidweather.gson;

public class ComponentGson {
    /**
     * 工程名称
     * 生产班组
     * 构件编号
     * 检验人员  【上传】
     * 检查项目  【上传】
     * */


    public String getComponentId() {
        return componentId;
    }

    public void setComponentId(String componentId) {
        this.componentId = componentId;
    }

    /**
     * 测试数据：
     *      Uid
     *      code
     *      status
     *      bench_id
     *      line_id
     *      template_id
     *      type_id
     *      create_by
     *      create_date
     * */
    private String componentId;
    private String componentCode;
    private String componentStatus;
    private String componentBenchId;
    private String componentLineId;
    private String componentTypeId;
    private String componentTemplateId;

    private boolean isChoosed;

    public boolean isChoosed() {
        return isChoosed;
    }

    public void setChoosed(boolean choosed) {
        isChoosed = choosed;
    }

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
