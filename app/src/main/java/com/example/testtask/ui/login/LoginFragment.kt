package com.example.testtask.ui.login

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.testtask.R
import com.example.testtask.ui.base.BaseFragment
import kotlinx.android.synthetic.main.layout_login_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : BaseFragment<LoginViewModel>() {
    override val layoutId: Int = R.layout.layout_login_fragment
    override val mViewModel: LoginViewModel by viewModel()

    override fun initialization(view: View, savedInstanceState: Bundle?) {
        login_btn.setOnClickListener { view ->
            mViewModel.login(
                email = textInputEditText_email.text.toString(),
                pass = textInputEditText_pass.text.toString()
            ){
                view.findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToContentFragment(it))
            }
        }
    }
}