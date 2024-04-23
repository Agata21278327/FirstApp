package com.agata.firstapp.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.agata.firstapp.R
import com.agata.firstapp.databinding.ListItemPessoaBinding
import com.agata.firstapp.service.model.Pessoa
import java.util.EventListener

class PessoaAdapter(pessoas: List<Pessoa>?, private val clickListListener: (Pessoa) -> Unit) :
    RecyclerView.Adapter<PessoaAdapter.PessoaViewHolder>() {

        private var pessoaList: List<Pessoa> = arrayListOf()

    class PessoaViewHolder(private val binding: ListItemPessoaBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(pessoa: Pessoa, clickListListener: (Pessoa) -> Unit) {
            binding.tvNome.text = pessoa.nome
            binding.tvIdade.text = pessoa.idade.toString()
            binding.tvfaixaEtaria.text = pessoa.faixaEtaria

            //metodo 1 para esconder ou mostrar uma imagem
            if (pessoa.sexo == "Masculino") {
                binding.imgMasc.visibility = View.VISIBLE
                binding.imgFem.visibility = View.GONE
            } else {
                binding.imgFem.visibility = View.VISIBLE
                binding.imgMasc.visibility = View.GONE
            }

            //metodo 2 para mostrar uma imagem desejada

//            if (pessoa.sexo == "Masculino"){
//                binding.imgMasc.setImageResource(R.drawable.ic_man)
//            } else {
//                binding.imgFem.setImageResource(R.drawable.ic_woman)
//            }

            binding.root.setOnClickListener {
                clickListListener(pessoa)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PessoaViewHolder {
        val listItemPessoaBinding =
            ListItemPessoaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PessoaViewHolder(listItemPessoaBinding)
    }

    override fun getItemCount(): Int {
        return  pessoaList.count()

    }

    override fun onBindViewHolder(holder: PessoaViewHolder, position: Int) {
        holder.bind(pessoaList[position], clickListListener)
    }

    fun updatePessoa(list: List<Pessoa>){
        pessoaList = list
        notifyDataSetChanged()
    }
}