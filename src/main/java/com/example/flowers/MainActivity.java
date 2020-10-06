package com.example.flowers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.flowers.adapter.FlowersAdapter;
import com.example.flowers.model.FlowersModel;
import com.example.flowers.mvp.FlowersListPresenterImpl;
import com.example.flowers.mvp.IFlowersListContract;
import com.example.flowers.network.ApiClient;
import com.example.flowers.network.ApiInterface;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

public class MainActivity extends AppCompatActivity implements IFlowersListContract.IView {

    IFlowersListContract.IPresenter iPresenter;
    FlowersListPresenterImpl flowersListPresenter;
    RecyclerView recyclerView;
    FlowersAdapter flowersAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        initializePresentClass();
        initializeRecyclerView();

    }

    public void initializePresentClass(){
        flowersListPresenter = new FlowersListPresenterImpl( this );
        flowersListPresenter.displayFlowersList();
    }

    public void initializeRecyclerView(){
        recyclerView = findViewById( R.id.list );
        recyclerView.setLayoutManager( new LinearLayoutManager( this ) );
    }

    @Override
    protected void onResume(){
        super.onResume();
        flowersListPresenter.start();
    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void passDataToAdapter(List <FlowersModel> flowersModelList) {
    Log.i("data", flowersModelList.get(1).getName().toString());

    recyclerView.setAdapter( new FlowersAdapter(flowersModelList, R.layout.row_flowers, getApplicationContext()) );

    }

    @Override
    public void dismissProgressDialog() {

    }

    @Override
    public void displayError(String message) {
        Toast.makeText( this, message, Toast.LENGTH_SHORT ).show();
    }

    @Override
    public void setPresenter(IFlowersListContract.IPresenter presenter) {

        iPresenter=checkNotNull(presenter);
    }
}