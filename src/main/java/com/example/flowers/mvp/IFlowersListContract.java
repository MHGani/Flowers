package com.example.flowers.mvp;

import com.example.flowers.model.FlowersModel;

import java.util.List;

public interface IFlowersListContract {
    interface IPresenter extends BasePresenter{
        void displayFlowersList();
    }
    interface IView extends BaseView <IPresenter>{
        void showProgressDialog();
        void passDataToAdapter(List <FlowersModel> flowersModelList);
        void dismissProgressDialog();
        void displayError(String message);

    }

}
