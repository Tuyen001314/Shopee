package com.example.shopeeapp.ui.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.core.view.doOnLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

abstract class BaseFragment : BaseView, Fragment(), NavigationCallback {

    private lateinit var mNavigation: NavigationControllerImp
    private lateinit var mNavigationCallback: NavigationCallback

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(javaClass.name, "onCreate()...")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeViews()
        initializeData()
        registerListeners()

        view.doOnLayout {

        }
    }

    open fun setupObserver(viewModel: BaseViewModel) {
        viewModel.toastLiveData.observe(viewLifecycleOwner) {
            if (it is Int) {
                showToast(it)
            } else {
                showToast(it.toString())
            }
        }
    }

    open fun onBackPressed() = false

    open fun showToast(@StringRes id: Int) {
        val con = activity ?: return
        Toast.makeText(con, id, Toast.LENGTH_SHORT).show()
    }

    private fun showToast(str: String) {
        val con = activity ?: return
        Toast.makeText(con, str, Toast.LENGTH_SHORT).show()
    }

    fun hiddenKeyboard() {
        (activity as? BaseActivity)?.showKeyboard(view)
    }

    fun showKeyboard(view: View? = null) {
        (activity as? BaseActivity)?.showKeyboard(view)
    }
}