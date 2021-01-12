package com.clem.androidarch.ui.main.activity

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.clem.androidarch.R
import com.clem.androidarch.databinding.ActivityGetFoodBinding
import com.clem.androidarch.ui.main.fragment.SearchFragment
import com.clem.androidarch.ui.main.viewmodel.GetFoodViewModel
import com.clem.arch_core.ui.BaseActivity
import com.zhengsr.tablib.view.adapter.TabFlowAdapter
import kotlinx.android.synthetic.main.activity_get_food.*
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.scope.viewModel

private const val TAG = "GetFoodActivity"

class GetFoodActivity : BaseActivity<ActivityGetFoodBinding, GetFoodViewModel>() {
    override val layoutRes: Int = R.layout.activity_get_food
    override val viewModel by lifecycleScope.viewModel<GetFoodViewModel>(this)

    private lateinit var searchFragments: MutableList<SearchFragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(binding) {
            lifecycleOwner = this@GetFoodActivity
            viewModel = this@GetFoodActivity.viewModel
        }
        initUI()
        initData()
    }

    private fun initData() =
            with(viewModel) {

            }

    private fun initUI() {

        searchFragments = mutableListOf<SearchFragment>().apply {
            for (i in 1..4) {
                add(SearchFragment.newInstance(i.toString()))
            }
        }

        val title = mutableListOf<String>().apply {
            for (i in 1..4) {
                add(i.toString() + "haha")
            }
        }

        Log.d(TAG, "initUI: " + searchFragments.size)

        viewpager.apply {
            adapter = ViewPagerAdapter(this@GetFoodActivity)
        }

        triflow.apply {
            val drawable: Drawable? = ContextCompat.getDrawable(context, R.drawable.corner)
            setBackgroundDrawable(drawable)
            setViewPager(viewpager)
            setTextId(R.id.item_text)
            setSelectedColor(ContextCompat.getColor(context, R.color.colorAccent))
            setUnSelectedColor(ContextCompat.getColor(context, R.color.colorAccent))
            adapter = object : TabFlowAdapter<String>(R.layout.item_msg, title) {
                override fun bindView(view: View?, data: String?, position: Int) {
                    setText(view, R.id.item_text, data)
                }

                override fun onItemClick(view: View, data: String, position: Int) {
                    super.onItemClick(view, data, position)
                    viewpager.currentItem = position
                }
            }
        }


    }

    inner class ViewPagerAdapter(appCompatActivity: AppCompatActivity) : FragmentStateAdapter(appCompatActivity) {
        override fun createFragment(position: Int): Fragment {
            return searchFragments[position]
        }

        override fun getItemCount(): Int {
            return searchFragments.size
        }
    }

}

