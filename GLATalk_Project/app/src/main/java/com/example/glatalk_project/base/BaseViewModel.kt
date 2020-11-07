package com.example.glatalk_project.base;

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


open class BaseViewModel: ViewModel(){
    private val compositeDisposable = CompositeDisposable()


    fun addDisposable(disposable: Disposable){
        compositeDisposable.add(disposable)
    } //얘기 뭔지 모르겠음 나중에 지멘한테 물어봐야징


}
