package com.clem.arch_core.utils

import androidx.fragment.app.Fragment
import com.clem.arch_core.ui.BaseActivity

/**
 * Created by TanJiaJun on 2019-08-07.
 */
inline fun <reified T : BaseActivity<*, *>> Fragment.startActivity() =
        startActivity(android.content.Intent(context, T::class.java))