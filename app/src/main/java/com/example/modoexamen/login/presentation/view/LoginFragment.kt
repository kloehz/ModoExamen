package com.example.modoexamen.login.presentation.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.modoexamen.R
import com.example.modoexamen.databinding.FragmentLoginBinding
import com.example.modoexamen.login.data.datasource.remote.RemoteLoginDataSource
import com.example.modoexamen.login.data.provider.RetrofitProvider
import com.example.modoexamen.login.data.service.LoginApiService
import com.example.modoexamen.login.domain.usecase.LoginRepositoryImplement
import com.example.modoexamen.login.presentation.viewmodel.LoginViewModel
import com.example.modoexamen.login.presentation.viewmodel.LoginViewModelFactory

class LoginFragment : Fragment(R.layout.fragment_login) {
    private lateinit var binding: FragmentLoginBinding
    private val viewModel by viewModels<LoginViewModel> {
        LoginViewModelFactory(
            LoginRepositoryImplement(RemoteLoginDataSource(RetrofitProvider.instance.create(
                LoginApiService::class.java)))
        )
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)
    }
}