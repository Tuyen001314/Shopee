package com.example.shopeeapp.session

class AppSession {

    companion object {
        private var sInstance: AppSession? = null

        @JvmStatic
        val instance: AppSession
            get() {
                if (sInstance == null) {
                    sInstance = AppSession()
                }
                return sInstance as AppSession
            }
    }
}