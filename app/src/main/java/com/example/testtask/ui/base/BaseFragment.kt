package com.example.testtask.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

abstract class BaseFragment<V : BaseViewModel> : Fragment() {

    abstract val layoutId: Int
    lateinit var fragmentRootView: View
    abstract val mViewModel: V

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentRootView = inflater.inflate(layoutId, container, false);
        return fragmentRootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mViewModel.listenLoadingStateUpdates()
        initialization(view, savedInstanceState)
        fragmentRootView = view
        super.onViewCreated(view, savedInstanceState)
    }

    protected abstract fun initialization(view: View, savedInstanceState: Bundle?)

    open fun showLoading(show: Boolean) {
        if (show) {
            activity?.root?.loading?.visibility = View.VISIBLE
        } else {
            activity?.root?.loading?.visibility = View.GONE
        }
    }

    protected fun BaseViewModel.listenLoadingStateUpdates() {
        loadingStateData.observe(viewLifecycleOwner, { result ->
            when (result) {
                State.LOADING -> {
                    showLoading(true)
                }
                else -> {
                    showLoading(false)
                    when(result){
                        is State.ERROR -> showErrorMsg(result.msg)
                        else -> Unit
                    }
                }
            }
        })
    }

    private fun showErrorMsg(msg: String) {
        Toast.makeText(fragmentRootView.context, msg, Toast.LENGTH_SHORT).show()
    }
}
