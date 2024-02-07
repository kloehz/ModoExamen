package com.example.modoexamen.features.login.presentation.view

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.modoexamen.MainActivity
import com.example.modoexamen.R
import com.example.modoexamen.core.UiState
import com.example.modoexamen.databinding.FragmentLoginBinding
import com.example.modoexamen.features.feed.presentation.viewmodel.FeedViewModel
import com.example.modoexamen.features.login.data.datasource.remote.RemoteLoginDataSource
import com.example.modoexamen.features.home.data.provider.HomeRetrofitProvider
import com.example.modoexamen.features.home.presentation.viewmodel.HomeViewModel
import com.example.modoexamen.features.login.data.service.LoginApiService
import com.example.modoexamen.features.login.domain.usecase.LoginRepositoryImplement
import com.example.modoexamen.features.login.presentation.components.PasswordDotsFragment
import com.example.modoexamen.features.login.presentation.components.recyclerviewitemdecoration.KeyboardButtonItemDecoration
import com.example.modoexamen.features.login.presentation.view.adapter.KeyboardGridAdapter
import com.example.modoexamen.features.login.presentation.viewmodel.LoginViewModel
import com.example.modoexamen.features.login.presentation.viewmodel.LoginViewModelFactory
import com.example.modoexamen.features.login.utils.KEYBOARD_NUMBERS
import com.example.modoexamen.features.login.utils.PASSWORD_LENGTH
import com.example.modoexamen.features.login.utils.getLoginErrorMessage
import com.example.modoexamen.shared.model.ErrorCodes
import com.example.modoexamen.shared.providers.DataBasesProvider
import com.example.modoexamen.shared.utils.DependenciesContainer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class LoginFragment : Fragment(R.layout.fragment_login), KeyboardGridAdapter.OnNumberClickListener {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var passwordDotsFragment: PasswordDotsFragment
    private var password: String = ""
    private var availableRetries = 3
    private var isLogging = false
    private lateinit var appContainer: DependenciesContainer
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var feedViewModel: FeedViewModel
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
        appContainer = (requireActivity() as MainActivity).appContainer
        homeViewModel = ViewModelProvider(requireActivity(), appContainer.homeViewModel)[HomeViewModel::class.java]
        feedViewModel = ViewModelProvider(requireActivity(), appContainer.feedViewModel)[FeedViewModel::class.java]

        // In real contexto, it need to be in the viewmodel
        lifecycleScope.launch {
            withContext(Dispatchers.IO){
                val loggedUserDao = DataBasesProvider(requireContext()).provide()
                val (_, firstName, lastName) = loggedUserDao.getLoggedUserInfo()
                binding.nameTextView.text = "$firstName $lastName"
            }
        }
        setInitialComponentProperties()
        setUpMeObserver()
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
                            homeViewModel.getMe()
                            feedViewModel.getFeed()
                        }
                        is UiState.Error -> {
                            if(result.error == ErrorCodes.authentication_fail) availableRetries--
                            val errorMessage = getLoginErrorMessage(result.error)
                            binding.errorText.text = getString(errorMessage, availableRetries.toString())
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

    private fun setUpMeObserver(){
        viewLifecycleOwner.lifecycleScope.launch{
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED){
                homeViewModel.homeState().collect{ result ->
                    when(result) {
                        is UiState.Initial -> {}
                        is UiState.Loading -> {}
                        is UiState.Success -> {
                            findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                        }
                        is UiState.Error -> {}
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