package com.example.shopeeapp.ui.base

interface BaseView {

    fun getContentViewId(): Int

    fun initializeViews() {}

    fun registerListeners() {}

    fun initializeData() {}

    fun registerObservers() {}
}