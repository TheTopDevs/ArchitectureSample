package com.sample.app.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<V : ViewDataBinding> : AppCompatActivity() {

    lateinit var binding: V

    abstract fun getActivityLayoutRedId(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, getActivityLayoutRedId())
        binding.lifecycleOwner = this
    }
}