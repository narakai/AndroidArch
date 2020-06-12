package com.clem.androidarch.ui.main.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.clem.androidarch.data.model.DataBean
import com.clem.androidarch.databinding.ItemArticleBinding

class MainAdapter(layoutResId: Int, data: MutableList<DataBean>?) :
    BaseQuickAdapter<DataBean, BaseDataBindingHolder<ItemArticleBinding>>(layoutResId, data) {
    override fun convert(holder: BaseDataBindingHolder<ItemArticleBinding>, item: DataBean) {

        // 获取 Binding
        val binding: ItemArticleBinding? = holder.dataBinding
        if (binding != null) {
            binding.data = item
//            binding.executePendingBindings()
        }
    }

}