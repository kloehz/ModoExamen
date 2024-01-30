package com.example.modoexamen.features.login.presentation.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.modoexamen.R
import com.example.modoexamen.application.Constants
import com.example.modoexamen.core.UiState
import com.example.modoexamen.databinding.FragmentLoginBinding
import com.example.modoexamen.features.login.data.datasource.remote.RemoteLoginDataSource
import com.example.modoexamen.features.home.data.provider.HomeRetrofitProvider
import com.example.modoexamen.features.login.data.service.LoginApiService
import com.example.modoexamen.features.login.domain.usecase.LoginRepositoryImplement
import com.example.modoexamen.features.login.presentation.components.PasswordDotsFragment
import com.example.modoexamen.features.login.presentation.components.recyclerviewitemdecoration.KeyboardButtonItemDecoration
import com.example.modoexamen.features.login.presentation.view.adapter.KeyboardGridAdapter
import com.example.modoexamen.features.login.presentation.viewmodel.LoginViewModel
import com.example.modoexamen.features.login.presentation.viewmodel.LoginViewModelFactory
import com.example.modoexamen.features.login.utils.KEYBOARD_NUMBERS
import com.example.modoexamen.features.login.utils.PASSWORD_LENGTH
import kotlinx.coroutines.launch

class LoginFragment : Fragment(R.layout.fragment_login), KeyboardGridAdapter.OnNumberClickListener {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var passwordDotsFragment: PasswordDotsFragment
    private var password: String = ""
    private var availableRetries = 3
    private var isLogging = false

    private val viewModel by viewModels<LoginViewModel> {
        LoginViewModelFactory(
            LoginRepositoryImplement(RemoteLoginDataSource(
                HomeRetrofitProvider.instance.create(
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
        setUpLoginObserver()
    }

    override fun onNumberClick(itemPressed: String) {
        if(isLogging || availableRetries == 0) return
        kotlin.runCatching {
            itemPressed.toInt()
        }.onSuccess {
            password += it.toString()
            if(password.length == PASSWORD_LENGTH){
                isLogging = true
                viewModel.doLogin(password)
                return
            }
        }.onFailure {
            if(itemPressed == "DELETE"){
                password = password.dropLast(1)
            }
        }
        passwordDotsFragment.keyboardPressed(itemPressed)
    }

    private fun setUpLoginObserver(){
        viewLifecycleOwner.lifecycleScope.launch{
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.loginState().collect{result->
                    when(result) {
                        is UiState.Initial -> {}
                        is UiState.Loading -> {
                            binding.errorText.text = ""
                            binding.textAndDotsContainer.visibility = View.GONE
                            binding.progressBar.visibility = View.VISIBLE
                        }
                        is UiState.Success -> {
                            findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                        }
                        is UiState.Error -> {
                            availableRetries--
                            binding.errorText.text = getString(R.string.password_input_error, availableRetries.toString())
                            binding.errorText.visibility = View.VISIBLE
                            binding.textAndDotsContainer.visibility = View.VISIBLE
                            binding.progressBar.visibility = View.GONE
                            password = ""
                            passwordDotsFragment.resetInputPassword()
                            isLogging = false
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