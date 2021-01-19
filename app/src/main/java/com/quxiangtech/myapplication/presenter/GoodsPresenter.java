package com.quxiangtech.myapplication.presenter;

import com.quxiangtech.myapplication.model.Goods;
import com.quxiangtech.myapplication.model.GoodsModel;
import com.quxiangtech.myapplication.model.IBaseModel;
import com.quxiangtech.myapplication.model.IGoodsModel;
import com.quxiangtech.myapplication.view.GoodsView;

import java.lang.ref.WeakReference;
import java.util.List;

public class GoodsPresenter extends BasePresenter<GoodsView> {
    private IBaseModel mModel;

    private GoodsModel mGoodsModel = new GoodsModel();

    public void fetch() {
        if (mView.get() != null && mModel != null) {
            mGoodsModel.loadGoodsData(new IGoodsModel.OnLoadListener() {
                @Override
                public void onComplete(List<Goods> goods) {
                    mView.get().showErrorMessage("onComplete");
                }
            });
        }
    }
}
