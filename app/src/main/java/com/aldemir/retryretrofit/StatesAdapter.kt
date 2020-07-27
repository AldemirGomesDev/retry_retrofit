package com.aldemir.retryretrofit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StatesAdapter(
    private val listStates: ArrayList<States>
) : RecyclerView.Adapter<StatesAdapter.ViewHolder>() {

    private lateinit var mClickListener: ClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_states, parent, false)
        return ViewHolder(view)
    }

    fun setOnItemClickListener(aClickListener: ClickListener) {
        mClickListener = aClickListener
    }

    interface ClickListener {
        fun onClick(position: Int, aView: View)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listStates[position]
        holder.mSigla.text = "Sigla: ${item.sigla}"

        holder.mRegion.text = "Região: ${item.regiao}"

        holder.mState.text = "Estado: ${item.nome}"
    }

    override fun getItemCount(): Int = listStates.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        val mSigla: TextView = view.findViewById(R.id.text_sigla)
        val mState: TextView = view.findViewById(R.id.text_state)
        val mRegion: TextView = view.findViewById(R.id.text_region)

        override fun toString(): String {
            return super.toString() + " '" + mSigla.text + "'"
        }

        override fun onClick(v: View) {
            mClickListener.onClick(adapterPosition, v)
        }
        init {
            view.setOnClickListener(this)
        }

    }
}