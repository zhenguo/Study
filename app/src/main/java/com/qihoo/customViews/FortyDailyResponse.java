package com.qihoo.customViews;

import java.util.List;

public class FortyDailyResponse {


    /**
     * code : 200
     * msg : ok
     * data : [{"date":"2020-09-22","timestamp":1600704000000,"dayWeatherCode":3,"nightWeatherCode":14,"dayConditionstext":"多云","nightConditionstext":"阴","maxTemp":26,"minTemp":16,"aqi":65,"wind":"东北风","winp":2,"moonIcon":6,"moonFraction":0.24,"moonrise":"11:32","moonset":"21:26","moonphase":2,"sunrise":"06:02","sunset":"18:12","moon":{"index":0,"desc":"前半夜可见","feel":"微凉","tip":"今晚天气不太好，不适合赏月"},"next_new":25,"next_full":10},{"date":"2020-09-23","timestamp":1600790400000,"dayWeatherCode":17,"nightWeatherCode":18,"dayConditionstext":"小雨","nightConditionstext":"中雨","prep_status":1,"maxTemp":20,"minTemp":16,"temp_status":-1,"aqi":30,"wind":"东风","winp":1,"moonIcon":7,"moonFraction":0.35,"moonrise":"12:42","moonset":"22:13","moonphase":2,"sunrise":"06:03","sunset":"18:10","moon":{"index":0,"desc":"前半夜可见","feel":"微凉","tip":"今晚天气不太好，不适合赏月"},"next_new":24,"next_full":9},{"date":"2020-09-24","timestamp":1600876800000,"dayWeatherCode":3,"nightWeatherCode":3,"dayConditionstext":"多云","nightConditionstext":"多云","prep_status":0,"maxTemp":22,"minTemp":13,"temp_status":0,"aqi":35,"wind":"东风","winp":1,"moonIcon":8,"moonFraction":0.46,"moonrise":"13:47","moonset":"23:05","moonphase":3,"sunrise":"06:04","sunset":"18:09","moon":{"index":2.5,"desc":"前半夜可见","feel":"微凉","tip":"今晚条件一般，不建议赏月"},"next_new":23,"next_full":8},{"date":"2020-09-25","timestamp":1600963200000,"dayWeatherCode":1,"nightWeatherCode":1,"dayConditionstext":"晴","nightConditionstext":"晴","prep_status":0,"maxTemp":23,"minTemp":13,"temp_status":0,"aqi":56,"wind":"东南风","winp":1,"moonIcon":9,"moonFraction":0.56,"moonrise":"14:43","moonset":"+00:03","moonphase":4,"sunrise":"06:05","sunset":"18:07","moon":{"index":3.5,"desc":"前半夜可见","feel":"微凉","tip":"今晚条件尚可，可以赏月"},"next_new":22,"next_full":7},{"date":"2020-09-26","timestamp":1601049600000,"dayWeatherCode":1,"nightWeatherCode":3,"dayConditionstext":"晴","nightConditionstext":"多云","prep_status":0,"maxTemp":25,"minTemp":15,"temp_status":0,"aqi":68,"wind":"南风","winp":2,"moonIcon":10,"moonFraction":0.67,"moonrise":"15:31","moonset":"+01:05","moonphase":4,"sunrise":"06:06","sunset":"18:05","moon":{"index":2.5,"desc":"前半夜可见","feel":"微凉","tip":"今晚条件一般，不建议赏月"},"next_new":21,"next_full":6},{"date":"2020-09-27","timestamp":1601136000000,"dayWeatherCode":3,"nightWeatherCode":3,"dayConditionstext":"多云","nightConditionstext":"多云","prep_status":0,"maxTemp":25,"minTemp":16,"temp_status":0,"aqi":73,"wind":"南风","winp":2,"moonIcon":11,"moonFraction":0.76,"moonrise":"16:12","moonset":"+02:08","moonphase":4,"sunrise":"06:07","sunset":"18:04","moon":{"index":4,"desc":"前半夜可见","feel":"微凉","tip":"今晚条件较好，推荐赏月"},"next_new":20,"next_full":5},{"date":"2020-09-28","timestamp":1601222400000,"dayWeatherCode":14,"nightWeatherCode":17,"dayConditionstext":"阴","nightConditionstext":"小雨","prep_status":1,"maxTemp":26,"minTemp":16,"temp_status":0,"aqi":73,"wind":"南风","winp":2,"moonIcon":12,"moonFraction":0.84,"moonrise":"16:46","moonset":"+03:10","moonphase":4,"sunrise":"06:08","sunset":"18:02","moon":{"index":0,"desc":"通宵可见","feel":"微凉","tip":"今晚天气不太好，不适合赏月"},"next_new":19,"next_full":4},{"date":"2020-09-29","timestamp":1601308800000,"dayWeatherCode":17,"nightWeatherCode":1,"dayConditionstext":"小雨","nightConditionstext":"晴","prep_status":1,"maxTemp":21,"minTemp":14,"temp_status":0,"aqi":52,"wind":"东风","winp":2,"moonIcon":13,"moonFraction":0.9,"moonrise":"17:15","moonset":"+04:11","moonphase":4,"sunrise":"06:09","sunset":"18:00","moon":{"index":6,"desc":"通宵可见","feel":"微凉","tip":"今晚皓月当空，非常适合赏月"},"next_new":18,"next_full":3},{"date":"2020-09-30","timestamp":1601395200000,"dayWeatherCode":1,"nightWeatherCode":1,"dayConditionstext":"晴","nightConditionstext":"晴","prep_status":0,"maxTemp":21,"minTemp":12,"temp_status":0,"aqi":33,"wind":"北风","winp":2,"moonIcon":14,"moonFraction":0.95,"moonrise":"17:41","moonset":"+05:10","moonphase":4,"sunrise":"06:10","sunset":"17:59","moon":{"index":6,"desc":"通宵可见","feel":"凉爽","tip":"今晚皓月当空，非常适合赏月"},"next_new":17,"next_full":2},{"date":"2020-10-01","timestamp":1601481600000,"dayWeatherCode":3,"nightWeatherCode":18,"dayConditionstext":"多云","nightConditionstext":"中雨","prep_status":1,"maxTemp":22,"minTemp":11,"temp_status":0,"aqi":50,"wind":"南风","winp":2,"moonIcon":15,"moonFraction":0.98,"moonrise":"18:05","moonset":"+06:09","moonphase":4,"sunrise":"06:11","sunset":"17:57","moon":{"index":0,"desc":"通宵可见","feel":"凉爽","tip":"今晚天气不太好，不适合赏月"},"next_new":16,"next_full":1},{"date":"2020-10-02","timestamp":1601568000000,"dayWeatherCode":3,"nightWeatherCode":14,"dayConditionstext":"多云","nightConditionstext":"阴","prep_status":0,"maxTemp":21,"minTemp":12,"temp_status":0,"aqi":56,"wind":"东南风","winp":2,"moonIcon":16,"moonFraction":1,"moonrise":"-18:05","moonset":"06:09","moonphase":5,"sunrise":"06:12","sunset":"17:55","moon":{"index":0,"desc":"通宵可见","feel":"凉爽","tip":"今晚天气不太好，不适合赏月"},"next_new":15,"next_full":29},{"date":"2020-10-03","timestamp":1601654400000,"dayWeatherCode":19,"nightWeatherCode":17,"dayConditionstext":"大雨","nightConditionstext":"小雨","prep_status":1,"maxTemp":25,"minTemp":12,"temp_status":0,"aqi":40,"wind":"北风","winp":2,"moonIcon":17,"moonFraction":0.99,"moonrise":"-18:28","moonset":"07:07","moonphase":6,"sunrise":"06:13","sunset":"17:54","moon":{"index":0,"desc":"通宵可见","feel":"凉爽","tip":"今晚天气不太好，不适合赏月"},"next_new":14,"next_full":28},{"date":"2020-10-04","timestamp":1601740800000,"dayWeatherCode":3,"nightWeatherCode":1,"dayConditionstext":"多云","nightConditionstext":"晴","prep_status":0,"maxTemp":25,"minTemp":16,"temp_status":0,"aqi":4,"wind":"北风","winp":4,"moonIcon":18,"moonFraction":0.97,"moonrise":"-18:51","moonset":"08:05","moonphase":6,"sunrise":"06:14","sunset":"17:52","moon":{"index":6,"desc":"通宵可见","feel":"微凉","tip":"今晚皓月当空，非常适合赏月"},"next_new":13,"next_full":27},{"date":"2020-10-05","timestamp":1601827200000,"dayWeatherCode":1,"nightWeatherCode":1,"dayConditionstext":"晴","nightConditionstext":"晴","prep_status":0,"maxTemp":22,"minTemp":13,"temp_status":0,"aqi":9,"wind":"西风","winp":3,"moonIcon":19,"moonFraction":0.93,"moonrise":"-19:16","moonset":"09:04","moonphase":6,"sunrise":"06:15","sunset":"17:51","moon":{"index":6,"desc":"通宵可见","feel":"微凉","tip":"今晚皓月当空，非常适合赏月"},"next_new":12,"next_full":26},{"date":"2020-10-06","timestamp":1601913600000,"dayWeatherCode":1,"nightWeatherCode":1,"dayConditionstext":"晴","nightConditionstext":"晴","prep_status":0,"maxTemp":20,"minTemp":10,"temp_status":0,"aqi":44,"wind":"西南风","winp":2,"moonIcon":20,"moonFraction":0.88,"moonrise":"-19:43","moonset":"10:03","moonphase":6,"sunrise":"06:16","sunset":"17:49","moon":{"index":6,"desc":"通宵可见","feel":"凉爽","tip":"今晚皓月当空，非常适合赏月"},"next_new":11,"next_full":25},{"date":"2020-10-07","timestamp":1602000000000,"dayWeatherCode":1,"nightWeatherCode":1,"dayConditionstext":"晴","nightConditionstext":"晴","prep_status":0,"maxTemp":21,"minTemp":12,"temp_status":0,"aqi":44,"wind":"东南风","winp":2,"moonIcon":21,"moonFraction":0.81,"moonrise":"-20:14","moonset":"11:03","moonphase":6,"sunrise":"06:17","sunset":"17:47","moon":{"index":6,"desc":"通宵可见","feel":"凉爽","tip":"今晚皓月当空，非常适合赏月"},"next_new":10,"next_full":24},{"date":"2020-10-08","timestamp":1602086400000,"dayWeatherCode":1,"nightWeatherCode":1,"dayConditionstext":"晴","nightConditionstext":"晴","prep_status":0,"maxTemp":19,"minTemp":10,"temp_status":0,"aqi":-1,"wind":"东南风","winp":2,"moonIcon":22,"moonFraction":0.73,"moonrise":"-20:50","moonset":"12:04","moonphase":6,"sunrise":"06:19","sunset":"17:46","moon":{"index":4.5,"desc":"后半夜可见","feel":"凉爽","tip":"今晚条件较好，推荐赏月"},"next_new":9,"next_full":23},{"date":"2020-10-09","timestamp":1602172800000,"dayWeatherCode":1,"nightWeatherCode":1,"dayConditionstext":"晴","nightConditionstext":"晴","prep_status":0,"maxTemp":18,"minTemp":8,"temp_status":0,"aqi":-1,"wind":"西南风","winp":2,"moonIcon":23,"moonFraction":0.64,"moonrise":"-21:32","moonset":"13:00","moonphase":6,"sunrise":"06:20","sunset":"17:44","moon":{"index":3,"desc":"后半夜可见","feel":"凉爽","tip":"今晚条件尚可，可以赏月"},"next_new":8,"next_full":22},{"date":"2020-10-10","timestamp":1602259200000,"dayWeatherCode":1,"nightWeatherCode":1,"dayConditionstext":"晴","nightConditionstext":"晴","prep_status":0,"maxTemp":20,"minTemp":11,"temp_status":0,"aqi":-1,"wind":"西风","winp":3,"moonIcon":24,"moonFraction":0.54,"moonrise":"-22:22","moonset":"13:53","moonphase":7,"sunrise":"06:21","sunset":"17:43","moon":{"index":0,"desc":"彻夜不见","feel":"凉爽","tip":"今晚看不到月亮，不适合赏月"},"next_new":7,"next_full":21},{"date":"2020-10-11","timestamp":1602345600000,"dayWeatherCode":3,"nightWeatherCode":1,"dayConditionstext":"多云","nightConditionstext":"晴","prep_status":0,"maxTemp":23,"minTemp":14,"temp_status":0,"aqi":-1,"wind":"北风","winp":4,"moonIcon":25,"moonFraction":0.44,"moonrise":"00:24","moonset":"14:40","moonphase":8,"sunrise":"06:22","sunset":"17:41","moon":{"index":0,"desc":"彻夜不见","feel":"微凉","tip":"今晚看不到月亮，不适合赏月"},"next_new":6,"next_full":20},{"date":"2020-10-12","timestamp":1602432000000,"dayWeatherCode":19,"nightWeatherCode":17,"dayConditionstext":"大雨","nightConditionstext":"小雨","prep_status":1,"maxTemp":23,"minTemp":10,"temp_status":0,"aqi":-1,"wind":"北风","winp":2,"moonIcon":26,"moonFraction":0.33,"moonrise":"01:34","moonset":"15:22","moonphase":8,"sunrise":"06:23","sunset":"17:40","moon":{"index":0,"desc":"彻夜不见","feel":"凉爽","tip":"今晚看不到月亮，不适合赏月"},"next_new":5,"next_full":19},{"date":"2020-10-13","timestamp":1602518400000,"dayWeatherCode":3,"nightWeatherCode":14,"dayConditionstext":"多云","nightConditionstext":"阴","prep_status":0,"maxTemp":19,"minTemp":10,"temp_status":0,"aqi":-1,"wind":"东南风","winp":2,"moonIcon":27,"moonFraction":0.23,"moonrise":"02:47","moonset":"15:59","moonphase":8,"sunrise":"06:24","sunset":"17:38","moon":{"index":0,"desc":"彻夜不见","feel":"凉爽","tip":"今晚看不到月亮，不适合赏月"},"next_new":4,"next_full":18},{"date":"2020-10-14","timestamp":1602604800000,"dayWeatherCode":3,"nightWeatherCode":18,"dayConditionstext":"多云","nightConditionstext":"中雨","prep_status":1,"maxTemp":20,"minTemp":9,"temp_status":0,"aqi":-1,"wind":"南风","winp":2,"moonIcon":28,"moonFraction":0.14,"moonrise":"04:01","moonset":"16:33","moonphase":8,"sunrise":"06:25","sunset":"17:37","moon":{"index":0,"desc":"彻夜不见","feel":"凉爽","tip":"今晚看不到月亮，不适合赏月"},"next_new":3,"next_full":17},{"date":"2020-10-15","timestamp":1602691200000,"dayWeatherCode":1,"nightWeatherCode":1,"dayConditionstext":"晴","nightConditionstext":"晴","prep_status":0,"maxTemp":19,"minTemp":10,"temp_status":0,"aqi":-1,"wind":"北风","winp":2,"moonIcon":29,"moonFraction":0.07,"moonrise":"05:17","moonset":"17:04","moonphase":8,"sunrise":"06:26","sunset":"17:35","moon":{"index":0,"desc":"彻夜不见","feel":"凉爽","tip":"今晚看不到月亮，不适合赏月"},"next_new":2,"next_full":16},{"date":"2020-10-16","timestamp":1602777600000,"dayWeatherCode":17,"nightWeatherCode":1,"dayConditionstext":"小雨","nightConditionstext":"晴","prep_status":1,"maxTemp":19,"minTemp":12,"temp_status":0,"aqi":-1,"wind":"东风","winp":2,"moonIcon":30,"moonFraction":0.02,"moonrise":"06:33","moonset":"17:35","moonphase":8,"sunrise":"06:27","sunset":"17:34","moon":{"index":0,"desc":"彻夜不见","feel":"凉爽","tip":"今晚看不到月亮，不适合赏月"},"next_new":1,"next_full":15},{"date":"2020-10-17","timestamp":1602864000000,"dayWeatherCode":14,"nightWeatherCode":17,"dayConditionstext":"阴","nightConditionstext":"小雨","prep_status":1,"maxTemp":24,"minTemp":14,"temp_status":0,"aqi":-1,"wind":"南风","winp":2,"moonIcon":1,"moonFraction":0,"moonrise":"07:51","moonset":"18:07","moonphase":1,"sunrise":"06:28","sunset":"17:32","moon":{"index":0,"desc":"彻夜不见","feel":"微凉","tip":"今晚看不到月亮，不适合赏月"},"next_new":29,"next_full":14},{"date":"2020-10-18","timestamp":1602950400000,"dayWeatherCode":3,"nightWeatherCode":3,"dayConditionstext":"多云","nightConditionstext":"多云","prep_status":0,"maxTemp":23,"minTemp":14,"temp_status":0,"aqi":-1,"wind":"南风","winp":2,"moonIcon":2,"moonFraction":0.01,"moonrise":"09:09","moonset":"18:41","moonphase":2,"sunrise":"06:29","sunset":"17:31","moon":{"index":0,"desc":"彻夜不见","feel":"微凉","tip":"今晚看不到月亮，不适合赏月"},"next_new":28,"next_full":13},{"date":"2020-10-19","timestamp":1603036800000,"dayWeatherCode":1,"nightWeatherCode":3,"dayConditionstext":"晴","nightConditionstext":"多云","prep_status":0,"maxTemp":23,"minTemp":13,"temp_status":0,"aqi":-1,"wind":"南风","winp":2,"moonIcon":3,"moonFraction":0.05,"moonrise":"10:24","moonset":"19:21","moonphase":2,"sunrise":"06:30","sunset":"17:29","moon":{"index":0,"desc":"彻夜不见","feel":"微凉","tip":"今晚看不到月亮，不适合赏月"},"next_new":27,"next_full":12},{"date":"2020-10-20","timestamp":1603123200000,"dayWeatherCode":1,"nightWeatherCode":1,"dayConditionstext":"晴","nightConditionstext":"晴","prep_status":0,"maxTemp":21,"minTemp":11,"temp_status":0,"aqi":-1,"wind":"东南风","winp":1,"moonIcon":4,"moonFraction":0.12,"moonrise":"11:35","moonset":"20:06","moonphase":2,"sunrise":"06:31","sunset":"17:28","moon":{"index":0,"desc":"前半夜可见","feel":"凉爽","tip":"今晚月亮较小，不适合赏月"},"next_new":26,"next_full":11},{"date":"2020-10-21","timestamp":1603209600000,"dayWeatherCode":3,"nightWeatherCode":3,"dayConditionstext":"多云","nightConditionstext":"多云","prep_status":0,"maxTemp":20,"minTemp":11,"temp_status":0,"aqi":-1,"wind":"东风","winp":1,"moonIcon":5,"moonFraction":0.2,"moonrise":"-11:35","moonset":"20:58","moonphase":2,"sunrise":"06:32","sunset":"17:26","moon":{"index":2.5,"desc":"前半夜可见","feel":"凉爽","tip":"今晚条件一般，不建议赏月"},"next_new":25,"next_full":10},{"date":"2020-10-22","timestamp":1603296000000,"dayWeatherCode":17,"nightWeatherCode":18,"dayConditionstext":"小雨","nightConditionstext":"中雨","prep_status":1,"maxTemp":18,"minTemp":14,"temp_status":0,"aqi":-1,"wind":"东风","winp":1,"moonIcon":6,"moonFraction":0.3,"moonrise":"12:37","moonset":"21:56","moonphase":2,"sunrise":"06:33","sunset":"17:25","moon":{"index":0,"desc":"前半夜可见","feel":"微凉","tip":"今晚天气不太好，不适合赏月"},"next_new":24,"next_full":9},{"date":"2020-10-23","timestamp":1603382400000,"dayWeatherCode":3,"nightWeatherCode":14,"dayConditionstext":"多云","nightConditionstext":"阴","prep_status":0,"maxTemp":24,"minTemp":14,"temp_status":1,"aqi":-1,"wind":"东北风","winp":2,"moonIcon":7,"moonFraction":0.41,"moonrise":"13:29","moonset":"22:58","moonphase":3,"sunrise":"06:34","sunset":"17:24","moon":{"index":0,"desc":"前半夜可见","feel":"微凉","tip":"今晚天气不太好，不适合赏月"},"next_new":23,"next_full":8},{"date":"2020-10-24","timestamp":1603468800000,"dayWeatherCode":1,"nightWeatherCode":1,"dayConditionstext":"晴","nightConditionstext":"晴","prep_status":0,"maxTemp":21,"minTemp":11,"temp_status":0,"aqi":-1,"wind":"东南风","winp":1,"moonIcon":8,"moonFraction":0.51,"moonrise":"14:12","moonset":"+01:04","moonphase":4,"sunrise":"06:36","sunset":"17:22","moon":{"index":3.5,"desc":"前半夜可见","feel":"凉爽","tip":"今晚条件尚可，可以赏月"},"next_new":22,"next_full":7},{"date":"2020-10-25","timestamp":1603555200000,"dayWeatherCode":1,"nightWeatherCode":3,"dayConditionstext":"晴","nightConditionstext":"多云","prep_status":0,"maxTemp":23,"minTemp":13,"temp_status":0,"aqi":-1,"wind":"南风","winp":2,"moonIcon":9,"moonFraction":0.61,"moonrise":"14:48","moonset":"+02:06","moonphase":4,"sunrise":"06:37","sunset":"17:21","moon":{"index":2.5,"desc":"前半夜可见","feel":"微凉","tip":"今晚条件一般，不建议赏月"},"next_new":21,"next_full":6},{"date":"2020-10-26","timestamp":1603641600000,"dayWeatherCode":3,"nightWeatherCode":3,"dayConditionstext":"多云","nightConditionstext":"多云","prep_status":0,"maxTemp":23,"minTemp":14,"temp_status":0,"aqi":-1,"wind":"南风","winp":2,"moonIcon":10,"moonFraction":0.71,"moonrise":"15:19","moonset":"+03:06","moonphase":4,"sunrise":"06:38","sunset":"17:20","moon":{"index":4,"desc":"前半夜可见","feel":"微凉","tip":"今晚条件较好，推荐赏月"},"next_new":20,"next_full":5},{"date":"2020-10-27","timestamp":1603728000000,"dayWeatherCode":14,"nightWeatherCode":17,"dayConditionstext":"阴","nightConditionstext":"小雨","prep_status":1,"maxTemp":24,"minTemp":14,"temp_status":0,"aqi":-1,"wind":"南风","winp":2,"moonIcon":11,"moonFraction":0.79,"moonrise":"15:45","moonset":"+04:04","moonphase":4,"sunrise":"06:39","sunset":"17:18","moon":{"index":0,"desc":"通宵可见","feel":"微凉","tip":"今晚天气不太好，不适合赏月"},"next_new":19,"next_full":4},{"date":"2020-10-28","timestamp":1603814400000,"dayWeatherCode":17,"nightWeatherCode":1,"dayConditionstext":"小雨","nightConditionstext":"晴","prep_status":1,"maxTemp":19,"minTemp":12,"temp_status":0,"aqi":-1,"wind":"东风","winp":2,"moonIcon":12,"moonFraction":0.86,"moonrise":"16:09","moonset":"+05:02","moonphase":4,"sunrise":"06:40","sunset":"17:17","moon":{"index":6,"desc":"通宵可见","feel":"凉爽","tip":"今晚皓月当空，非常适合赏月"},"next_new":18,"next_full":3},{"date":"2020-10-29","timestamp":1603900800000,"dayWeatherCode":1,"nightWeatherCode":1,"dayConditionstext":"晴","nightConditionstext":"晴","prep_status":0,"maxTemp":19,"minTemp":10,"temp_status":0,"aqi":-1,"wind":"北风","winp":2,"moonIcon":13,"moonFraction":0.92,"moonrise":"16:32","moonset":"+06:00","moonphase":4,"sunrise":"06:41","sunset":"17:16","moon":{"index":6,"desc":"通宵可见","feel":"凉爽","tip":"今晚皓月当空，非常适合赏月"},"next_new":17,"next_full":2},{"date":"2020-10-30","timestamp":1603987200000,"dayWeatherCode":3,"nightWeatherCode":18,"dayConditionstext":"多云","nightConditionstext":"中雨","prep_status":1,"maxTemp":20,"minTemp":9,"temp_status":0,"aqi":-1,"wind":"南风","winp":2,"moonIcon":14,"moonFraction":0.96,"moonrise":"16:55","moonset":"+06:59","moonphase":4,"sunrise":"06:42","sunset":"17:15","moon":{"index":0,"desc":"通宵可见","feel":"凉爽","tip":"今晚天气不太好，不适合赏月"},"next_new":16,"next_full":1},{"date":"2020-10-31","timestamp":1604073600000,"dayWeatherCode":3,"nightWeatherCode":14,"dayConditionstext":"多云","nightConditionstext":"阴","prep_status":0,"maxTemp":19,"minTemp":10,"temp_status":0,"aqi":-1,"wind":"东南风","winp":2,"moonIcon":15,"moonFraction":0.99,"moonrise":"-16:55","moonset":"06:59","moonphase":5,"sunrise":"06:43","sunset":"17:13","moon":{"index":0,"desc":"通宵可见","feel":"凉爽","tip":"今晚天气不太好，不适合赏月"},"next_new":15,"next_full":30}]
     */

    private int code;
    private String msg;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * date : 2020-09-22
         * timestamp : 1600704000000
         * dayWeatherCode : 3
         * nightWeatherCode : 14
         * dayConditionstext : 多云
         * nightConditionstext : 阴
         * maxTemp : 26
         * minTemp : 16
         * aqi : 65
         * wind : 东北风
         * winp : 2
         * moonIcon : 6
         * moonFraction : 0.24
         * moonrise : 11:32
         * moonset : 21:26
         * moonphase : 2
         * sunrise : 06:02
         * sunset : 18:12
         * moon : {"index":0,"desc":"前半夜可见","feel":"微凉","tip":"今晚天气不太好，不适合赏月"}
         * next_new : 25
         * next_full : 10
         * prep_status : 1
         * temp_status : -1
         *
         * quality : 1, 空气质量等级
         * lunarYear : 2020, 农历年
         * lunarMonth : 8, 农历月
         * lunarDay : 8, 农历日
         * leap : false, 是否闰月
         */

        private String date;
        private long timestamp;
        private int dayWeatherCode;
        private int nightWeatherCode;
        private String dayConditionstext;
        private String nightConditionstext;
        private int maxTemp;
        private int minTemp;
        private int aqi;
        private String wind;
        private int winp;
        private int moonIcon;
        private double moonFraction;
        private String moonrise;
        private String moonset;
        private int moonphase;
        private String sunrise;
        private String sunset;
        private MoonBean moon;
        private int next_new;
        private int next_full;
        private int prep_status;
        private int temp_status;
        private int quality;
        private int lunarYear;
        private int lunarMonth;
        private int lunarDay;
        private boolean leap;


        /**
         *  是否点击，0表示没有点击 ，1 表示点击 自己添加字段
         */
        private int isClick = 0;

        public int getIsClick() {
            return isClick;
        }

        public void setIsClick(int isClick) {
            this.isClick = isClick;
        }


        /**
         * 0表示非空，1表空
         */
        private int isNull = 0;

        public int getIsNull() {
            return isNull;
        }

        public void setIsNull(int isNull) {
            this.isNull = isNull;
        }


        public int getQuality() {
            return quality;
        }

        public void setQuality(int quality) {
            this.quality = quality;
        }

        public int getLunarYear() {
            return lunarYear;
        }

        public void setLunarYear(int lunarYear) {
            this.lunarYear = lunarYear;
        }

        public int getLunarMonth() {
            return lunarMonth;
        }

        public void setLunarMonth(int lunarMonth) {
            this.lunarMonth = lunarMonth;
        }

        public int getLunarDay() {
            return lunarDay;
        }

        public void setLunarDay(int lunarDay) {
            this.lunarDay = lunarDay;
        }

        public boolean isLeap() {
            return leap;
        }

        public void setLeap(boolean leap) {
            this.leap = leap;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public long getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(long timestamp) {
            this.timestamp = timestamp;
        }

        public int getDayWeatherCode() {
            return dayWeatherCode;
        }

        public void setDayWeatherCode(int dayWeatherCode) {
            this.dayWeatherCode = dayWeatherCode;
        }

        public int getNightWeatherCode() {
            return nightWeatherCode;
        }

        public void setNightWeatherCode(int nightWeatherCode) {
            this.nightWeatherCode = nightWeatherCode;
        }

        public String getDayConditionstext() {
            return dayConditionstext;
        }

        public void setDayConditionstext(String dayConditionstext) {
            this.dayConditionstext = dayConditionstext;
        }

        public String getNightConditionstext() {
            return nightConditionstext;
        }

        public void setNightConditionstext(String nightConditionstext) {
            this.nightConditionstext = nightConditionstext;
        }

        public int getMaxTemp() {
            return maxTemp;
        }

        public void setMaxTemp(int maxTemp) {
            this.maxTemp = maxTemp;
        }

        public int getMinTemp() {
            return minTemp;
        }

        public void setMinTemp(int minTemp) {
            this.minTemp = minTemp;
        }

        public int getAqi() {
            return aqi;
        }

        public void setAqi(int aqi) {
            this.aqi = aqi;
        }

        public String getWind() {
            return wind;
        }

        public void setWind(String wind) {
            this.wind = wind;
        }

        public int getWinp() {
            return winp;
        }

        public void setWinp(int winp) {
            this.winp = winp;
        }

        public int getMoonIcon() {
            return moonIcon;
        }

        public void setMoonIcon(int moonIcon) {
            this.moonIcon = moonIcon;
        }

        public double getMoonFraction() {
            return moonFraction;
        }

        public void setMoonFraction(double moonFraction) {
            this.moonFraction = moonFraction;
        }

        public String getMoonrise() {
            return moonrise;
        }

        public void setMoonrise(String moonrise) {
            this.moonrise = moonrise;
        }

        public String getMoonset() {
            return moonset;
        }

        public void setMoonset(String moonset) {
            this.moonset = moonset;
        }

        public int getMoonphase() {
            return moonphase;
        }

        public void setMoonphase(int moonphase) {
            this.moonphase = moonphase;
        }

        public String getSunrise() {
            return sunrise;
        }

        public void setSunrise(String sunrise) {
            this.sunrise = sunrise;
        }

        public String getSunset() {
            return sunset;
        }

        public void setSunset(String sunset) {
            this.sunset = sunset;
        }

        public MoonBean getMoon() {
            return moon;
        }

        public void setMoon(MoonBean moon) {
            this.moon = moon;
        }

        public int getNext_new() {
            return next_new;
        }

        public void setNext_new(int next_new) {
            this.next_new = next_new;
        }

        public int getNext_full() {
            return next_full;
        }

        public void setNext_full(int next_full) {
            this.next_full = next_full;
        }

        public int getPrep_status() {
            return prep_status;
        }

        public void setPrep_status(int prep_status) {
            this.prep_status = prep_status;
        }

        public int getTemp_status() {
            return temp_status;
        }

        public void setTemp_status(int temp_status) {
            this.temp_status = temp_status;
        }

        public static class MoonBean {
            /**
             * index : 0.0
             * desc : 前半夜可见
             * feel : 微凉
             * tip : 今晚天气不太好，不适合赏月
             */

            private double index;
            private String desc;
            private String feel;
            private String tip;

            public double getIndex() {
                return index;
            }

            public void setIndex(double index) {
                this.index = index;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getFeel() {
                return feel;
            }

            public void setFeel(String feel) {
                this.feel = feel;
            }

            public String getTip() {
                return tip;
            }

            public void setTip(String tip) {
                this.tip = tip;
            }
        }
    }
}
