package com.example.modoexamen.features.login.presentation.components

import android.content.res.ColorStateList
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.graphics.Color
import android.util.Log
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import com.example.modoexamen.R
import com.example.modoexamen.databinding.FragmentPasswordDotsBinding
import com.example.modoexamen.features.login.utils.PASSWORD_LENGTH
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PasswordDotsFragment : Fragment(R.layout.fragment_password_dots) {
    private var currentIndex = 0
    private val coroutineScope = CoroutineScope(Dispatchers.Default)
    private lateinit var binding: FragmentPasswordDotsBinding
    private lateinit var dotsArray: Array<ImageView>
    private lateinit var numberArray: Array<TextView>
    private lateinit var numberContainerArray: Array<FrameLayout>
    private var colorStateListActive: Int = 0
    private var colorStateListInactive: Int = 0
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPasswordDotsBinding.bind(view)
        colorStateListActive = ContextCompat.getColor(requireContext(), R.color.payGradientEnd)
        colorStateListInactive = ContextCompat.getColor(requireContext(), R.color.secondaryGray08)
        initializeIds()
    }

    private fun initializeIds(){
        dotsArray = arrayOf(
            binding.passwordDot1,
            binding.passwordDot2,
            binding.passwordDot3,
            binding.passwordDot4,
            binding.passwordDot5,
            binding.passwordDot6,
        )
        numberArray = arrayOf(
            binding.passwordNumber1,
            binding.passwordNumber2,
            binding.passwordNumber3,
            binding.passwordNumber4,
            binding.passwordNumber5,
            binding.passwordNumber6,
        )
        numberContainerArray = arrayOf(
            binding.passwordNumberContainer1,
            binding.passwordNumberContainer2,
            binding.passwordNumberContainer3,
            binding.passwordNumberContainer4,
            binding.passwordNumberContainer5,
            binding.passwordNumberContainer6,
        )
    }

    fun resetInputPassword(){
        for(idx in currentIndex downTo 0){
            dotsArray[idx].background.setTintList(ColorStateList.valueOf(colorStateListInactive))
        }
        currentIndex = 0
    }

    fun keyboardPressed(value: String){
        kotlin.runCatching {
            value.toInt()
        }.onSuccess {
            if(currentIndex >= PASSWORD_LENGTH) return;
            dotsArray[currentIndex].visibility = View.GONE
            numberArray[currentIndex].text = value
            numberContainerArray[currentIndex].visibility = View.VISIBLE
            val lastIndex = currentIndex
            coroutineScope.launch {
                withContext(Dispatchers.Main) {
                    delay(200L)
                    numberContainerArray[lastIndex].visibility = View.GONE
                    dotsArray[lastIndex].background.setTintList(ColorStateList.valueOf(colorStateListActive))
                    dotsArray[lastIndex].visibility = View.VISIBLE
                }
            }
            if(currentIndex < PASSWORD_LENGTH) currentIndex++
        }.onFailure {
            if(currentIndex > 0) currentIndex--;
            if(value == "DELETE") dotsArray[currentIndex].background.setTintList(ColorStateList.valueOf(colorStateListInactive))
        }
    }
}