package com.qihoo.customViews;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.animation.Animator;
import androidx.core.animation.ObjectAnimator;
import androidx.core.animation.ValueAnimator;

import com.google.gson.Gson;
import com.qihoo.customViews.dashboardview.view.CarLiveDataBean;
import com.qihoo.customViews.dashboardview.view.SeresDashBordView;
import com.qihoo.customViews.dashboardview.view.VerifyCodeDialog;
import com.qihoo.customViews.dashboardview.view.VerifyCodeView;
import com.quxiangtech.myapplication.R;

public class TestDragActivity extends AppCompatActivity {
    private static final String TAG = "TestDragActivity";
    private final static String json = "{\n" +
            "    \"code\": 200,\n" +
            "    \"msg\": \"ok\",\n" +
            "    \"data\": [\n" +
            "        {\n" +
            "            \"date\": \"2021-03-20\",\n" +
            "            \"timestamp\": 1616169600000,\n" +
            "            \"termName\": \"春分\",\n" +
            "            \"dayWeatherCode\": 1,\n" +
            "            \"nightWeatherCode\": 1,\n" +
            "            \"dayConditionstext\": \"晴\",\n" +
            "            \"nightConditionstext\": \"晴\",\n" +
            "            \"maxTemp\": 13,\n" +
            "            \"minTemp\": 3,\n" +
            "            \"quality\": 1,\n" +
            "            \"aqi\": 42,\n" +
            "            \"wind\": \"西北风\",\n" +
            "            \"winp\": 3,\n" +
            "            \"dayCloud\": 0,\n" +
            "            \"lunarYear\": 2021,\n" +
            "            \"lunarMonth\": 2,\n" +
            "            \"lunarDay\": 8,\n" +
            "            \"leap\": false,\n" +
            "            \"cnEra\": \"辛丑年\",\n" +
            "            \"moonIcon\": 8,\n" +
            "            \"moonFraction\": 0.32,\n" +
            "            \"moonrise\": \"09:50\",\n" +
            "            \"moonset\": \"+01:02\",\n" +
            "            \"moonphase\": 2,\n" +
            "            \"festivalOrWork\": \"\",\n" +
            "            \"workOrNot\": \"\",\n" +
            "            \"sunrise\": \"06:17\",\n" +
            "            \"sunset\": \"18:25\",\n" +
            "            \"moon\": {\n" +
            "                \"index\": 3.5,\n" +
            "                \"desc\": \"前半夜可见\",\n" +
            "                \"feel\": \"微寒\",\n" +
            "                \"tip\": \"今晚条件尚可，可以赏月\"\n" +
            "            },\n" +
            "            \"next_new\": 23,\n" +
            "            \"next_full\": 9\n" +
            "        },\n" +
            "        {\n" +
            "            \"date\": \"2021-03-21\",\n" +
            "            \"timestamp\": 1616256000000,\n" +
            "            \"termName\": \"\",\n" +
            "            \"dayWeatherCode\": 1,\n" +
            "            \"nightWeatherCode\": 1,\n" +
            "            \"dayConditionstext\": \"晴\",\n" +
            "            \"nightConditionstext\": \"晴\",\n" +
            "            \"prep_status\": 0,\n" +
            "            \"maxTemp\": 13,\n" +
            "            \"minTemp\": 0,\n" +
            "            \"temp_status\": 0,\n" +
            "            \"quality\": 1,\n" +
            "            \"aqi\": 9,\n" +
            "            \"wind\": \"西北风\",\n" +
            "            \"winp\": 3,\n" +
            "            \"dayCloud\": 0,\n" +
            "            \"lunarYear\": 2021,\n" +
            "            \"lunarMonth\": 2,\n" +
            "            \"lunarDay\": 9,\n" +
            "            \"leap\": false,\n" +
            "            \"cnEra\": \"辛丑年\",\n" +
            "            \"moonIcon\": 9,\n" +
            "            \"moonFraction\": 0.41,\n" +
            "            \"moonrise\": \"10:30\",\n" +
            "            \"moonset\": \"+01:59\",\n" +
            "            \"moonphase\": 3,\n" +
            "            \"festivalOrWork\": \"\",\n" +
            "            \"workOrNot\": \"\",\n" +
            "            \"sunrise\": \"06:15\",\n" +
            "            \"sunset\": \"18:26\",\n" +
            "            \"moon\": {\n" +
            "                \"index\": 3.5,\n" +
            "                \"desc\": \"前半夜可见\",\n" +
            "                \"feel\": \"极寒\",\n" +
            "                \"tip\": \"今晚条件尚可，可以赏月\"\n" +
            "            },\n" +
            "            \"next_new\": 22,\n" +
            "            \"next_full\": 8\n" +
            "        },\n" +
            "        {\n" +
            "            \"date\": \"2021-03-22\",\n" +
            "            \"timestamp\": 1616342400000,\n" +
            "            \"termName\": \"\",\n" +
            "            \"dayWeatherCode\": 1,\n" +
            "            \"nightWeatherCode\": 1,\n" +
            "            \"dayConditionstext\": \"晴\",\n" +
            "            \"nightConditionstext\": \"晴\",\n" +
            "            \"prep_status\": 0,\n" +
            "            \"maxTemp\": 18,\n" +
            "            \"minTemp\": 4,\n" +
            "            \"temp_status\": 0,\n" +
            "            \"quality\": 1,\n" +
            "            \"aqi\": 44,\n" +
            "            \"wind\": \"西南风\",\n" +
            "            \"winp\": 2,\n" +
            "            \"dayCloud\": 0,\n" +
            "            \"lunarYear\": 2021,\n" +
            "            \"lunarMonth\": 2,\n" +
            "            \"lunarDay\": 10,\n" +
            "            \"leap\": false,\n" +
            "            \"cnEra\": \"辛丑年\",\n" +
            "            \"moonIcon\": 10,\n" +
            "            \"moonFraction\": 0.51,\n" +
            "            \"moonrise\": \"11:17\",\n" +
            "            \"moonset\": \"+02:52\",\n" +
            "            \"moonphase\": 4,\n" +
            "            \"festivalOrWork\": \"\",\n" +
            "            \"workOrNot\": \"\",\n" +
            "            \"sunrise\": \"06:14\",\n" +
            "            \"sunset\": \"18:27\",\n" +
            "            \"moon\": {\n" +
            "                \"index\": 4.5,\n" +
            "                \"desc\": \"通宵可见\",\n" +
            "                \"feel\": \"微寒\",\n" +
            "                \"tip\": \"今晚条件较好，推荐赏月\"\n" +
            "            },\n" +
            "            \"next_new\": 21,\n" +
            "            \"next_full\": 7\n" +
            "        },\n" +
            "        {\n" +
            "            \"date\": \"2021-03-23\",\n" +
            "            \"timestamp\": 1616428800000,\n" +
            "            \"termName\": \"\",\n" +
            "            \"dayWeatherCode\": 1,\n" +
            "            \"nightWeatherCode\": 3,\n" +
            "            \"dayConditionstext\": \"晴\",\n" +
            "            \"nightConditionstext\": \"多云\",\n" +
            "            \"prep_status\": 0,\n" +
            "            \"maxTemp\": 20,\n" +
            "            \"minTemp\": 8,\n" +
            "            \"temp_status\": 0,\n" +
            "            \"quality\": 2,\n" +
            "            \"aqi\": 96,\n" +
            "            \"wind\": \"西南风\",\n" +
            "            \"winp\": 1,\n" +
            "            \"dayCloud\": 18,\n" +
            "            \"lunarYear\": 2021,\n" +
            "            \"lunarMonth\": 2,\n" +
            "            \"lunarDay\": 11,\n" +
            "            \"leap\": false,\n" +
            "            \"cnEra\": \"辛丑年\",\n" +
            "            \"moonIcon\": 11,\n" +
            "            \"moonFraction\": 0.6,\n" +
            "            \"moonrise\": \"12:12\",\n" +
            "            \"moonset\": \"+03:40\",\n" +
            "            \"moonphase\": 4,\n" +
            "            \"festivalOrWork\": \"\",\n" +
            "            \"workOrNot\": \"\",\n" +
            "            \"sunrise\": \"06:12\",\n" +
            "            \"sunset\": \"18:28\",\n" +
            "            \"moon\": {\n" +
            "                \"index\": 3.5,\n" +
            "                \"desc\": \"通宵可见\",\n" +
            "                \"feel\": \"凉爽\",\n" +
            "                \"tip\": \"今晚条件尚可，可以赏月\"\n" +
            "            },\n" +
            "            \"next_new\": 20,\n" +
            "            \"next_full\": 6\n" +
            "        },\n" +
            "        {\n" +
            "            \"date\": \"2021-03-24\",\n" +
            "            \"timestamp\": 1616515200000,\n" +
            "            \"termName\": \"\",\n" +
            "            \"dayWeatherCode\": 1,\n" +
            "            \"nightWeatherCode\": 3,\n" +
            "            \"dayConditionstext\": \"晴\",\n" +
            "            \"nightConditionstext\": \"多云\",\n" +
            "            \"prep_status\": 0,\n" +
            "            \"maxTemp\": 20,\n" +
            "            \"minTemp\": 6,\n" +
            "            \"temp_status\": 0,\n" +
            "            \"quality\": 2,\n" +
            "            \"aqi\": 76,\n" +
            "            \"wind\": \"东北风\",\n" +
            "            \"winp\": 2,\n" +
            "            \"dayCloud\": 18,\n" +
            "            \"lunarYear\": 2021,\n" +
            "            \"lunarMonth\": 2,\n" +
            "            \"lunarDay\": 12,\n" +
            "            \"leap\": false,\n" +
            "            \"cnEra\": \"辛丑年\",\n" +
            "            \"moonIcon\": 12,\n" +
            "            \"moonFraction\": 0.7,\n" +
            "            \"moonrise\": \"13:14\",\n" +
            "            \"moonset\": \"+04:22\",\n" +
            "            \"moonphase\": 4,\n" +
            "            \"festivalOrWork\": \"\",\n" +
            "            \"workOrNot\": \"\",\n" +
            "            \"sunrise\": \"06:11\",\n" +
            "            \"sunset\": \"18:29\",\n" +
            "            \"moon\": {\n" +
            "                \"index\": 5.0,\n" +
            "                \"desc\": \"通宵可见\",\n" +
            "                \"feel\": \"微寒\",\n" +
            "                \"tip\": \"今晚皓月当空，非常适合赏月\"\n" +
            "            },\n" +
            "            \"next_new\": 19,\n" +
            "            \"next_full\": 5\n" +
            "        },\n" +
            "        {\n" +
            "            \"date\": \"2021-03-25\",\n" +
            "            \"timestamp\": 1616601600000,\n" +
            "            \"termName\": \"\",\n" +
            "            \"dayWeatherCode\": 3,\n" +
            "            \"nightWeatherCode\": 3,\n" +
            "            \"dayConditionstext\": \"多云\",\n" +
            "            \"nightConditionstext\": \"多云\",\n" +
            "            \"prep_status\": 0,\n" +
            "            \"maxTemp\": 20,\n" +
            "            \"minTemp\": 7,\n" +
            "            \"temp_status\": 0,\n" +
            "            \"quality\": 2,\n" +
            "            \"aqi\": 86,\n" +
            "            \"wind\": \"南风\",\n" +
            "            \"winp\": 2,\n" +
            "            \"dayCloud\": 9,\n" +
            "            \"lunarYear\": 2021,\n" +
            "            \"lunarMonth\": 2,\n" +
            "            \"lunarDay\": 13,\n" +
            "            \"leap\": false,\n" +
            "            \"cnEra\": \"辛丑年\",\n" +
            "            \"moonIcon\": 13,\n" +
            "            \"moonFraction\": 0.79,\n" +
            "            \"moonrise\": \"14:22\",\n" +
            "            \"moonset\": \"+04:59\",\n" +
            "            \"moonphase\": 4,\n" +
            "            \"festivalOrWork\": \"\",\n" +
            "            \"workOrNot\": \"\",\n" +
            "            \"sunrise\": \"06:09\",\n" +
            "            \"sunset\": \"18:30\",\n" +
            "            \"moon\": {\n" +
            "                \"index\": 5.0,\n" +
            "                \"desc\": \"通宵可见\",\n" +
            "                \"feel\": \"微寒\",\n" +
            "                \"tip\": \"今晚皓月当空，非常适合赏月\"\n" +
            "            },\n" +
            "            \"next_new\": 18,\n" +
            "            \"next_full\": 4\n" +
            "        },\n" +
            "        {\n" +
            "            \"date\": \"2021-03-26\",\n" +
            "            \"timestamp\": 1616688000000,\n" +
            "            \"termName\": \"\",\n" +
            "            \"dayWeatherCode\": 17,\n" +
            "            \"nightWeatherCode\": 14,\n" +
            "            \"dayConditionstext\": \"小雨\",\n" +
            "            \"nightConditionstext\": \"阴\",\n" +
            "            \"prep_status\": 1,\n" +
            "            \"maxTemp\": 15,\n" +
            "            \"minTemp\": 6,\n" +
            "            \"temp_status\": 0,\n" +
            "            \"quality\": 3,\n" +
            "            \"aqi\": 107,\n" +
            "            \"wind\": \"东南风\",\n" +
            "            \"winp\": 1,\n" +
            "            \"dayCloud\": 96,\n" +
            "            \"lunarYear\": 2021,\n" +
            "            \"lunarMonth\": 2,\n" +
            "            \"lunarDay\": 14,\n" +
            "            \"leap\": false,\n" +
            "            \"cnEra\": \"辛丑年\",\n" +
            "            \"moonIcon\": 14,\n" +
            "            \"moonFraction\": 0.87,\n" +
            "            \"moonrise\": \"15:33\",\n" +
            "            \"moonset\": \"+05:32\",\n" +
            "            \"moonphase\": 4,\n" +
            "            \"festivalOrWork\": \"\",\n" +
            "            \"workOrNot\": \"\",\n" +
            "            \"sunrise\": \"06:07\",\n" +
            "            \"sunset\": \"18:31\",\n" +
            "            \"moon\": {\n" +
            "                \"index\": 0.0,\n" +
            "                \"desc\": \"通宵可见\",\n" +
            "                \"feel\": \"微寒\",\n" +
            "                \"tip\": \"今晚天气不太好，不适合赏月\"\n" +
            "            },\n" +
            "            \"next_new\": 17,\n" +
            "            \"next_full\": 3\n" +
            "        },\n" +
            "        {\n" +
            "            \"date\": \"2021-03-27\",\n" +
            "            \"timestamp\": 1616774400000,\n" +
            "            \"termName\": \"\",\n" +
            "            \"dayWeatherCode\": 3,\n" +
            "            \"nightWeatherCode\": 3,\n" +
            "            \"dayConditionstext\": \"多云\",\n" +
            "            \"nightConditionstext\": \"多云\",\n" +
            "            \"prep_status\": 0,\n" +
            "            \"maxTemp\": 19,\n" +
            "            \"minTemp\": 9,\n" +
            "            \"temp_status\": 0,\n" +
            "            \"quality\": 3,\n" +
            "            \"aqi\": 108,\n" +
            "            \"wind\": \"南风\",\n" +
            "            \"winp\": 2,\n" +
            "            \"dayCloud\": 57,\n" +
            "            \"lunarYear\": 2021,\n" +
            "            \"lunarMonth\": 2,\n" +
            "            \"lunarDay\": 15,\n" +
            "            \"leap\": false,\n" +
            "            \"cnEra\": \"辛丑年\",\n" +
            "            \"moonIcon\": 15,\n" +
            "            \"moonFraction\": 0.94,\n" +
            "            \"moonrise\": \"16:45\",\n" +
            "            \"moonset\": \"+06:02\",\n" +
            "            \"moonphase\": 4,\n" +
            "            \"festivalOrWork\": \"\",\n" +
            "            \"workOrNot\": \"\",\n" +
            "            \"sunrise\": \"06:06\",\n" +
            "            \"sunset\": \"18:32\",\n" +
            "            \"moon\": {\n" +
            "                \"index\": 5.0,\n" +
            "                \"desc\": \"通宵可见\",\n" +
            "                \"feel\": \"凉爽\",\n" +
            "                \"tip\": \"今晚皓月当空，非常适合赏月\"\n" +
            "            },\n" +
            "            \"next_new\": 16,\n" +
            "            \"next_full\": 2\n" +
            "        },\n" +
            "        {\n" +
            "            \"date\": \"2021-03-28\",\n" +
            "            \"timestamp\": 1616860800000,\n" +
            "            \"termName\": \"\",\n" +
            "            \"dayWeatherCode\": 3,\n" +
            "            \"nightWeatherCode\": 14,\n" +
            "            \"dayConditionstext\": \"多云\",\n" +
            "            \"nightConditionstext\": \"阴\",\n" +
            "            \"prep_status\": 0,\n" +
            "            \"maxTemp\": 18,\n" +
            "            \"minTemp\": 7,\n" +
            "            \"temp_status\": 0,\n" +
            "            \"quality\": 1,\n" +
            "            \"aqi\": 21,\n" +
            "            \"wind\": \"西北风\",\n" +
            "            \"winp\": 3,\n" +
            "            \"dayCloud\": 38,\n" +
            "            \"lunarYear\": 2021,\n" +
            "            \"lunarMonth\": 2,\n" +
            "            \"lunarDay\": 16,\n" +
            "            \"leap\": false,\n" +
            "            \"cnEra\": \"辛丑年\",\n" +
            "            \"moonIcon\": 16,\n" +
            "            \"moonFraction\": 0.98,\n" +
            "            \"moonrise\": \"-16:45\",\n" +
            "            \"moonset\": \"06:02\",\n" +
            "            \"moonphase\": 4,\n" +
            "            \"festivalOrWork\": \"\",\n" +
            "            \"workOrNot\": \"\",\n" +
            "            \"sunrise\": \"06:04\",\n" +
            "            \"sunset\": \"18:33\",\n" +
            "            \"moon\": {\n" +
            "                \"index\": 0.0,\n" +
            "                \"desc\": \"通宵可见\",\n" +
            "                \"feel\": \"微寒\",\n" +
            "                \"tip\": \"今晚天气不太好，不适合赏月\"\n" +
            "            },\n" +
            "            \"next_new\": 15,\n" +
            "            \"next_full\": 1\n" +
            "        },\n" +
            "        {\n" +
            "            \"date\": \"2021-03-29\",\n" +
            "            \"timestamp\": 1616947200000,\n" +
            "            \"termName\": \"\",\n" +
            "            \"dayWeatherCode\": 14,\n" +
            "            \"nightWeatherCode\": 14,\n" +
            "            \"dayConditionstext\": \"阴\",\n" +
            "            \"nightConditionstext\": \"阴\",\n" +
            "            \"prep_status\": 0,\n" +
            "            \"maxTemp\": 17,\n" +
            "            \"minTemp\": 7,\n" +
            "            \"temp_status\": 0,\n" +
            "            \"quality\": 2,\n" +
            "            \"aqi\": 55,\n" +
            "            \"wind\": \"东风\",\n" +
            "            \"winp\": 1,\n" +
            "            \"dayCloud\": 99,\n" +
            "            \"lunarYear\": 2021,\n" +
            "            \"lunarMonth\": 2,\n" +
            "            \"lunarDay\": 17,\n" +
            "            \"leap\": false,\n" +
            "            \"cnEra\": \"辛丑年\",\n" +
            "            \"moonIcon\": 17,\n" +
            "            \"moonFraction\": 1.0,\n" +
            "            \"moonrise\": \"-17:59\",\n" +
            "            \"moonset\": \"06:31\",\n" +
            "            \"moonphase\": 5,\n" +
            "            \"festivalOrWork\": \"\",\n" +
            "            \"workOrNot\": \"\",\n" +
            "            \"sunrise\": \"06:02\",\n" +
            "            \"sunset\": \"18:34\",\n" +
            "            \"moon\": {\n" +
            "                \"index\": 0.0,\n" +
            "                \"desc\": \"通宵可见\",\n" +
            "                \"feel\": \"微寒\",\n" +
            "                \"tip\": \"今晚天气不太好，不适合赏月\"\n" +
            "            },\n" +
            "            \"next_new\": 14,\n" +
            "            \"next_full\": 29\n" +
            "        },\n" +
            "        {\n" +
            "            \"date\": \"2021-03-30\",\n" +
            "            \"timestamp\": 1617033600000,\n" +
            "            \"termName\": \"\",\n" +
            "            \"dayWeatherCode\": 3,\n" +
            "            \"nightWeatherCode\": 9,\n" +
            "            \"dayConditionstext\": \"多云\",\n" +
            "            \"nightConditionstext\": \"霾\",\n" +
            "            \"prep_status\": 0,\n" +
            "            \"maxTemp\": 17,\n" +
            "            \"minTemp\": 9,\n" +
            "            \"temp_status\": 0,\n" +
            "            \"quality\": 2,\n" +
            "            \"aqi\": 90,\n" +
            "            \"wind\": \"东南风\",\n" +
            "            \"winp\": 2,\n" +
            "            \"dayCloud\": 49,\n" +
            "            \"lunarYear\": 2021,\n" +
            "            \"lunarMonth\": 2,\n" +
            "            \"lunarDay\": 18,\n" +
            "            \"leap\": false,\n" +
            "            \"cnEra\": \"辛丑年\",\n" +
            "            \"moonIcon\": 18,\n" +
            "            \"moonFraction\": 0.99,\n" +
            "            \"moonrise\": \"-19:14\",\n" +
            "            \"moonset\": \"07:01\",\n" +
            "            \"moonphase\": 6,\n" +
            "            \"festivalOrWork\": \"\",\n" +
            "            \"workOrNot\": \"\",\n" +
            "            \"sunrise\": \"06:01\",\n" +
            "            \"sunset\": \"18:35\",\n" +
            "            \"moon\": {\n" +
            "                \"index\": 0.0,\n" +
            "                \"desc\": \"通宵可见\",\n" +
            "                \"feel\": \"凉爽\",\n" +
            "                \"tip\": \"今晚天气不太好，不适合赏月\"\n" +
            "            },\n" +
            "            \"next_new\": 13,\n" +
            "            \"next_full\": 28\n" +
            "        },\n" +
            "        {\n" +
            "            \"date\": \"2021-03-31\",\n" +
            "            \"timestamp\": 1617120000000,\n" +
            "            \"termName\": \"\",\n" +
            "            \"dayWeatherCode\": 9,\n" +
            "            \"nightWeatherCode\": 17,\n" +
            "            \"dayConditionstext\": \"霾\",\n" +
            "            \"nightConditionstext\": \"小雨\",\n" +
            "            \"prep_status\": 1,\n" +
            "            \"maxTemp\": 21,\n" +
            "            \"minTemp\": 9,\n" +
            "            \"temp_status\": 0,\n" +
            "            \"quality\": 3,\n" +
            "            \"aqi\": 115,\n" +
            "            \"wind\": \"南风\",\n" +
            "            \"winp\": 2,\n" +
            "            \"dayCloud\": 82,\n" +
            "            \"lunarYear\": 2021,\n" +
            "            \"lunarMonth\": 2,\n" +
            "            \"lunarDay\": 19,\n" +
            "            \"leap\": false,\n" +
            "            \"cnEra\": \"辛丑年\",\n" +
            "            \"moonIcon\": 19,\n" +
            "            \"moonFraction\": 0.95,\n" +
            "            \"moonrise\": \"-20:30\",\n" +
            "            \"moonset\": \"07:32\",\n" +
            "            \"moonphase\": 6,\n" +
            "            \"festivalOrWork\": \"\",\n" +
            "            \"workOrNot\": \"\",\n" +
            "            \"sunrise\": \"05:59\",\n" +
            "            \"sunset\": \"18:36\",\n" +
            "            \"moon\": {\n" +
            "                \"index\": 0.0,\n" +
            "                \"desc\": \"通宵可见\",\n" +
            "                \"feel\": \"凉爽\",\n" +
            "                \"tip\": \"今晚天气不太好，不适合赏月\"\n" +
            "            },\n" +
            "            \"next_new\": 12,\n" +
            "            \"next_full\": 27\n" +
            "        },\n" +
            "        {\n" +
            "            \"date\": \"2021-04-01\",\n" +
            "            \"timestamp\": 1617206400000,\n" +
            "            \"termName\": \"\",\n" +
            "            \"dayWeatherCode\": 17,\n" +
            "            \"nightWeatherCode\": 14,\n" +
            "            \"dayConditionstext\": \"小雨\",\n" +
            "            \"nightConditionstext\": \"阴\",\n" +
            "            \"prep_status\": 1,\n" +
            "            \"maxTemp\": 21,\n" +
            "            \"minTemp\": 11,\n" +
            "            \"temp_status\": 0,\n" +
            "            \"quality\": 2,\n" +
            "            \"aqi\": 67,\n" +
            "            \"wind\": \"东北风\",\n" +
            "            \"winp\": 2,\n" +
            "            \"dayCloud\": 100,\n" +
            "            \"lunarYear\": 2021,\n" +
            "            \"lunarMonth\": 2,\n" +
            "            \"lunarDay\": 20,\n" +
            "            \"leap\": false,\n" +
            "            \"cnEra\": \"辛丑年\",\n" +
            "            \"moonIcon\": 20,\n" +
            "            \"moonFraction\": 0.88,\n" +
            "            \"moonrise\": \"-21:47\",\n" +
            "            \"moonset\": \"08:08\",\n" +
            "            \"moonphase\": 6,\n" +
            "            \"festivalOrWork\": \"\",\n" +
            "            \"workOrNot\": \"\",\n" +
            "            \"sunrise\": \"05:58\",\n" +
            "            \"sunset\": \"18:37\",\n" +
            "            \"moon\": {\n" +
            "                \"index\": 0.0,\n" +
            "                \"desc\": \"彻夜不见\",\n" +
            "                \"feel\": \"凉爽\",\n" +
            "                \"tip\": \"今晚看不到月亮，不适合赏月\"\n" +
            "            },\n" +
            "            \"next_new\": 11,\n" +
            "            \"next_full\": 26\n" +
            "        },\n" +
            "        {\n" +
            "            \"date\": \"2021-04-02\",\n" +
            "            \"timestamp\": 1617292800000,\n" +
            "            \"termName\": \"\",\n" +
            "            \"dayWeatherCode\": 14,\n" +
            "            \"nightWeatherCode\": 3,\n" +
            "            \"dayConditionstext\": \"阴\",\n" +
            "            \"nightConditionstext\": \"多云\",\n" +
            "            \"prep_status\": 0,\n" +
            "            \"maxTemp\": 21,\n" +
            "            \"minTemp\": 11,\n" +
            "            \"temp_status\": 0,\n" +
            "            \"quality\": 1,\n" +
            "            \"aqi\": 33,\n" +
            "            \"wind\": \"东北风\",\n" +
            "            \"winp\": 2,\n" +
            "            \"dayCloud\": 89,\n" +
            "            \"lunarYear\": 2021,\n" +
            "            \"lunarMonth\": 2,\n" +
            "            \"lunarDay\": 21,\n" +
            "            \"leap\": false,\n" +
            "            \"cnEra\": \"辛丑年\",\n" +
            "            \"moonIcon\": 21,\n" +
            "            \"moonFraction\": 0.8,\n" +
            "            \"moonrise\": \"-23:04\",\n" +
            "            \"moonset\": \"08:49\",\n" +
            "            \"moonphase\": 6,\n" +
            "            \"festivalOrWork\": \"\",\n" +
            "            \"workOrNot\": \"\",\n" +
            "            \"sunrise\": \"05:56\",\n" +
            "            \"sunset\": \"18:38\",\n" +
            "            \"moon\": {\n" +
            "                \"index\": 0.0,\n" +
            "                \"desc\": \"彻夜不见\",\n" +
            "                \"feel\": \"凉爽\",\n" +
            "                \"tip\": \"今晚看不到月亮，不适合赏月\"\n" +
            "            },\n" +
            "            \"next_new\": 10,\n" +
            "            \"next_full\": 25\n" +
            "        },\n" +
            "        {\n" +
            "            \"date\": \"2021-04-03\",\n" +
            "            \"timestamp\": 1617379200000,\n" +
            "            \"termName\": \"\",\n" +
            "            \"dayWeatherCode\": 1,\n" +
            "            \"nightWeatherCode\": 1,\n" +
            "            \"dayConditionstext\": \"晴\",\n" +
            "            \"nightConditionstext\": \"晴\",\n" +
            "            \"prep_status\": 0,\n" +
            "            \"maxTemp\": 21,\n" +
            "            \"minTemp\": 11,\n" +
            "            \"temp_status\": 0,\n" +
            "            \"quality\": 2,\n" +
            "            \"aqi\": 70,\n" +
            "            \"wind\": \"东南风\",\n" +
            "            \"winp\": 2,\n" +
            "            \"dayCloud\": 5,\n" +
            "            \"lunarYear\": 2021,\n" +
            "            \"lunarMonth\": 2,\n" +
            "            \"lunarDay\": 22,\n" +
            "            \"leap\": false,\n" +
            "            \"cnEra\": \"辛丑年\",\n" +
            "            \"moonIcon\": 22,\n" +
            "            \"moonFraction\": 0.69,\n" +
            "            \"moonrise\": \"00:17\",\n" +
            "            \"moonset\": \"09:38\",\n" +
            "            \"moonphase\": 6,\n" +
            "            \"festivalOrWork\": \"\",\n" +
            "            \"workOrNot\": \"休\",\n" +
            "            \"sunrise\": \"05:54\",\n" +
            "            \"sunset\": \"18:39\",\n" +
            "            \"moon\": {\n" +
            "                \"index\": 0.0,\n" +
            "                \"desc\": \"彻夜不见\",\n" +
            "                \"feel\": \"凉爽\",\n" +
            "                \"tip\": \"今晚看不到月亮，不适合赏月\"\n" +
            "            },\n" +
            "            \"next_new\": 9,\n" +
            "            \"next_full\": 24\n" +
            "        },\n" +
            "        {\n" +
            "            \"date\": \"2021-04-04\",\n" +
            "            \"timestamp\": 1617465600000,\n" +
            "            \"termName\": \"清明\",\n" +
            "            \"dayWeatherCode\": 1,\n" +
            "            \"nightWeatherCode\": 3,\n" +
            "            \"dayConditionstext\": \"晴\",\n" +
            "            \"nightConditionstext\": \"多云\",\n" +
            "            \"prep_status\": 0,\n" +
            "            \"maxTemp\": 21,\n" +
            "            \"minTemp\": 11,\n" +
            "            \"temp_status\": 0,\n" +
            "            \"quality\": 2,\n" +
            "            \"aqi\": 88,\n" +
            "            \"wind\": \"南风\",\n" +
            "            \"winp\": 3,\n" +
            "            \"dayCloud\": 22,\n" +
            "            \"lunarYear\": 2021,\n" +
            "            \"lunarMonth\": 2,\n" +
            "            \"lunarDay\": 23,\n" +
            "            \"leap\": false,\n" +
            "            \"cnEra\": \"辛丑年\",\n" +
            "            \"moonIcon\": 23,\n" +
            "            \"moonFraction\": 0.58,\n" +
            "            \"moonrise\": \"01:24\",\n" +
            "            \"moonset\": \"10:34\",\n" +
            "            \"moonphase\": 7,\n" +
            "            \"festivalOrWork\": \"清明\",\n" +
            "            \"workOrNot\": \"休\",\n" +
            "            \"sunrise\": \"05:53\",\n" +
            "            \"sunset\": \"18:40\",\n" +
            "            \"moon\": {\n" +
            "                \"index\": 0.0,\n" +
            "                \"desc\": \"彻夜不见\",\n" +
            "                \"feel\": \"凉爽\",\n" +
            "                \"tip\": \"今晚看不到月亮，不适合赏月\"\n" +
            "            },\n" +
            "            \"next_new\": 8,\n" +
            "            \"next_full\": 23\n" +
            "        },\n" +
            "        {\n" +
            "            \"date\": \"2021-04-05\",\n" +
            "            \"timestamp\": 1617552000000,\n" +
            "            \"termName\": \"\",\n" +
            "            \"dayWeatherCode\": 1,\n" +
            "            \"nightWeatherCode\": 3,\n" +
            "            \"dayConditionstext\": \"晴\",\n" +
            "            \"nightConditionstext\": \"多云\",\n" +
            "            \"prep_status\": 0,\n" +
            "            \"maxTemp\": 22,\n" +
            "            \"minTemp\": 12,\n" +
            "            \"temp_status\": 0,\n" +
            "            \"aqi\": -1,\n" +
            "            \"wind\": \"南风\",\n" +
            "            \"winp\": 3,\n" +
            "            \"dayCloud\": 22,\n" +
            "            \"lunarYear\": 2021,\n" +
            "            \"lunarMonth\": 2,\n" +
            "            \"lunarDay\": 24,\n" +
            "            \"leap\": false,\n" +
            "            \"cnEra\": \"辛丑年\",\n" +
            "            \"moonIcon\": 24,\n" +
            "            \"moonFraction\": 0.47,\n" +
            "            \"moonrise\": \"03:09\",\n" +
            "            \"moonset\": \"+12:44\",\n" +
            "            \"moonphase\": 8,\n" +
            "            \"festivalOrWork\": \"清明\",\n" +
            "            \"workOrNot\": \"休\",\n" +
            "            \"sunrise\": \"05:51\",\n" +
            "            \"sunset\": \"18:42\",\n" +
            "            \"moon\": {\n" +
            "                \"index\": 0.0,\n" +
            "                \"desc\": \"彻夜不见\",\n" +
            "                \"feel\": \"凉爽\",\n" +
            "                \"tip\": \"今晚看不到月亮，不适合赏月\"\n" +
            "            },\n" +
            "            \"next_new\": 7,\n" +
            "            \"next_full\": 22\n" +
            "        },\n" +
            "        {\n" +
            "            \"date\": \"2021-04-06\",\n" +
            "            \"timestamp\": 1617638400000,\n" +
            "            \"termName\": \"\",\n" +
            "            \"dayWeatherCode\": 1,\n" +
            "            \"nightWeatherCode\": 1,\n" +
            "            \"dayConditionstext\": \"晴\",\n" +
            "            \"nightConditionstext\": \"晴\",\n" +
            "            \"prep_status\": 0,\n" +
            "            \"maxTemp\": 22,\n" +
            "            \"minTemp\": 12,\n" +
            "            \"temp_status\": 0,\n" +
            "            \"aqi\": -1,\n" +
            "            \"wind\": \"东南风\",\n" +
            "            \"winp\": 2,\n" +
            "            \"dayCloud\": 5,\n" +
            "            \"lunarYear\": 2021,\n" +
            "            \"lunarMonth\": 2,\n" +
            "            \"lunarDay\": 25,\n" +
            "            \"leap\": false,\n" +
            "            \"cnEra\": \"辛丑年\",\n" +
            "            \"moonIcon\": 25,\n" +
            "            \"moonFraction\": 0.37,\n" +
            "            \"moonrise\": \"03:48\",\n" +
            "            \"moonset\": \"12:44\",\n" +
            "            \"moonphase\": 8,\n" +
            "            \"festivalOrWork\": \"\",\n" +
            "            \"workOrNot\": \"\",\n" +
            "            \"sunrise\": \"05:49\",\n" +
            "            \"sunset\": \"18:43\",\n" +
            "            \"moon\": {\n" +
            "                \"index\": 0.0,\n" +
            "                \"desc\": \"彻夜不见\",\n" +
            "                \"feel\": \"凉爽\",\n" +
            "                \"tip\": \"今晚看不到月亮，不适合赏月\"\n" +
            "            },\n" +
            "            \"next_new\": 6,\n" +
            "            \"next_full\": 21\n" +
            "        },\n" +
            "        {\n" +
            "            \"date\": \"2021-04-07\",\n" +
            "            \"timestamp\": 1617724800000,\n" +
            "            \"termName\": \"\",\n" +
            "            \"dayWeatherCode\": 14,\n" +
            "            \"nightWeatherCode\": 3,\n" +
            "            \"dayConditionstext\": \"阴\",\n" +
            "            \"nightConditionstext\": \"多云\",\n" +
            "            \"prep_status\": 0,\n" +
            "            \"maxTemp\": 22,\n" +
            "            \"minTemp\": 12,\n" +
            "            \"temp_status\": 0,\n" +
            "            \"aqi\": -1,\n" +
            "            \"wind\": \"东北风\",\n" +
            "            \"winp\": 2,\n" +
            "            \"dayCloud\": 89,\n" +
            "            \"lunarYear\": 2021,\n" +
            "            \"lunarMonth\": 2,\n" +
            "            \"lunarDay\": 26,\n" +
            "            \"leap\": false,\n" +
            "            \"cnEra\": \"辛丑年\",\n" +
            "            \"moonIcon\": 26,\n" +
            "            \"moonFraction\": 0.27,\n" +
            "            \"moonrise\": \"04:21\",\n" +
            "            \"moonset\": \"13:50\",\n" +
            "            \"moonphase\": 8,\n" +
            "            \"festivalOrWork\": \"\",\n" +
            "            \"workOrNot\": \"\",\n" +
            "            \"sunrise\": \"05:48\",\n" +
            "            \"sunset\": \"18:44\",\n" +
            "            \"moon\": {\n" +
            "                \"index\": 0.0,\n" +
            "                \"desc\": \"彻夜不见\",\n" +
            "                \"feel\": \"凉爽\",\n" +
            "                \"tip\": \"今晚看不到月亮，不适合赏月\"\n" +
            "            },\n" +
            "            \"next_new\": 5,\n" +
            "            \"next_full\": 20\n" +
            "        },\n" +
            "        {\n" +
            "            \"date\": \"2021-04-08\",\n" +
            "            \"timestamp\": 1617811200000,\n" +
            "            \"termName\": \"\",\n" +
            "            \"dayWeatherCode\": 17,\n" +
            "            \"nightWeatherCode\": 14,\n" +
            "            \"dayConditionstext\": \"小雨\",\n" +
            "            \"nightConditionstext\": \"阴\",\n" +
            "            \"prep_status\": 1,\n" +
            "            \"maxTemp\": 22,\n" +
            "            \"minTemp\": 12,\n" +
            "            \"temp_status\": 0,\n" +
            "            \"aqi\": -1,\n" +
            "            \"wind\": \"东北风\",\n" +
            "            \"winp\": 2,\n" +
            "            \"dayCloud\": 100,\n" +
            "            \"lunarYear\": 2021,\n" +
            "            \"lunarMonth\": 2,\n" +
            "            \"lunarDay\": 27,\n" +
            "            \"leap\": false,\n" +
            "            \"cnEra\": \"辛丑年\",\n" +
            "            \"moonIcon\": 27,\n" +
            "            \"moonFraction\": 0.18,\n" +
            "            \"moonrise\": \"04:49\",\n" +
            "            \"moonset\": \"14:54\",\n" +
            "            \"moonphase\": 8,\n" +
            "            \"festivalOrWork\": \"\",\n" +
            "            \"workOrNot\": \"\",\n" +
            "            \"sunrise\": \"05:46\",\n" +
            "            \"sunset\": \"18:45\",\n" +
            "            \"moon\": {\n" +
            "                \"index\": 0.0,\n" +
            "                \"desc\": \"彻夜不见\",\n" +
            "                \"feel\": \"凉爽\",\n" +
            "                \"tip\": \"今晚看不到月亮，不适合赏月\"\n" +
            "            },\n" +
            "            \"next_new\": 4,\n" +
            "            \"next_full\": 19\n" +
            "        },\n" +
            "        {\n" +
            "            \"date\": \"2021-04-09\",\n" +
            "            \"timestamp\": 1617897600000,\n" +
            "            \"termName\": \"\",\n" +
            "            \"dayWeatherCode\": 9,\n" +
            "            \"nightWeatherCode\": 17,\n" +
            "            \"dayConditionstext\": \"霾\",\n" +
            "            \"nightConditionstext\": \"小雨\",\n" +
            "            \"prep_status\": 1,\n" +
            "            \"maxTemp\": 22,\n" +
            "            \"minTemp\": 10,\n" +
            "            \"temp_status\": 0,\n" +
            "            \"aqi\": -1,\n" +
            "            \"wind\": \"南风\",\n" +
            "            \"winp\": 2,\n" +
            "            \"dayCloud\": 82,\n" +
            "            \"lunarYear\": 2021,\n" +
            "            \"lunarMonth\": 2,\n" +
            "            \"lunarDay\": 28,\n" +
            "            \"leap\": false,\n" +
            "            \"cnEra\": \"辛丑年\",\n" +
            "            \"moonIcon\": 28,\n" +
            "            \"moonFraction\": 0.11,\n" +
            "            \"moonrise\": \"05:14\",\n" +
            "            \"moonset\": \"15:57\",\n" +
            "            \"moonphase\": 8,\n" +
            "            \"festivalOrWork\": \"\",\n" +
            "            \"workOrNot\": \"\",\n" +
            "            \"sunrise\": \"05:45\",\n" +
            "            \"sunset\": \"18:46\",\n" +
            "            \"moon\": {\n" +
            "                \"index\": 0.0,\n" +
            "                \"desc\": \"彻夜不见\",\n" +
            "                \"feel\": \"凉爽\",\n" +
            "                \"tip\": \"今晚看不到月亮，不适合赏月\"\n" +
            "            },\n" +
            "            \"next_new\": 3,\n" +
            "            \"next_full\": 18\n" +
            "        },\n" +
            "        {\n" +
            "            \"date\": \"2021-04-10\",\n" +
            "            \"timestamp\": 1617984000000,\n" +
            "            \"termName\": \"\",\n" +
            "            \"dayWeatherCode\": 3,\n" +
            "            \"nightWeatherCode\": 9,\n" +
            "            \"dayConditionstext\": \"多云\",\n" +
            "            \"nightConditionstext\": \"霾\",\n" +
            "            \"prep_status\": 0,\n" +
            "            \"maxTemp\": 18,\n" +
            "            \"minTemp\": 10,\n" +
            "            \"temp_status\": 0,\n" +
            "            \"aqi\": -1,\n" +
            "            \"wind\": \"东南风\",\n" +
            "            \"winp\": 2,\n" +
            "            \"dayCloud\": 49,\n" +
            "            \"lunarYear\": 2021,\n" +
            "            \"lunarMonth\": 2,\n" +
            "            \"lunarDay\": 29,\n" +
            "            \"leap\": false,\n" +
            "            \"cnEra\": \"辛丑年\",\n" +
            "            \"moonIcon\": 29,\n" +
            "            \"moonFraction\": 0.06,\n" +
            "            \"moonrise\": \"05:37\",\n" +
            "            \"moonset\": \"16:58\",\n" +
            "            \"moonphase\": 8,\n" +
            "            \"festivalOrWork\": \"\",\n" +
            "            \"workOrNot\": \"\",\n" +
            "            \"sunrise\": \"05:43\",\n" +
            "            \"sunset\": \"18:47\",\n" +
            "            \"moon\": {\n" +
            "                \"index\": 0.0,\n" +
            "                \"desc\": \"彻夜不见\",\n" +
            "                \"feel\": \"凉爽\",\n" +
            "                \"tip\": \"今晚看不到月亮，不适合赏月\"\n" +
            "            },\n" +
            "            \"next_new\": 2,\n" +
            "            \"next_full\": 17\n" +
            "        },\n" +
            "        {\n" +
            "            \"date\": \"2021-04-11\",\n" +
            "            \"timestamp\": 1618070400000,\n" +
            "            \"termName\": \"\",\n" +
            "            \"dayWeatherCode\": 14,\n" +
            "            \"nightWeatherCode\": 14,\n" +
            "            \"dayConditionstext\": \"阴\",\n" +
            "            \"nightConditionstext\": \"阴\",\n" +
            "            \"prep_status\": 0,\n" +
            "            \"maxTemp\": 18,\n" +
            "            \"minTemp\": 8,\n" +
            "            \"temp_status\": 0,\n" +
            "            \"aqi\": -1,\n" +
            "            \"wind\": \"东风\",\n" +
            "            \"winp\": 1,\n" +
            "            \"dayCloud\": 99,\n" +
            "            \"lunarYear\": 2021,\n" +
            "            \"lunarMonth\": 2,\n" +
            "            \"lunarDay\": 30,\n" +
            "            \"leap\": false,\n" +
            "            \"cnEra\": \"辛丑年\",\n" +
            "            \"moonIcon\": 30,\n" +
            "            \"moonFraction\": 0.02,\n" +
            "            \"moonrise\": \"06:00\",\n" +
            "            \"moonset\": \"17:58\",\n" +
            "            \"moonphase\": 8,\n" +
            "            \"festivalOrWork\": \"\",\n" +
            "            \"workOrNot\": \"\",\n" +
            "            \"sunrise\": \"05:42\",\n" +
            "            \"sunset\": \"18:49\",\n" +
            "            \"moon\": {\n" +
            "                \"index\": 0.0,\n" +
            "                \"desc\": \"彻夜不见\",\n" +
            "                \"feel\": \"凉爽\",\n" +
            "                \"tip\": \"今晚看不到月亮，不适合赏月\"\n" +
            "            },\n" +
            "            \"next_new\": 1,\n" +
            "            \"next_full\": 16\n" +
            "        },\n" +
            "        {\n" +
            "            \"date\": \"2021-04-12\",\n" +
            "            \"timestamp\": 1618156800000,\n" +
            "            \"termName\": \"\",\n" +
            "            \"dayWeatherCode\": 3,\n" +
            "            \"nightWeatherCode\": 14,\n" +
            "            \"dayConditionstext\": \"多云\",\n" +
            "            \"nightConditionstext\": \"阴\",\n" +
            "            \"prep_status\": 0,\n" +
            "            \"maxTemp\": 19,\n" +
            "            \"minTemp\": 8,\n" +
            "            \"temp_status\": 0,\n" +
            "            \"aqi\": -1,\n" +
            "            \"wind\": \"西北风\",\n" +
            "            \"winp\": 3,\n" +
            "            \"dayCloud\": 38,\n" +
            "            \"lunarYear\": 2021,\n" +
            "            \"lunarMonth\": 3,\n" +
            "            \"lunarDay\": 1,\n" +
            "            \"leap\": false,\n" +
            "            \"cnEra\": \"辛丑年\",\n" +
            "            \"moonIcon\": 1,\n" +
            "            \"moonFraction\": 0.0,\n" +
            "            \"moonrise\": \"06:23\",\n" +
            "            \"moonset\": \"18:58\",\n" +
            "            \"moonphase\": 1,\n" +
            "            \"festivalOrWork\": \"\",\n" +
            "            \"workOrNot\": \"\",\n" +
            "            \"sunrise\": \"05:40\",\n" +
            "            \"sunset\": \"18:50\",\n" +
            "            \"moon\": {\n" +
            "                \"index\": 0.0,\n" +
            "                \"desc\": \"彻夜不见\",\n" +
            "                \"feel\": \"凉爽\",\n" +
            "                \"tip\": \"今晚看不到月亮，不适合赏月\"\n" +
            "            },\n" +
            "            \"next_new\": 30,\n" +
            "            \"next_full\": 15\n" +
            "        },\n" +
            "        {\n" +
            "            \"date\": \"2021-04-13\",\n" +
            "            \"timestamp\": 1618243200000,\n" +
            "            \"termName\": \"\",\n" +
            "            \"dayWeatherCode\": 3,\n" +
            "            \"nightWeatherCode\": 3,\n" +
            "            \"dayConditionstext\": \"多云\",\n" +
            "            \"nightConditionstext\": \"多云\",\n" +
            "            \"prep_status\": 0,\n" +
            "            \"maxTemp\": 20,\n" +
            "            \"minTemp\": 10,\n" +
            "            \"temp_status\": 0,\n" +
            "            \"aqi\": -1,\n" +
            "            \"wind\": \"南风\",\n" +
            "            \"winp\": 2,\n" +
            "            \"dayCloud\": 57,\n" +
            "            \"lunarYear\": 2021,\n" +
            "            \"lunarMonth\": 3,\n" +
            "            \"lunarDay\": 2,\n" +
            "            \"leap\": false,\n" +
            "            \"cnEra\": \"辛丑年\",\n" +
            "            \"moonIcon\": 2,\n" +
            "            \"moonFraction\": 0.0,\n" +
            "            \"moonrise\": \"06:48\",\n" +
            "            \"moonset\": \"19:57\",\n" +
            "            \"moonphase\": 2,\n" +
            "            \"festivalOrWork\": \"\",\n" +
            "            \"workOrNot\": \"\",\n" +
            "            \"sunrise\": \"05:39\",\n" +
            "            \"sunset\": \"18:51\",\n" +
            "            \"moon\": {\n" +
            "                \"index\": 0.0,\n" +
            "                \"desc\": \"彻夜不见\",\n" +
            "                \"feel\": \"凉爽\",\n" +
            "                \"tip\": \"今晚看不到月亮，不适合赏月\"\n" +
            "            },\n" +
            "            \"next_new\": 29,\n" +
            "            \"next_full\": 14\n" +
            "        },\n" +
            "        {\n" +
            "            \"date\": \"2021-04-14\",\n" +
            "            \"timestamp\": 1618329600000,\n" +
            "            \"termName\": \"\",\n" +
            "            \"dayWeatherCode\": 17,\n" +
            "            \"nightWeatherCode\": 14,\n" +
            "            \"dayConditionstext\": \"小雨\",\n" +
            "            \"nightConditionstext\": \"阴\",\n" +
            "            \"prep_status\": 1,\n" +
            "            \"maxTemp\": 16,\n" +
            "            \"minTemp\": 7,\n" +
            "            \"temp_status\": 0,\n" +
            "            \"aqi\": -1,\n" +
            "            \"wind\": \"东南风\",\n" +
            "            \"winp\": 1,\n" +
            "            \"dayCloud\": 96,\n" +
            "            \"lunarYear\": 2021,\n" +
            "            \"lunarMonth\": 3,\n" +
            "            \"lunarDay\": 3,\n" +
            "            \"leap\": false,\n" +
            "            \"cnEra\": \"辛丑年\",\n" +
            "            \"moonIcon\": 3,\n" +
            "            \"moonFraction\": 0.02,\n" +
            "            \"moonrise\": \"07:16\",\n" +
            "            \"moonset\": \"20:57\",\n" +
            "            \"moonphase\": 2,\n" +
            "            \"festivalOrWork\": \"\",\n" +
            "            \"workOrNot\": \"\",\n" +
            "            \"sunrise\": \"05:37\",\n" +
            "            \"sunset\": \"18:52\",\n" +
            "            \"moon\": {\n" +
            "                \"index\": 0.0,\n" +
            "                \"desc\": \"前半夜可见\",\n" +
            "                \"feel\": \"微寒\",\n" +
            "                \"tip\": \"今晚月亮较小，不适合赏月\"\n" +
            "            },\n" +
            "            \"next_new\": 28,\n" +
            "            \"next_full\": 13\n" +
            "        },\n" +
            "        {\n" +
            "            \"date\": \"2021-04-15\",\n" +
            "            \"timestamp\": 1618416000000,\n" +
            "            \"termName\": \"\",\n" +
            "            \"dayWeatherCode\": 3,\n" +
            "            \"nightWeatherCode\": 3,\n" +
            "            \"dayConditionstext\": \"多云\",\n" +
            "            \"nightConditionstext\": \"多云\",\n" +
            "            \"prep_status\": 0,\n" +
            "            \"maxTemp\": 21,\n" +
            "            \"minTemp\": 8,\n" +
            "            \"temp_status\": 0,\n" +
            "            \"aqi\": -1,\n" +
            "            \"wind\": \"南风\",\n" +
            "            \"winp\": 2,\n" +
            "            \"dayCloud\": 9,\n" +
            "            \"lunarYear\": 2021,\n" +
            "            \"lunarMonth\": 3,\n" +
            "            \"lunarDay\": 4,\n" +
            "            \"leap\": false,\n" +
            "            \"cnEra\": \"辛丑年\",\n" +
            "            \"moonIcon\": 4,\n" +
            "            \"moonFraction\": 0.06,\n" +
            "            \"moonrise\": \"07:48\",\n" +
            "            \"moonset\": \"21:57\",\n" +
            "            \"moonphase\": 2,\n" +
            "            \"festivalOrWork\": \"\",\n" +
            "            \"workOrNot\": \"\",\n" +
            "            \"sunrise\": \"05:36\",\n" +
            "            \"sunset\": \"18:53\",\n" +
            "            \"moon\": {\n" +
            "                \"index\": 0.0,\n" +
            "                \"desc\": \"前半夜可见\",\n" +
            "                \"feel\": \"凉爽\",\n" +
            "                \"tip\": \"今晚月亮较小，不适合赏月\"\n" +
            "            },\n" +
            "            \"next_new\": 27,\n" +
            "            \"next_full\": 12\n" +
            "        },\n" +
            "        {\n" +
            "            \"date\": \"2021-04-16\",\n" +
            "            \"timestamp\": 1618502400000,\n" +
            "            \"termName\": \"\",\n" +
            "            \"dayWeatherCode\": 1,\n" +
            "            \"nightWeatherCode\": 3,\n" +
            "            \"dayConditionstext\": \"晴\",\n" +
            "            \"nightConditionstext\": \"多云\",\n" +
            "            \"prep_status\": 0,\n" +
            "            \"maxTemp\": 21,\n" +
            "            \"minTemp\": 7,\n" +
            "            \"temp_status\": 0,\n" +
            "            \"aqi\": -1,\n" +
            "            \"wind\": \"东北风\",\n" +
            "            \"winp\": 2,\n" +
            "            \"dayCloud\": 18,\n" +
            "            \"lunarYear\": 2021,\n" +
            "            \"lunarMonth\": 3,\n" +
            "            \"lunarDay\": 5,\n" +
            "            \"leap\": false,\n" +
            "            \"cnEra\": \"辛丑年\",\n" +
            "            \"moonIcon\": 5,\n" +
            "            \"moonFraction\": 0.11,\n" +
            "            \"moonrise\": \"08:25\",\n" +
            "            \"moonset\": \"22:56\",\n" +
            "            \"moonphase\": 2,\n" +
            "            \"festivalOrWork\": \"\",\n" +
            "            \"workOrNot\": \"\",\n" +
            "            \"sunrise\": \"05:34\",\n" +
            "            \"sunset\": \"18:54\",\n" +
            "            \"moon\": {\n" +
            "                \"index\": 0.0,\n" +
            "                \"desc\": \"前半夜可见\",\n" +
            "                \"feel\": \"微寒\",\n" +
            "                \"tip\": \"今晚月亮较小，不适合赏月\"\n" +
            "            },\n" +
            "            \"next_new\": 26,\n" +
            "            \"next_full\": 11\n" +
            "        },\n" +
            "        {\n" +
            "            \"date\": \"2021-04-17\",\n" +
            "            \"timestamp\": 1618588800000,\n" +
            "            \"termName\": \"\",\n" +
            "            \"dayWeatherCode\": 1,\n" +
            "            \"nightWeatherCode\": 3,\n" +
            "            \"dayConditionstext\": \"晴\",\n" +
            "            \"nightConditionstext\": \"多云\",\n" +
            "            \"prep_status\": 0,\n" +
            "            \"maxTemp\": 21,\n" +
            "            \"minTemp\": 9,\n" +
            "            \"temp_status\": 0,\n" +
            "            \"aqi\": -1,\n" +
            "            \"wind\": \"西南风\",\n" +
            "            \"winp\": 1,\n" +
            "            \"dayCloud\": 18,\n" +
            "            \"lunarYear\": 2021,\n" +
            "            \"lunarMonth\": 3,\n" +
            "            \"lunarDay\": 6,\n" +
            "            \"leap\": false,\n" +
            "            \"cnEra\": \"辛丑年\",\n" +
            "            \"moonIcon\": 6,\n" +
            "            \"moonFraction\": 0.18,\n" +
            "            \"moonrise\": \"09:09\",\n" +
            "            \"moonset\": \"23:53\",\n" +
            "            \"moonphase\": 2,\n" +
            "            \"festivalOrWork\": \"\",\n" +
            "            \"workOrNot\": \"\",\n" +
            "            \"sunrise\": \"05:33\",\n" +
            "            \"sunset\": \"18:55\",\n" +
            "            \"moon\": {\n" +
            "                \"index\": 2.5,\n" +
            "                \"desc\": \"前半夜可见\",\n" +
            "                \"feel\": \"凉爽\",\n" +
            "                \"tip\": \"今晚条件一般，不建议赏月\"\n" +
            "            },\n" +
            "            \"next_new\": 25,\n" +
            "            \"next_full\": 10\n" +
            "        },\n" +
            "        {\n" +
            "            \"date\": \"2021-04-18\",\n" +
            "            \"timestamp\": 1618675200000,\n" +
            "            \"termName\": \"\",\n" +
            "            \"dayWeatherCode\": 1,\n" +
            "            \"nightWeatherCode\": 1,\n" +
            "            \"dayConditionstext\": \"晴\",\n" +
            "            \"nightConditionstext\": \"晴\",\n" +
            "            \"prep_status\": 0,\n" +
            "            \"maxTemp\": 19,\n" +
            "            \"minTemp\": 5,\n" +
            "            \"temp_status\": 0,\n" +
            "            \"aqi\": -1,\n" +
            "            \"wind\": \"西南风\",\n" +
            "            \"winp\": 2,\n" +
            "            \"dayCloud\": 0,\n" +
            "            \"lunarYear\": 2021,\n" +
            "            \"lunarMonth\": 3,\n" +
            "            \"lunarDay\": 7,\n" +
            "            \"leap\": false,\n" +
            "            \"cnEra\": \"辛丑年\",\n" +
            "            \"moonIcon\": 7,\n" +
            "            \"moonFraction\": 0.25,\n" +
            "            \"moonrise\": \"10:00\",\n" +
            "            \"moonset\": \"+01:36\",\n" +
            "            \"moonphase\": 2,\n" +
            "            \"festivalOrWork\": \"\",\n" +
            "            \"workOrNot\": \"\",\n" +
            "            \"sunrise\": \"05:31\",\n" +
            "            \"sunset\": \"18:56\",\n" +
            "            \"moon\": {\n" +
            "                \"index\": 3.5,\n" +
            "                \"desc\": \"前半夜可见\",\n" +
            "                \"feel\": \"微寒\",\n" +
            "                \"tip\": \"今晚条件尚可，可以赏月\"\n" +
            "            },\n" +
            "            \"next_new\": 24,\n" +
            "            \"next_full\": 9\n" +
            "        },\n" +
            "        {\n" +
            "            \"date\": \"2021-04-19\",\n" +
            "            \"timestamp\": 1618761600000,\n" +
            "            \"termName\": \"\",\n" +
            "            \"dayWeatherCode\": 1,\n" +
            "            \"nightWeatherCode\": 1,\n" +
            "            \"dayConditionstext\": \"晴\",\n" +
            "            \"nightConditionstext\": \"晴\",\n" +
            "            \"prep_status\": 0,\n" +
            "            \"maxTemp\": 14,\n" +
            "            \"minTemp\": 1,\n" +
            "            \"temp_status\": 0,\n" +
            "            \"aqi\": -1,\n" +
            "            \"wind\": \"西北风\",\n" +
            "            \"winp\": 3,\n" +
            "            \"dayCloud\": 0,\n" +
            "            \"lunarYear\": 2021,\n" +
            "            \"lunarMonth\": 3,\n" +
            "            \"lunarDay\": 8,\n" +
            "            \"leap\": false,\n" +
            "            \"cnEra\": \"辛丑年\",\n" +
            "            \"moonIcon\": 8,\n" +
            "            \"moonFraction\": 0.34,\n" +
            "            \"moonrise\": \"10:58\",\n" +
            "            \"moonset\": \"+02:19\",\n" +
            "            \"moonphase\": 2,\n" +
            "            \"festivalOrWork\": \"\",\n" +
            "            \"workOrNot\": \"\",\n" +
            "            \"sunrise\": \"05:30\",\n" +
            "            \"sunset\": \"18:57\",\n" +
            "            \"moon\": {\n" +
            "                \"index\": 3.5,\n" +
            "                \"desc\": \"前半夜可见\",\n" +
            "                \"feel\": \"极寒\",\n" +
            "                \"tip\": \"今晚条件尚可，可以赏月\"\n" +
            "            },\n" +
            "            \"next_new\": 23,\n" +
            "            \"next_full\": 8\n" +
            "        },\n" +
            "        {\n" +
            "            \"date\": \"2021-04-20\",\n" +
            "            \"timestamp\": 1618848000000,\n" +
            "            \"termName\": \"谷雨\",\n" +
            "            \"dayWeatherCode\": 1,\n" +
            "            \"nightWeatherCode\": 1,\n" +
            "            \"dayConditionstext\": \"晴\",\n" +
            "            \"nightConditionstext\": \"晴\",\n" +
            "            \"prep_status\": 0,\n" +
            "            \"maxTemp\": 14,\n" +
            "            \"minTemp\": 4,\n" +
            "            \"temp_status\": 0,\n" +
            "            \"aqi\": -1,\n" +
            "            \"wind\": \"西北风\",\n" +
            "            \"winp\": 3,\n" +
            "            \"dayCloud\": 0,\n" +
            "            \"lunarYear\": 2021,\n" +
            "            \"lunarMonth\": 3,\n" +
            "            \"lunarDay\": 9,\n" +
            "            \"leap\": false,\n" +
            "            \"cnEra\": \"辛丑年\",\n" +
            "            \"moonIcon\": 9,\n" +
            "            \"moonFraction\": 0.44,\n" +
            "            \"moonrise\": \"-10:58\",\n" +
            "            \"moonset\": \"02:19\",\n" +
            "            \"moonphase\": 3,\n" +
            "            \"festivalOrWork\": \"\",\n" +
            "            \"workOrNot\": \"\",\n" +
            "            \"sunrise\": \"05:28\",\n" +
            "            \"sunset\": \"18:58\",\n" +
            "            \"moon\": {\n" +
            "                \"index\": 3.5,\n" +
            "                \"desc\": \"前半夜可见\",\n" +
            "                \"feel\": \"微寒\",\n" +
            "                \"tip\": \"今晚条件尚可，可以赏月\"\n" +
            "            },\n" +
            "            \"next_new\": 22,\n" +
            "            \"next_full\": 7\n" +
            "        },\n" +
            "        {\n" +
            "            \"date\": \"2021-04-21\",\n" +
            "            \"timestamp\": 1618934400000,\n" +
            "            \"termName\": \"\",\n" +
            "            \"dayWeatherCode\": 1,\n" +
            "            \"nightWeatherCode\": 3,\n" +
            "            \"dayConditionstext\": \"晴\",\n" +
            "            \"nightConditionstext\": \"多云\",\n" +
            "            \"prep_status\": 0,\n" +
            "            \"maxTemp\": 21,\n" +
            "            \"minTemp\": 9,\n" +
            "            \"temp_status\": 1,\n" +
            "            \"aqi\": -1,\n" +
            "            \"wind\": \"西南风\",\n" +
            "            \"winp\": 1,\n" +
            "            \"dayCloud\": 18,\n" +
            "            \"lunarYear\": 2021,\n" +
            "            \"lunarMonth\": 3,\n" +
            "            \"lunarDay\": 10,\n" +
            "            \"leap\": false,\n" +
            "            \"cnEra\": \"辛丑年\",\n" +
            "            \"moonIcon\": 10,\n" +
            "            \"moonFraction\": 0.54,\n" +
            "            \"moonrise\": \"12:01\",\n" +
            "            \"moonset\": \"+03:31\",\n" +
            "            \"moonphase\": 4,\n" +
            "            \"festivalOrWork\": \"\",\n" +
            "            \"workOrNot\": \"\",\n" +
            "            \"sunrise\": \"05:27\",\n" +
            "            \"sunset\": \"18:59\",\n" +
            "            \"moon\": {\n" +
            "                \"index\": 3.5,\n" +
            "                \"desc\": \"通宵可见\",\n" +
            "                \"feel\": \"凉爽\",\n" +
            "                \"tip\": \"今晚条件尚可，可以赏月\"\n" +
            "            },\n" +
            "            \"next_new\": 21,\n" +
            "            \"next_full\": 6\n" +
            "        },\n" +
            "        {\n" +
            "            \"date\": \"2021-04-22\",\n" +
            "            \"timestamp\": 1619020800000,\n" +
            "            \"termName\": \"\",\n" +
            "            \"dayWeatherCode\": 1,\n" +
            "            \"nightWeatherCode\": 3,\n" +
            "            \"dayConditionstext\": \"晴\",\n" +
            "            \"nightConditionstext\": \"多云\",\n" +
            "            \"prep_status\": 0,\n" +
            "            \"maxTemp\": 21,\n" +
            "            \"minTemp\": 7,\n" +
            "            \"temp_status\": 0,\n" +
            "            \"aqi\": -1,\n" +
            "            \"wind\": \"东北风\",\n" +
            "            \"winp\": 2,\n" +
            "            \"dayCloud\": 18,\n" +
            "            \"lunarYear\": 2021,\n" +
            "            \"lunarMonth\": 3,\n" +
            "            \"lunarDay\": 11,\n" +
            "            \"leap\": false,\n" +
            "            \"cnEra\": \"辛丑年\",\n" +
            "            \"moonIcon\": 11,\n" +
            "            \"moonFraction\": 0.64,\n" +
            "            \"moonrise\": \"13:09\",\n" +
            "            \"moonset\": \"+04:01\",\n" +
            "            \"moonphase\": 4,\n" +
            "            \"festivalOrWork\": \"\",\n" +
            "            \"workOrNot\": \"\",\n" +
            "            \"sunrise\": \"05:25\",\n" +
            "            \"sunset\": \"19:00\",\n" +
            "            \"moon\": {\n" +
            "                \"index\": 3.5,\n" +
            "                \"desc\": \"通宵可见\",\n" +
            "                \"feel\": \"微寒\",\n" +
            "                \"tip\": \"今晚条件尚可，可以赏月\"\n" +
            "            },\n" +
            "            \"next_new\": 20,\n" +
            "            \"next_full\": 5\n" +
            "        },\n" +
            "        {\n" +
            "            \"date\": \"2021-04-23\",\n" +
            "            \"timestamp\": 1619107200000,\n" +
            "            \"termName\": \"\",\n" +
            "            \"dayWeatherCode\": 3,\n" +
            "            \"nightWeatherCode\": 3,\n" +
            "            \"dayConditionstext\": \"多云\",\n" +
            "            \"nightConditionstext\": \"多云\",\n" +
            "            \"prep_status\": 0,\n" +
            "            \"maxTemp\": 21,\n" +
            "            \"minTemp\": 8,\n" +
            "            \"temp_status\": 0,\n" +
            "            \"aqi\": -1,\n" +
            "            \"wind\": \"南风\",\n" +
            "            \"winp\": 2,\n" +
            "            \"dayCloud\": 9,\n" +
            "            \"lunarYear\": 2021,\n" +
            "            \"lunarMonth\": 3,\n" +
            "            \"lunarDay\": 12,\n" +
            "            \"leap\": false,\n" +
            "            \"cnEra\": \"辛丑年\",\n" +
            "            \"moonIcon\": 12,\n" +
            "            \"moonFraction\": 0.74,\n" +
            "            \"moonrise\": \"14:19\",\n" +
            "            \"moonset\": \"+04:30\",\n" +
            "            \"moonphase\": 4,\n" +
            "            \"festivalOrWork\": \"\",\n" +
            "            \"workOrNot\": \"\",\n" +
            "            \"sunrise\": \"05:24\",\n" +
            "            \"sunset\": \"19:01\",\n" +
            "            \"moon\": {\n" +
            "                \"index\": 5.0,\n" +
            "                \"desc\": \"通宵可见\",\n" +
            "                \"feel\": \"凉爽\",\n" +
            "                \"tip\": \"今晚皓月当空，非常适合赏月\"\n" +
            "            },\n" +
            "            \"next_new\": 19,\n" +
            "            \"next_full\": 4\n" +
            "        },\n" +
            "        {\n" +
            "            \"date\": \"2021-04-24\",\n" +
            "            \"timestamp\": 1619193600000,\n" +
            "            \"termName\": \"\",\n" +
            "            \"dayWeatherCode\": 17,\n" +
            "            \"nightWeatherCode\": 14,\n" +
            "            \"dayConditionstext\": \"小雨\",\n" +
            "            \"nightConditionstext\": \"阴\",\n" +
            "            \"prep_status\": 1,\n" +
            "            \"maxTemp\": 16,\n" +
            "            \"minTemp\": 7,\n" +
            "            \"temp_status\": 0,\n" +
            "            \"aqi\": -1,\n" +
            "            \"wind\": \"东南风\",\n" +
            "            \"winp\": 1,\n" +
            "            \"dayCloud\": 96,\n" +
            "            \"lunarYear\": 2021,\n" +
            "            \"lunarMonth\": 3,\n" +
            "            \"lunarDay\": 13,\n" +
            "            \"leap\": false,\n" +
            "            \"cnEra\": \"辛丑年\",\n" +
            "            \"moonIcon\": 13,\n" +
            "            \"moonFraction\": 0.83,\n" +
            "            \"moonrise\": \"15:31\",\n" +
            "            \"moonset\": \"+04:59\",\n" +
            "            \"moonphase\": 4,\n" +
            "            \"festivalOrWork\": \"\",\n" +
            "            \"workOrNot\": \"\",\n" +
            "            \"sunrise\": \"05:23\",\n" +
            "            \"sunset\": \"19:02\",\n" +
            "            \"moon\": {\n" +
            "                \"index\": 0.0,\n" +
            "                \"desc\": \"通宵可见\",\n" +
            "                \"feel\": \"微寒\",\n" +
            "                \"tip\": \"今晚天气不太好，不适合赏月\"\n" +
            "            },\n" +
            "            \"next_new\": 18,\n" +
            "            \"next_full\": 3\n" +
            "        },\n" +
            "        {\n" +
            "            \"date\": \"2021-04-25\",\n" +
            "            \"timestamp\": 1619280000000,\n" +
            "            \"termName\": \"\",\n" +
            "            \"dayWeatherCode\": 3,\n" +
            "            \"nightWeatherCode\": 3,\n" +
            "            \"dayConditionstext\": \"多云\",\n" +
            "            \"nightConditionstext\": \"多云\",\n" +
            "            \"prep_status\": 0,\n" +
            "            \"maxTemp\": 20,\n" +
            "            \"minTemp\": 10,\n" +
            "            \"temp_status\": 0,\n" +
            "            \"aqi\": -1,\n" +
            "            \"wind\": \"南风\",\n" +
            "            \"winp\": 2,\n" +
            "            \"dayCloud\": 57,\n" +
            "            \"lunarYear\": 2021,\n" +
            "            \"lunarMonth\": 3,\n" +
            "            \"lunarDay\": 14,\n" +
            "            \"leap\": false,\n" +
            "            \"cnEra\": \"辛丑年\",\n" +
            "            \"moonIcon\": 14,\n" +
            "            \"moonFraction\": 0.91,\n" +
            "            \"moonrise\": \"16:45\",\n" +
            "            \"moonset\": \"+05:29\",\n" +
            "            \"moonphase\": 4,\n" +
            "            \"festivalOrWork\": \"\",\n" +
            "            \"workOrNot\": \"班\",\n" +
            "            \"sunrise\": \"05:21\",\n" +
            "            \"sunset\": \"19:03\",\n" +
            "            \"moon\": {\n" +
            "                \"index\": 5.0,\n" +
            "                \"desc\": \"通宵可见\",\n" +
            "                \"feel\": \"凉爽\",\n" +
            "                \"tip\": \"今晚皓月当空，非常适合赏月\"\n" +
            "            },\n" +
            "            \"next_new\": 17,\n" +
            "            \"next_full\": 2\n" +
            "        },\n" +
            "        {\n" +
            "            \"date\": \"2021-04-26\",\n" +
            "            \"timestamp\": 1619366400000,\n" +
            "            \"termName\": \"\",\n" +
            "            \"dayWeatherCode\": 3,\n" +
            "            \"nightWeatherCode\": 14,\n" +
            "            \"dayConditionstext\": \"多云\",\n" +
            "            \"nightConditionstext\": \"阴\",\n" +
            "            \"prep_status\": 0,\n" +
            "            \"maxTemp\": 19,\n" +
            "            \"minTemp\": 8,\n" +
            "            \"temp_status\": 0,\n" +
            "            \"aqi\": -1,\n" +
            "            \"wind\": \"西北风\",\n" +
            "            \"winp\": 3,\n" +
            "            \"dayCloud\": 38,\n" +
            "            \"lunarYear\": 2021,\n" +
            "            \"lunarMonth\": 3,\n" +
            "            \"lunarDay\": 15,\n" +
            "            \"leap\": false,\n" +
            "            \"cnEra\": \"辛丑年\",\n" +
            "            \"moonIcon\": 15,\n" +
            "            \"moonFraction\": 0.97,\n" +
            "            \"moonrise\": \"18:01\",\n" +
            "            \"moonset\": \"+06:03\",\n" +
            "            \"moonphase\": 4,\n" +
            "            \"festivalOrWork\": \"\",\n" +
            "            \"workOrNot\": \"\",\n" +
            "            \"sunrise\": \"05:20\",\n" +
            "            \"sunset\": \"19:04\",\n" +
            "            \"moon\": {\n" +
            "                \"index\": 0.0,\n" +
            "                \"desc\": \"通宵可见\",\n" +
            "                \"feel\": \"凉爽\",\n" +
            "                \"tip\": \"今晚天气不太好，不适合赏月\"\n" +
            "            },\n" +
            "            \"next_new\": 16,\n" +
            "            \"next_full\": 1\n" +
            "        },\n" +
            "        {\n" +
            "            \"date\": \"2021-04-27\",\n" +
            "            \"timestamp\": 1619452800000,\n" +
            "            \"termName\": \"\",\n" +
            "            \"dayWeatherCode\": 14,\n" +
            "            \"nightWeatherCode\": 14,\n" +
            "            \"dayConditionstext\": \"阴\",\n" +
            "            \"nightConditionstext\": \"阴\",\n" +
            "            \"prep_status\": 0,\n" +
            "            \"maxTemp\": 18,\n" +
            "            \"minTemp\": 8,\n" +
            "            \"temp_status\": 0,\n" +
            "            \"aqi\": -1,\n" +
            "            \"wind\": \"东风\",\n" +
            "            \"winp\": 1,\n" +
            "            \"dayCloud\": 99,\n" +
            "            \"lunarYear\": 2021,\n" +
            "            \"lunarMonth\": 3,\n" +
            "            \"lunarDay\": 16,\n" +
            "            \"leap\": false,\n" +
            "            \"cnEra\": \"辛丑年\",\n" +
            "            \"moonIcon\": 16,\n" +
            "            \"moonFraction\": 1.0,\n" +
            "            \"moonrise\": \"-18:01\",\n" +
            "            \"moonset\": \"06:03\",\n" +
            "            \"moonphase\": 5,\n" +
            "            \"festivalOrWork\": \"\",\n" +
            "            \"workOrNot\": \"\",\n" +
            "            \"sunrise\": \"05:19\",\n" +
            "            \"sunset\": \"19:05\",\n" +
            "            \"moon\": {\n" +
            "                \"index\": 0.0,\n" +
            "                \"desc\": \"通宵可见\",\n" +
            "                \"feel\": \"凉爽\",\n" +
            "                \"tip\": \"今晚天气不太好，不适合赏月\"\n" +
            "            },\n" +
            "            \"next_new\": 15,\n" +
            "            \"next_full\": 29\n" +
            "        },\n" +
            "        {\n" +
            "            \"date\": \"2021-04-28\",\n" +
            "            \"timestamp\": 1619539200000,\n" +
            "            \"termName\": \"\",\n" +
            "            \"dayWeatherCode\": 3,\n" +
            "            \"nightWeatherCode\": 9,\n" +
            "            \"dayConditionstext\": \"多云\",\n" +
            "            \"nightConditionstext\": \"霾\",\n" +
            "            \"prep_status\": 0,\n" +
            "            \"maxTemp\": 18,\n" +
            "            \"minTemp\": 10,\n" +
            "            \"temp_status\": 0,\n" +
            "            \"aqi\": -1,\n" +
            "            \"wind\": \"东南风\",\n" +
            "            \"winp\": 2,\n" +
            "            \"dayCloud\": 49,\n" +
            "            \"lunarYear\": 2021,\n" +
            "            \"lunarMonth\": 3,\n" +
            "            \"lunarDay\": 17,\n" +
            "            \"leap\": false,\n" +
            "            \"cnEra\": \"辛丑年\",\n" +
            "            \"moonIcon\": 17,\n" +
            "            \"moonFraction\": 1.0,\n" +
            "            \"moonrise\": \"-19:19\",\n" +
            "            \"moonset\": \"06:42\",\n" +
            "            \"moonphase\": 6,\n" +
            "            \"festivalOrWork\": \"\",\n" +
            "            \"workOrNot\": \"\",\n" +
            "            \"sunrise\": \"05:17\",\n" +
            "            \"sunset\": \"19:06\",\n" +
            "            \"moon\": {\n" +
            "                \"index\": 0.0,\n" +
            "                \"desc\": \"通宵可见\",\n" +
            "                \"feel\": \"凉爽\",\n" +
            "                \"tip\": \"今晚天气不太好，不适合赏月\"\n" +
            "            },\n" +
            "            \"next_new\": 14,\n" +
            "            \"next_full\": 28\n" +
            "        }\n" +
            "    ]\n" +
            "}";
    private Weather40View mWeather40View;
    private SeresDashBordView mDashboardView;

    private static final String carJson = "{\"acDriverC\":\"21.5\",\"acPassengerC\":\"45.0\",\"airConditionStatus\":\"2\",\"chargeConnect\":\"2\",\"chargeEndTime\":\"0:00\",\"chargeStartTime\":\"12:00\",\"fuelComsum\":\"0\",\"fuelLevel\":\"60.0\",\"highBeam\":\"1\",\"lfDoorStatus\":\"1\",\"lockStatus\":\"1\",\"lowBeam\":\"1\",\"lrDoorStatus\":\"1\",\"lrWindowStatus\":\"1\",\"masterHeat\":\"1\",\"masterLevel\":\"0\",\"oilPercent\":\"\",\"powerComsum\":\"-200\",\"rechargeType\":\"1\",\"remainMileage\":\"458.0\",\"remainPower\":\"82\",\"rfDoorStatus\":\"1\",\"rfWindowStatus\":\"2\",\"roofWindowStatus\":\"0\",\"rrDoorStatus\":\"1\",\"rrWindowStatus\":\"2\",\"slaveHeat\":\"1\",\"slaveLevel\":\"0\",\"socMaxValue\":\"0\",\"thisAvgFuelConsum\":\"0\",\"thisAvgPowCurrent\":\"643\",\"totalMileage\":\"17049.0\",\"trunkDoorStatus\":\"1\",\"vehicleNoUpStatus\":\"2\",\"chargeStstus\":\"3\"}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_drag);
        mWeather40View = findViewById(R.id.weather40View);
        mDashboardView = findViewById(R.id.dashbord);
        mDashboardView.updateView(new Gson().fromJson(carJson, CarLiveDataBean.class), true);
        mDashboardView.setActionListener(new SeresDashBordView.ActionListener() {
            @Override
            public void onStartExamClicked(View view) {
                VerifyCodeDialog dialog = new VerifyCodeDialog();
                dialog.setOnInputCompleteListener(new VerifyCodeView.OnInputCompleteListener() {
                    @Override
                    public void onInput(String s) {
                        Toast.makeText(view.getContext(), s, Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.show(getSupportFragmentManager());
            }

            @Override
            public void onStopExamClicked(View view) {

            }

            @Override
            public void onStopChargeClicked(View view) {

            }
        });
        Handler mainHandler = new Handler();
        HandlerThread handlerThread = new HandlerThread("ser");
        handlerThread.start();
        Handler handler = new Handler(handlerThread.getLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                Gson gson = new Gson();
                FortyDailyResponse response = gson.fromJson(json, FortyDailyResponse.class);
                handlerThread.quit();
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mWeather40View.setData(response.getData());
                    }
                });
            }
        });
        mainHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.i(TAG, "postDelayed: ");
                startWeatherAnim();
                startDateAnim();
//                startOilAnim();
            }
        }, 2000);
    }

    public void startDateAnim() {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mWeather40View, "percent", 0, 1).setDuration(2000);
        objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
        objectAnimator.setRepeatMode(ValueAnimator.REVERSE);
        objectAnimator.start();
    }

    public void startWeatherAnim() {
        ObjectAnimator objectAnimator = ObjectAnimator.ofInt(mWeather40View, "hintIndex", 0, 39).setDuration(20000);
        objectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(@NonNull Animator animation) {
                Log.i(TAG, "onAnimationStart: ");
            }

            @Override
            public void onAnimationEnd(@NonNull Animator animation) {
                Log.i(TAG, "onAnimationEnd: ");
            }

            @Override
            public void onAnimationCancel(@NonNull Animator animation) {
                Log.i(TAG, "onAnimationCancel: ");
            }

            @Override
            public void onAnimationRepeat(@NonNull Animator animation) {
                Log.i(TAG, "onAnimationRepeat: ");
            }
        });
        objectAnimator.start();
    }
}