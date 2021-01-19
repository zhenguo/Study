package com.quxiangtech.myapplication.model;

import java.util.ArrayList;
import java.util.List;

public class GoodsModel implements IGoodsModel {
    @Override
    public void loadGoodsData(OnLoadListener listener) {
        List<Goods> goods = getData();
        listener.onComplete(goods);
    }

    private List<Goods> getData() {
        List<Goods> goods = new ArrayList<>();
        return goods;
    }
}
