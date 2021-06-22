package com.qihoo.myapplication.presenter;

import com.qihoo.myapplication.model.Goods;
import com.qihoo.myapplication.model.GoodsModel;
import com.qihoo.myapplication.model.IBaseModel;
import com.qihoo.myapplication.model.IGoodsModel;
import com.qihoo.myapplication.view.GoodsView;

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
