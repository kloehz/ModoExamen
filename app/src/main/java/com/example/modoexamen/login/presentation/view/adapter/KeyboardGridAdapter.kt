package com.example.modoexamen.login.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.modoexamen.databinding.FragmentKeyboardButtonBinding

class KeyboardGridAdapter(
    private val numbersList: List<Pair<String, String>>,
    private val numberClickListener: OnNumberClickListener): RecyclerView.Adapter<KeyboardGridAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KeyboardGridAdapter.ViewHolder {
        val itemBinding = FragmentKeyboardButtonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = ViewHolder(itemBinding)

        itemBinding.root.setOnClickListener {
            // Here we take the touch position if it is != -1
            val position =
                holder.adapterPosition.takeIf { it != DiffUtil.DiffResult.NO_POSITION }
                    ?: return@setOnClickListener
            numberClickListener.onNumberClick(numbersList[position].first)
        }
        return holder
    }

    override fun getItemCount(): Int = numbersList.size

    override fun onBindViewHolder(holder: KeyboardGridAdapter.ViewHolder, position: Int) {
        when(holder){
            is ViewHolder -> holder.bind(numbersList[position])
        }
    }

    interface OnNumberClickListener {
        fun onNumberClick(number: String)
    }

     inner class ViewHolder(private val binding: FragmentKeyboardButtonBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Pair<String, String>){
            var topText = if (item.first != "BIOMETRIC" && item.first != "DELETE") item.first else ""
            if(item.first != "BIOMETRIC" || item.first != "DELETE") binding.topText.text = topText
            binding.bottomText.text = item.second
            //if(item.second.isBlank()) binding.bottomText.visibility = View.GONE
        }
    }
}