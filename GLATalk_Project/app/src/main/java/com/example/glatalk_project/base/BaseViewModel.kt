package com.example.glatalk_project.base;

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


open class BaseViewModel: ViewModel(){
    private val compositeDisposable = CompositeDisposable()


    fun addDisposable(disposable: Disposable){
        compositeDisposable.add(disposable)
    } //얘기 뭔지 모르겠음 나중에 지멘한테 물어봐야징
    //이거 Rxjava 관련


}
