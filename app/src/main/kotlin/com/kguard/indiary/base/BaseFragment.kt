package com.kguard.indiary.base

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<T : ViewDataBinding>(@LayoutRes val layoutRes: Int) : Fragment() {
    private var _binding: T? = null
    val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.lifecycleOwner = viewLifecycleOwner //Databinding 으로 livedata를 사용하기 때문에
//        initView()
        initUI()
        initObserver()
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onResume() {
        initListener()
        super.onResume()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /*protected fun showToast(msg: String) =
       Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()*/
    protected fun loge(msg: String, tag: String = javaClass.simpleName) {
        Log.e(tag, msg)
    }

    //    abstract fun initView()
    abstract fun initData()
    abstract fun initUI()
    abstract fun initObserver()
    abstract fun initListener()
}