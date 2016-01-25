package model;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

/**
 * Created by phongpham on 1/4/16.
 */
public class CarCheck {

    protected Long id;
    protected int year;
    @JsonProperty(value = "styles")
    protected List<CarStyle> carStyles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<CarStyle> getCarStyles() {
        return carStyles;
    }

    public void setCarStyles(List<CarStyle> carStyles) {
        this.carStyles = carStyles;
    }

    public String toString(){
        StringBuffer sb = new StringBuffer();
        sb.append("id: " + this.id + "\n");
        sb.append("year: " + this.year + "\n");
        sb.append("carStyles: \n");
        if(carStyles != null){
            for(CarStyle carStyle : carStyles){
                sb.append("\tId: " + carStyle.getStyleId() + "\n");
                sb.append("\tName: " + carStyle.getName() + "\n");
                sb.append("\tTrim: " + carStyle.getTrim() + "\n");
                if(carStyle.getSubModel() != null){
                    sb.append("\tSub Model:\n");
                    sb.append("\t\tBody:" + carStyle.getSubModel().getBody() + "\n");
                    sb.append("\t\tModel Name:" + carStyle.getSubModel().getModelName() + "\n");
                    sb.append("\t\tNice Name:" + carStyle.getSubModel().getNiceName() + "\n");
                }
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}
