package com.example.modoexamen.login.presentation.view

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.modoexamen.R
import com.example.modoexamen.core.UiState
import com.example.modoexamen.databinding.FragmentLoginBinding
import com.example.modoexamen.login.data.datasource.remote.RemoteLoginDataSource
import com.example.modoexamen.login.data.provider.RetrofitProvider
import com.example.modoexamen.login.data.service.LoginApiService
import com.example.modoexamen.login.domain.usecase.LoginRepositoryImplement
import com.example.modoexamen.login.presentation.components.PasswordDotsFragment
import com.example.modoexamen.login.presentation.components.recyclerviewitemdecoration.KeyboardButtonItemDecoration
import com.example.modoexamen.login.presentation.view.adapter.KeyboardGridAdapter
import com.example.modoexamen.login.presentation.viewmodel.LoginViewModel
import com.example.modoexamen.login.presentation.viewmodel.LoginViewModelFactory
import com.example.modoexamen.login.utils.KEYBOARD_NUMBERS
import com.example.modoexamen.login.utils.PASSWORD_LENGTH
import kotlinx.coroutines.launch

class LoginFragment : Fragment(R.layout.fragment_login), KeyboardGridAdapter.OnNumberClickListener {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var passwordDotsFragment: PasswordDotsFragment
    private var password: String = ""

    private val viewModel by viewModels<LoginViewModel> {
        LoginViewModelFactory(
            LoginRepositoryImplement(RemoteLoginDataSource(RetrofitProvider.instance.create(
                LoginApiService::class.java)))
        )
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)
        passwordDotsFragment = PasswordDotsFragment()
        childFragmentManager.beginTransaction()
            .replace(R.id.password_dots_container, passwordDotsFragment)
            .commitNow()
        setInitialComponentProperties()
    }

    override fun onNumberClick(itemPressed: String) {
        kotlin.runCatching {
            itemPressed.toInt()
        }.onSuccess {
            password += it.toString()
            if(password.length == PASSWORD_LENGTH){
                doLogin(password)
                return
            }
            passwordDotsFragment.keyboardPressed(itemPressed)
        }.onFailure {
            if(itemPressed == "DELETE"){
                password.dropLast(1)
            }
        }
    }

    private fun doLogin(password: String){
        viewModel.doLogin(password)
        viewLifecycleOwner.lifecycleScope.launch{
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.loginState().collect{result->
                    when(result) {
                        is UiState.Loading -> {
                            binding.textAndDotsContainer.visibility = View.GONE
                            binding.progressBar.visibility = View.VISIBLE
                        }
                        is UiState.Success -> {

                        }
                        is UiState.Error -> {
                            binding.textAndDotsContainer.visibility = View.VISIBLE
                            binding.progressBar.visibility = View.GONE
                            passwordDotsFragment.resetInputPassword()
                        }
                    }
                }
            }
        }
    }

    private fun setInitialComponentProperties(){
        binding.gridContainer.apply {
            adapter = KeyboardGridAdapter(KEYBOARD_NUMBERS.toList(), this@LoginFragment)
            layoutManager = GridLayoutManager(context,  3, GridLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
            addItemDecoration(KeyboardButtonItemDecoration(3, 26))
        }


    }
}