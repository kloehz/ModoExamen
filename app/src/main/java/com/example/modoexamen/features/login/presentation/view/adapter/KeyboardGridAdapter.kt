package com.example.modoexamen.features.login.presentation.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.modoexamen.R
import com.example.modoexamen.databinding.FragmentKeyboardButtonBinding
import com.example.modoexamen.features.login.utils.KeyboardUtils
import com.example.modoexamen.shared.utils.isSmallScreen

class KeyboardGridAdapter(
    private val numbersList: List<Pair<String, String>>,
    private val numberClickListener: OnNumberClickListener,
    private val keyboardUtils: KeyboardUtils
): RecyclerView.Adapter<KeyboardGridAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = FragmentKeyboardButtonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = ViewHolder(itemBinding)

        itemBinding.root.setOnClickListener {
            val position =
                holder.bindingAdapterPosition.takeIf { it != DiffUtil.DiffResult.NO_POSITION }
                    ?: return@setOnClickListener
            numberClickListener.onNumberClick(numbersList[position].first)
        }
        return holder
    }

    override fun getItemCount(): Int = numbersList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(numbersList[position])
    }

    interface OnNumberClickListener {
        fun onNumberClick(itemPressed: String)
    }

     inner class ViewHolder(private val binding: FragmentKeyboardButtonBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Pair<String, String>){
            if(item.first == "DELETE"){
                binding.imageView.setImageResource(R.drawable.delete_arrow)
                binding.imageView.visibility = View.VISIBLE
                binding.topText.visibility = View.GONE
                binding.bottomText.visibility = View.GONE
            } else if(item.first == "BIOMETRIC"){
                binding.imageView.setImageResource(R.drawable.biometry)
                val imageParams = binding.imageView.layoutParams as ViewGroup.LayoutParams
                imageParams.width = keyboardUtils.getBiometricIconSize()
                imageParams.height = keyboardUtils.getBiometricIconSize()
                binding.imageView.layoutParams = imageParams
                binding.imageView.visibility = View.VISIBLE
                binding.topText.visibility = View.GONE
                binding.bottomText.visibility = View.GONE
            } else {
                val topText = item.first
                binding.topText.text = topText
                binding.bottomText.text = item.second
                if(item.second.isBlank() && item.first == "0") binding.bottomText.visibility = View.GONE
            }
        }
    }
}