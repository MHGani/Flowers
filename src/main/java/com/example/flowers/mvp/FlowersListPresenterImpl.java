package com.example.flowers.mvp;

import com.example.flowers.model.FlowersModel;
import com.example.flowers.network.ApiClient;
import com.example.flowers.network.ApiInterface;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class FlowersListPresenterImpl implements IFlowersListContract.IPresenter  {

    IFlowersListContract.IView iView;
    private ApiInterface apiInterface = ApiClient.getRetrofit().create( ApiInterface.class );

    public FlowersListPresenterImpl(IFlowersListContract.IView iView){
        this.iView = iView;

    }
    @Override
    public void displayFlowersList() {
        apiInterface.getFlowersList()
                .subscribeOn( Schedulers.io() )
                .observeOn( AndroidSchedulers.mainThread() )
                .subscribe( new Observer <List <FlowersModel>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List <FlowersModel> flowersModels) {

                        iView.passDataToAdapter( flowersModels );
                    }

                    @Override
                    public void onError(Throwable e) {
                        iView.dismissProgressDialog();
                        iView.displayError( e.getLocalizedMessage() );
                    }

                    @Override
                    public void onComplete() {
                        iView.dismissProgressDialog();
                    }
                } );
    }

    @Override
    public void start() {
        iView.setPresenter( FlowersListPresenterImpl.this );
    }
}
