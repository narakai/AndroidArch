package com.clem.androidarch.ui.main.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.clem.androidarch.R
import com.clem.androidarch.databinding.FragmentSearchBinding
import com.clem.androidarch.ui.main.viewmodel.GetFoodViewModel
import com.clem.androidarch.ui.main.viewmodel.SearchViewModel
import com.clem.arch_core.ui.BaseFragment
import com.zhengsr.tablib.view.flow.TabFlowLayout
import kotlinx.android.synthetic.main.fragment_search.*
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.scope.viewModel

private const val TAG = "SearchFragment"

class SearchFragment : BaseFragment<FragmentSearchBinding, SearchViewModel>() {
    private val EXTRA = "extra"
    override val layoutRes: Int = R.layout.fragment_search
    override val viewModel by lifecycleScope.viewModel<SearchViewModel>(this)
    override val transactionTag: String = TAG

    private lateinit var extra: String

    override fun onAttach(context: Context) {
        super.onAttach(context)
        arguments?.run { extra = getString(EXTRA, "") }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            lifecycleOwner = this@SearchFragment
            viewModel = this@SearchFragment.viewModel
        }
        initUI()
        initData()
    }

    private fun initData() =
        with(viewModel) {

        }

    private fun initUI() {
        test_tv.text = extra
    }


    companion object {
        fun newInstance(extra: String): SearchFragment =
            SearchFragment().apply {
                arguments = Bundle().apply {
                    putString(EXTRA, extra)
                }
            }
    }
}