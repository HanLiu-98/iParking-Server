package xyz.hanliu.iparking.domain;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Date;

/**
 * @author HanLiu
 * @create 2020-03-10-16:03
 * @blogip 47.110.70.206
 */

/*
 * 空闲车位实体类
 */
public class ParkingSpace {
    private int id;
    private String area;
    private String positionDetail;

    /*
     * FastJSON的注解，方便Date类型转JSON字符串出错
     */
    @JSONField(format = "yyyy-MM-dd HH:mm")
    private Date startTime;
    @JSONField(format = "yyyy-MM-dd HH:mm")
    private Date endTime;


    private float price;
    private String remark;
    private String imagePath;
    private int status;
    private String ownerMobile;

    /*
     *getter方法
     */
    public int getId() {
        return id;
    }

    public String getArea() {
        return area;
    }

    public String getPositionDetail() {
        return positionDetail;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public float getPrice() {
        return price;
    }

    public String getRemark() {
        return remark;
    }

    public String getImagePath() {
        return imagePath;
    }

    public int getStatus() {
        return status;
    }

    public String getOwnerMobile() {
        return ownerMobile;
    }

    /*
     *setter方法
     */

    public void setId(int id) {
        this.id = id;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setPositionDetail(String positionDetail) {
        this.positionDetail = positionDetail;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setOwnerMobile(String ownerMobile) {
        this.ownerMobile = ownerMobile;
    }

    /*
     *toString方法
     */

    @Override
    public String toString() {
        return "ParkingSpace{" +
                "id=" + id +
                ", area='" + area + '\'' +
                ", positionDetail='" + positionDetail + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", price=" + price +
                ", remark='" + remark + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", status=" + status +
                ", ownerMobile='" + ownerMobile + '\'' +
                '}';
    }
}
