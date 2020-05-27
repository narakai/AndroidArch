package com.clem.androidarch.data.local

import com.clem.arch_core.utils.string
import com.tencent.mmkv.MMKV

//simply leave empty
class LocalDataSource(
    private val mmkv: MMKV
) {
    var page by mmkv.string("page", "")

    fun cachePageNumber(page: String) {
        this.page = page
    }
}