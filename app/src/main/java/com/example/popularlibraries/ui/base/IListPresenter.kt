package com.example.popularlibraries.ui.base

import android.view.View
import androidx.appcompat.view.menu.MenuView

interface IListPresenter<V : IItemView> {
    var itemClickListener: (pos:Int) -> Unit

    fun getCount(): Int

    fun bindView(view: V)
}