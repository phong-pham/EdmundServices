package model;


import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Created by phongpham on 1/4/16.
 */
public class CarStyle {

    @JsonProperty(value = "id")
    protected Long styleId;
    protected String name;
    @JsonProperty(value = "submodel")
    protected CarSubModel subModel;
    protected String trim;

    public Long getStyleId() {
        return styleId;
    }

    public void setStyleId(Long styleId) {
        this.styleId = styleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CarSubModel getSubModel() {
        return subModel;
    }

    public void setSubModel(CarSubModel subModel) {
        this.subModel = subModel;
    }

    public String getTrim() {
        return trim;
    }

    public void setTrim(String trim) {
        this.trim = trim;
    }
}
