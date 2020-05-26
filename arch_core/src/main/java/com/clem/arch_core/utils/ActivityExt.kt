package com.clem.arch_core.utils

import android.app.Activity
import android.content.Intent
import com.clem.arch_core.ui.BaseActivity

inline fun <reified T : BaseActivity<*, *>> Activity.startActivity() =
        startActivity(Intent(this, T::class.java))