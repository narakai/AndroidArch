package com.clem.androidarch.ui.main.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.clem.androidarch.R
import com.clem.androidarch.data.model.DataBean
import com.clem.androidarch.databinding.ActivityMainBinding
import com.clem.androidarch.databinding.LayoutErrorBinding
import com.clem.androidarch.ui.main.adapter.MainAdapter
import com.clem.androidarch.ui.main.viewmodel.MainViewModel
import com.clem.arch_core.ui.BaseActivity
import com.clem.arch_core.ui.BaseViewModel
import com.clem.arch_core.utils.startActivity
import com.clem.arch_core.utils.toastShort
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.scope.viewModel
private const val TAG = "MainActivity"

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(), BaseViewModel.Handlers, MainViewModel.Handlers {

    override val layoutRes: Int = R.layout.activity_main
    override val viewModel by lifecycleScope.viewModel<MainViewModel>(this)

    private lateinit var adapter: MainAdapter
    private var errorView: View? = null
    private var itemList: MutableList<DataBean> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        registerToastEvent()
        initUI()
        initData()
    }

    private fun initUI() =
        with(binding) {
            lifecycleOwner = this@MainActivity
            viewModel = this@MainActivity.viewModel
            handlers = this@MainActivity

            main_rv.layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = MainAdapter(R.layout.item_article, itemList)
            main_rv.adapter = this@MainActivity.adapter

            //error 需要重新inflate, loading已经include进去
            vsError.setOnInflateListener { _, inflated ->
                DataBindingUtil.bind<LayoutErrorBinding>(inflated)?.run {
                    lifecycleOwner = this@MainActivity
                    viewModel = this@MainActivity.viewModel
                    handlers = this@MainActivity
                }
            }

            this@MainActivity.viewModel.isShowErrorView.observe(
                this@MainActivity,
                Observer { isShowErrorView ->
                    if (isShowErrorView) {
                        if (!vsError.isInflated) {
                            vsError.viewStub?.inflate()?.also { errorView = it }
                        } else {
                            errorView?.visibility = View.VISIBLE
                        }
                    } else {
                        errorView?.visibility = View.GONE
                    }
                })
        }

    private fun initData() =
        with(viewModel) {
            getArticle("0")
            articleData.observe(this@MainActivity, Observer {
//                toastShort(it.data.datas.size.toString())
                itemList.clear()
                itemList.addAll(it.data.datas as MutableList<DataBean>)
                adapter.notifyDataSetChanged()
            })
        }

    override fun onRetryClick(view: View) {
        viewModel.getArticle("0")
    }

    override fun onTvClick(view: View) {
        Log.d(TAG, "onTvClick: ")
        startActivity<GetFoodActivity>()
    }
}
