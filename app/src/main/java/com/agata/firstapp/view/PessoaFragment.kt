package com.agata.firstapp.view

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.agata.firstapp.databinding.FragmentPessoaBinding
import com.agata.firstapp.service.model.Pessoa
import com.agata.firstapp.viewmodel.PessoaViewModel
import java.time.LocalDateTime

class PessoaFragment : Fragment() {
    private val viewModel: PessoaViewModel by viewModels()

    private var _binding: FragmentPessoaBinding? = null
    private val binding: FragmentPessoaBinding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPessoaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Carregar a pessoa caso tenha selecionado
        arguments?.let {
            viewModel.getPessoa(it.getInt("pessoaId"))
        }

        binding.btnEnviar.setOnClickListener{
            var nome = binding.edtNome.editableText.toString()
            var anoNascimento = binding.edtIdade.editableText.toString()
            var sexo = ""
            var faixaEtaria = ""
            if (nome != "" && anoNascimento != "" && binding.rbMasculino.isChecked || binding.rbFeminino.isChecked) {

                if (binding.rbMasculino.isChecked){
                    sexo = "Masculino"
                } else {
                    sexo = "Feminino"
                }
                binding.tvNome.text = "Nome: ${nome}"


                val anoAtual = LocalDateTime.now().year
                var idade = anoAtual - anoNascimento.toInt()

                if (idade <= 12) {
                    faixaEtaria = "Infantil"
                } else if (idade <= 17) {
                    faixaEtaria = "Adolescente"
                } else if (idade <= 64) {
                    faixaEtaria = "Adulto"
                } else if (idade <= 115) {
                    faixaEtaria = "Idoso"
                } else {
                    Toast.makeText(requireContext(), "Digite sua idade correta, esses dados são invalidos!",
                        Toast.LENGTH_LONG).show()
                }

                binding.tvIdade.text = "Idade: ${idade}"

                val pessoa = Pessoa(
                    nome = nome,
                    idade = idade,
                    sexo = sexo,
                    faixaEtaria = faixaEtaria
                )

                viewModel.pessoa.value?.let {
                    pessoa.id = it.id
                    viewModel.update(pessoa)

                } ?: run {
                    viewModel.insert(pessoa)
                }


                binding.edtNome.editableText.clear()
                binding.edtIdade.editableText.clear()
                findNavController().navigateUp()
            } else {
                Toast.makeText(requireContext(), "Digite os dados", Toast.LENGTH_LONG).show()
            }

        }

        binding.btnDeletar.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle("Exclusão de pessoa")
                .setMessage("Você realmente deseja excluir?")
                .setPositiveButton("Sim"){ _,_ ->
                    viewModel.delete(viewModel.pessoa.value?.id ?: 0)
                    findNavController().navigateUp()
                }
                .setNegativeButton("Não"){_,_ ->}
                .show()
        }

        viewModel.pessoa.observe(viewLifecycleOwner){pessoa ->
            binding.edtNome.setText(pessoa.nome)
            binding.edtIdade.setText((LocalDateTime.now().year - pessoa.idade).toString())

            if (pessoa.sexo == "masculino"){
                binding.rbMasculino.isChecked = true
            } else {
                binding.rbFeminino.isChecked = true
            }

            binding.btnDeletar.visibility = View.VISIBLE
        }
    }
}