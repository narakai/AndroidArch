package com.clem.arch_core.utils

import android.widget.Toast
import com.clem.arch_core.AndroidArchCoreApplication

fun toastShort(text: String) =
        Toast.makeText(AndroidArchCoreApplication.instance, text, Toast.LENGTH_SHORT).show()

fun toastLong(text: String) =
        Toast.makeText(AndroidArchCoreApplication.instance, text, Toast.LENGTH_LONG).show()
