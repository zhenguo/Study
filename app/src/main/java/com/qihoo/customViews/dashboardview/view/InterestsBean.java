package com.qihoo.customViews.dashboardview.view;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class InterestsBean {

    @SerializedName("success")
    private Boolean success;
    @SerializedName("code")
    private Integer code;
    @SerializedName("message")
    private String message;
    @SerializedName("value")
    public ValueDTO value;

    public static class ValueDTO {
        @SerializedName("rightPackage")
        public RightPackageDTO rightPackage;
        @SerializedName("list")
        public List<ListDTO> list;

        public static class RightPackageDTO {
            @SerializedName("id")
            public Integer id;
            @SerializedName("packageCode")
            public String packageCode;
            @SerializedName("packageName")
            public String packageName;
            @SerializedName("createTime")
            public Object createTime;
            @SerializedName("updateTime")
            public Object updateTime;
            @SerializedName("createUser")
            public Object createUser;
            @SerializedName("updateUser")
            public Object updateUser;
        }

        public static class ListDTO {
            @SerializedName("id")
            private Integer id;
            @SerializedName("rightTypeCode")
            private String rightTypeCode;
            @SerializedName("rightTypeName")
            public String rightTypeName;
            @SerializedName("rightTypeImage")
            public String rightTypeImage;
            @SerializedName("rights")
            public List<RightsDTO> rights;

            public static class RightsDTO {
                @SerializedName("id")
                private Integer id;
                @SerializedName("rightCode")
                private Object rightCode;
                @SerializedName("rightName")
                public String rightName;
                @SerializedName("rightDesc")
                private String rightDesc;
                @SerializedName("rightDescExd")
                private Object rightDescExd;
                @SerializedName("rightImage")
                public Object rightImage;
            }
        }
    }
}
