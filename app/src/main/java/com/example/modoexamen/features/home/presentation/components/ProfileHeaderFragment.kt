package com.example.modoexamen.features.home.presentation.components

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.modoexamen.R
import com.example.modoexamen.databinding.FragmentProfileHeaderBinding
import com.example.modoexamen.shared.providers.DataBasesProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProfileHeaderFragment : Fragment(R.layout.fragment_profile_header) {
    private lateinit var binding: FragmentProfileHeaderBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProfileHeaderBinding.bind(view)
        lifecycleScope.launch {
            withContext(Dispatchers.IO){
                val loggedUserDao = DataBasesProvider(requireContext()).provide()
                val loggedUserInfo = loggedUserDao.getLoggedUserInfo()
                binding.hiUser.text =  getString(R.string.hi_user, loggedUserInfo.firstName)
            }
        }
    }
}