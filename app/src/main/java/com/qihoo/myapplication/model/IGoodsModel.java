package com.qihoo.myapplication.model;

import java.util.List;

public interface IGoodsModel extends IBaseModel {
    void loadGoodsData(OnLoadListener listener);
    interface OnLoadListener {
        void onComplete(List<Goods> goods);
    }
}
