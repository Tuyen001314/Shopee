package com.example.shopeeapp.ui.base

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import java.lang.reflect.ParameterizedType

abstract class BaseActivityBinding<T : ViewDataBinding, V : BaseViewModel> : BaseActivity() {

    open lateinit var dataBinding: T
    open lateinit var viewModel: V

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            dataBinding = DataBindingUtil.bind(view)!!
            dataBinding.lifecycleOwner = this
            @Suppress("UNCHECKED_CAST")
            val clazz: Class<V> =
                (this.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[1] as Class<V>
            viewModel = ViewModelProvider(this)[clazz]
        } catch (e: Exception) {
            finish()
            return
        }

        viewModel.toastLiveData.observe(this) {
            if (it is Int) {
                showToast(it)
            } else {
                showToast(it.toString())
            }
        }
        onViewCreated(savedInstanceState)
    }

    open fun onViewCreated(savedInstanceState: Bundle?) {

    }

    override fun onDestroy() {
        if (this::dataBinding.isInitialized) dataBinding.unbind()
        super.onDestroy()
    }

    protected val isInitialized get() = this::dataBinding.isInitialized && this::viewModel.isInitialized
}