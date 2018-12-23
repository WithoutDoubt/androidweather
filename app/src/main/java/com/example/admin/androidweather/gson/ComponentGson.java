package com.example.admin.androidweather.gson;

public class ComponentGson {
    /**
     * 工程名称
     * 生产班组
     * 构件编号
     * 检验人员  【上传】
     * 检查项目  【上传】
     * */



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

    private String code;
    private String type;
    private String makeDate;
    private String projectName;
    private String typeName;
    private String printTimes;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMakeDate() {
        return makeDate;
    }

    public void setMakeDate(String makeDate) {
        this.makeDate = makeDate;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getPrintTimes() {
        return printTimes;
    }

    public void setPrintTimes(String printTimes) {
        this.printTimes = printTimes;
    }

    private String componentId;
    private String componentCode;
    private String componentStatus;
    private String componentBenchId;
    private String componentLineId;
    private String componentTypeId;
    private String componentTemplateId;

    private boolean isChoosed;

    private String name;
    private String componentTypeCode;
    private String weight;
    private String dimension;
    private String floor;
    private String blockName;
    private String spell;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComponentTypeCode() {
        return componentTypeCode;
    }

    public void setComponentTypeCode(String componentTypeCode) {
        this.componentTypeCode = componentTypeCode;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getBlockName() {
        return blockName;
    }

    public void setBlockName(String blockName) {
        this.blockName = blockName;
    }

    public String getSpell() {
        return spell;
    }

    public void setSpell(String spell) {
        this.spell = spell;
    }

    public String getComponentId() {
        return componentId;
    }

    public void setComponentId(String componentId) {
        this.componentId = componentId;
    }
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
