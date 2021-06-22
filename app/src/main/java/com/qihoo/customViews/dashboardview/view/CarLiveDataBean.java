package com.qihoo.customViews.dashboardview.view;

import com.google.gson.annotations.SerializedName;

/**
 * @AUTHOR 77689
 * @DATE 2021/3/5
 * <p>
 * 车辆实时状态
 */
public class CarLiveDataBean {
    /**
     * 发动机状态（0未知状态，1关闭 ,2 keyon,3 keyoff,4 running）
     */
    @SerializedName("engineStatus")
    String engineStatus;

    public String getFuelLevel() {
        return fuelLevel;
    }

    public String getTrunkDoorStatus() {
        return trunkDoorStatus;
    }

    public String getRrWindowStatus() {
        return rrWindowStatus;
    }

    public String getRoofWindowStatus() {
        return roofWindowStatus;
    }

    public String getLockStatus() {
        return lockStatus;
    }

    public String getLrWindowStatus() {
        return lrWindowStatus;
    }

    public String getLfDoorStatus() {
        return lfDoorStatus;
    }

    public String getAirConditionStatus() {
        return airConditionStatus;
    }

    public String getHandBreakStatus() {
        return handBreakStatus;
    }

    public String getRfDoorStatus() {
        return rfDoorStatus;
    }

    public String getLfWindowStatus() {
        return lfWindowStatus;
    }

    public String getLrDoorStatus() {
        return lrDoorStatus;
    }

    public String getRrDoorStatus() {
        return rrDoorStatus;
    }

    public String getRfWindowStatus() {
        return rfWindowStatus;
    }

    public String getAirDefrostState() {
        return airDefrostState;
    }

    public String getLfPressuer() {
        return lfPressuer;
    }

    public String getRrPressuer() {
        return rrPressuer;
    }

    public String getLrPressuer() {
        return lrPressuer;
    }

    public String getRfPressuer() {
        return rfPressuer;
    }

    public String getAcDriverC() {
        return acDriverC;
    }

    public String getAcPassengerC() {
        return acPassengerC;
    }

    public String getSocMaxValue() {
        return socMaxValue;
    }

    public String getChargeMaxValue() {
        return chargeMaxValue;
    }

    public String getRemainMileage() {
        return remainMileage;
    }

    public String getRemainPower() {
        return remainPower;
    }

    public String getTotalMileage() {
        return totalMileage;
    }

    public String getRemainChargeTime() {
        return remainChargeTime;
    }

    public String getChargeStatus() {
        return chargeStatus;
    }

    public String getStartupStatus() {
        return startupStatus;
    }

    public String getRechargeType() {
        return rechargeType;
    }

    public String getChargeStartTime() {
        return chargeStartTime;
    }

    public String getChargeEndTime() {
        return chargeEndTime;
    }

    public String getMasterHeat() {
        return masterHeat;
    }

    public String getSlaveHeat() {
        return slaveHeat;
    }

    public String getMasterLevel() {
        return masterLevel;
    }

    public String getSlaveLevel() {
        return slaveLevel;
    }

    public String getChargeConnect() {
        return chargeConnect;
    }

    public String getHighBeam() {
        return highBeam;
    }

    public String getLowBeam() {
        return lowBeam;
    }

    public String getOilPercent() {
        return oilPercent;
    }

    public String getFuelComsum() {
        return fuelComsum;
    }

    public String getPowerComsum() {
        return powerComsum;
    }

    public String getThisAvgFuelConsum() {
        return thisAvgFuelConsum;
    }

    public String getThisAvgPowCurrent() {
        return thisAvgPowCurrent;
    }

    public String getVehicleNoUpStatus() {
        return vehicleNoUpStatus;
    }

    /**
     * 剩余油量
     */
    @SerializedName("fuelLevel")
    String fuelLevel;
    /**
     * 后备箱开关状态（0未知状态，1关闭 2开启）
     */
    @SerializedName("trunkDoorStatus")
    String trunkDoorStatus;
    /**
     * 右后窗开关状态（0未知状态，1关闭，2开启）
     */
    @SerializedName("rrWindowStatus")
    String rrWindowStatus;
    /**
     * 天窗开关状态（0未知状态， 1关闭 2开启）
     */
    @SerializedName("roofWindowStatus")
    String roofWindowStatus;
    /**
     * 中控锁状态（0未知状态，1解锁，中控关 2上锁 中控开）
     */
    @SerializedName("lockStatus")
    String lockStatus;
    /**
     * 左后窗开关状态（0未知状态，1关闭 2开启）
     */
    @SerializedName("lrWindowStatus")
    String lrWindowStatus;
    /**
     * 左前门开关状（0未知状态，1关闭 2开启）
     */
    @SerializedName("lfDoorStatus")
    String lfDoorStatus;
    /**
     * 空调开关状态（0未知状态，1关闭 2开启）
     */
    @SerializedName("airConditionStatus")
    String airConditionStatus;
    /**
     * 手刹开关状态（0未知状态 ,4开启，其它值关闭）
     */
    @SerializedName("handBreakStatus")
    String handBreakStatus;
    /**
     * 右前门开关状态（0未知状态，1关闭 2开启）
     */
    @SerializedName("rfDoorStatus")
    String rfDoorStatus;
    /**
     * 左前窗开关状态（0未知状态，1关闭， 2开启）
     */
    @SerializedName("lfWindowStatus")
    String lfWindowStatus;
    /**
     * 左后门开关状态（0未知状态，1关闭 2开启）
     */
    @SerializedName("lrDoorStatus")
    String lrDoorStatus;
    /**
     * 右后门开关状态（0未知状态，1关闭 2开启）
     */
    @SerializedName("rrDoorStatus")
    String rrDoorStatus;
    /**
     * 右前窗开关状态（0未知状态，1关闭，2开启）
     */
    @SerializedName("rfWindowStatus")
    String rfWindowStatus;
    /**
     * 除霜开关状态（0未知状态，1关闭，2开启）
     */
    @SerializedName("airDefrostState")
    String airDefrostState;
    /**
     * 胎压（左前轮）
     */
    @SerializedName("lfPressuer")
    String lfPressuer;
    /**
     * 胎压（右后轮）
     */
    @SerializedName("rrPressuer")
    String rrPressuer;
    /**
     * 胎压（左后轮）
     */
    @SerializedName("lrPressuer")
    String lrPressuer;
    /**
     * 胎压（右前轮）
     */
    @SerializedName("rfPressuer")
    String rfPressuer;
    /**
     * 空调主驾温度（摄氏度）
     */
    @SerializedName("acDriverC")
    String acDriverC;
    /**
     * 空调副驾温度（摄氏度）
     */
    @SerializedName("acPassengerC")
    String acPassengerC;
    /**
     * SOC
     */
    @SerializedName("socMaxValue")
    String socMaxValue;
    /**
     * 电流
     */
    @SerializedName("ChargeMaxValue")
    String chargeMaxValue;
    /**
     * 续航里程KM
     */
    @SerializedName("remainMileage")
    String remainMileage;
    /**
     * 剩余电量  %
     */
    @SerializedName("remainPower")
    String remainPower;
    /**
     * 行驶总里程 KM
     */
    @SerializedName("totalMileage")
    String totalMileage;
    /**
     * 剩余充电时长 HOUR
     */
    @SerializedName("remainChargTime")
    String remainChargeTime;
    /**
     * 充电状态 （0未插入充电枪，1插入充电枪进行初始化，2准备充电，3充电中，4充电完成,5回复故障,6充电故障）
     */
    @SerializedName("chargeStstus")
    String chargeStatus;
    /**
     * 车辆是否启动
     */
    @SerializedName("startupStatus")
    String startupStatus;
    /**
     * 充电模式 0.未知1.立即充电2.预约充电
     */
    @SerializedName("rechargeType")
    String rechargeType;
    /**
     * 预约充电开始时间
     */
    @SerializedName("chargeStartTime")
    String chargeStartTime;
    /**
     * 预约充电结束时间
     */
    @SerializedName("chargeEndTime")
    String chargeEndTime;
    /**
     * 主座椅加熱状态（1关闭，2开启，4无效）
     */
    @SerializedName("masterHeat")
    String masterHeat;
    /**
     * 副座椅加熱状态（1关闭，2开启，4无效）
     */
    @SerializedName("slaveHeat")
    String slaveHeat;
    /**
     * 主座椅加熱等级（1 一级，2二级，3 三级）
     */
    @SerializedName("masterLevel")
    String masterLevel;
    /**
     * 副座椅加熱等级（1 一级，2二级，3 三级）
     */
    @SerializedName("slaveLevel")
    String slaveLevel;
    /**
     * 充电枪开关状态（0未知状态，1关闭，2开启）
     */
    @SerializedName("chargeConnect")
    String chargeConnect;
    /**
     * 远光灯开光状态（0未知状态，1关闭，2开启）
     */
    @SerializedName("highBeam")
    String highBeam;
    /**
     * 近光灯开光状态（0未知状态，1关闭，2开启）
     */
    @SerializedName("lowBeam")
    String lowBeam;
    /**
     * 剩余油量百分unit:%（升）精确小数点后一位数据有效值范围（0.0~1）
     */
    @SerializedName("oilPercent")
    String oilPercent;
    /**
     * 瞬时油耗unit:ml(升) 整数
     */
    @SerializedName("fuelComsum")
    String fuelComsum;
    /**
     * 瞬时电耗unit: kw(千瓦
     */
    @SerializedName("powerComsum")
    String powerComsum;
    /**
     * 本次平均油耗unit: L/100km (升每100公里)
     */
    @SerializedName("thisAvgFuelConsum")
    String thisAvgFuelConsum;
    /**
     * 本次平均电耗unit: kWh/100km (千瓦时每100公里)
     */
    @SerializedName("thisAvgPowCurrent")
    String thisAvgPowCurrent;
    /**
     * 当前远程禁止发动启动指令的开关状态 （0启动1:禁止启动2：未知）
     */
    @SerializedName("vehicleNoUpStatus")
    String vehicleNoUpStatus;
}