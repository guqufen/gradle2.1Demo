package net.fnsco.bigdata.service.sys.entity;


public class SequenceDO {

    /**
     * 
     */
    private Integer id;

    /**
     * 
     */
    private String tableName;

    /**
     * 
     */
    private Integer currentValue;

    /**
     * 
     */
    private Integer nextValue;

    /**
     * 
     */
    private Integer step;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Integer getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(Integer currentValue) {
        this.currentValue = currentValue;
    }

    public Integer getNextValue() {
        return nextValue;
    }

    public void setNextValue(Integer nextValue) {
        this.nextValue = nextValue;
    }

    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
    }



    @Override
    public String toString() {
        return "[id="+ id + ", tableName="+ tableName + ", currentValue="+ currentValue + ", nextValue="+ nextValue + ", step="+ step + "]";
    }
}