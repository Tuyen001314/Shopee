package com.example.shopeeapp.ui.base

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : BaseView, AppCompatActivity(), NavigationCallback {

    protected lateinit var view: View

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        view = layoutInflater.inflate(getContentViewId(), null)
        setContentView(view)
        init(view)
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments[0].childFragmentManager.fragments.forEach {
            if (it is BaseFragment && it.onBackPressed()) {
                return
            }
        }
    }

    open fun init(view: View) {

    }

    fun hiddenKeyboard() {
        var viewFocus = view.findFocus()
        if (viewFocus == null) {
            viewFocus = findViewById(android.R.id.content) ?: return
        }

        val inputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(viewFocus.windowToken, 0)
        viewFocus.clearFocus()
    }

    fun showKeyboard(view: View? = null) {
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        val v = view ?: this.currentFocus as? EditText
        if (v != null) {
            inputMethodManager.showSoftInput(v, InputMethodManager.SHOW_IMPLICIT)
        } else {
            inputMethodManager.toggleSoftInput(
                InputMethodManager.SHOW_FORCED,
                InputMethodManager.HIDE_IMPLICIT_ONLY
            )
        }
    }

    fun showToast(str: String) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show()
    }

    fun showToast(@StringRes id: Int) {
        Toast.makeText(this, id, Toast.LENGTH_SHORT).show()
    }
}