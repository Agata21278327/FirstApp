package com.agata.firstapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.agata.firstapp.R
import com.agata.firstapp.databinding.FragmentHomeBinding
import com.agata.firstapp.databinding.FragmentVerificaBinding

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnCadastroPessoa.setOnClickListener{
            findNavController().navigate(R.id.allPessoasFragment)
        }
        binding.btnCadastroPessoa.setOnClickListener {
            findNavController().navigate(R.id.allPessoasFragment)
        }

        binding.btnVerifica.setOnClickListener{
            findNavController().navigate(R.id.verificaFragment)
        }
    }
}