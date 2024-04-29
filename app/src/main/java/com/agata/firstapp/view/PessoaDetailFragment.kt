package com.agata.firstapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.agata.firstapp.R
import com.agata.firstapp.databinding.FragmentPessoaBinding
import com.agata.firstapp.databinding.FragmentPessoaDetailBinding
import com.agata.firstapp.viewmodel.PessoaViewModel


class PessoaDetailFragment : Fragment() {

    //chamar a viewmodel para pegar os dados
    private val viewModel: PessoaViewModel by viewModels()

    //criar o binding para pegar os elementos da tela
    private var _binding: FragmentPessoaDetailBinding? = null
    private val binding: FragmentPessoaDetailBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //congigurar o binding
        // Inflate the layout for this fragment
        _binding = FragmentPessoaDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    //chamar a função onViewCreated onde vamos implementar o codigo
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //pegar o id da pessoa que foi enviado pela AllPessoasFragment
        //Setar a pessoa para ser carregada
        arguments?.let{
            viewModel.getPessoa(it.getInt("pessoaId"))
        }

        //carregar as informação de pessoa selecionada
        viewModel.pessoa.observe(viewLifecycleOwner) { pessoa ->
            binding.tvNome.text = pessoa.nome
            binding.tvIdade.text = pessoa.idade.toString() + " anos"
            binding.tvFaixaEtaria.text = pessoa.faixaEtaria

            if(pessoa.sexo == "Masculino"){
                binding.imgSexo.setImageResource(R.drawable.boyy)
            } else {
                binding.imgSexo.setImageResource(R.drawable.girll)
            }
        }
    }

}