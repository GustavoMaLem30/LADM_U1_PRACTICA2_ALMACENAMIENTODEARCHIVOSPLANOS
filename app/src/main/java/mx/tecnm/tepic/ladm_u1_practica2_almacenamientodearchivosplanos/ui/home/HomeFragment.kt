package mx.tecnm.tepic.ladm_u1_practica2_almacenamientodearchivosplanos.ui.home

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import mx.tecnm.tepic.ladm_u1_practica2_almacenamientodearchivosplanos.CustomAdapter
import mx.tecnm.tepic.ladm_u1_practica2_almacenamientodearchivosplanos.R
import mx.tecnm.tepic.ladm_u1_practica2_almacenamientodearchivosplanos.cosasUtiles
import mx.tecnm.tepic.ladm_u1_practica2_almacenamientodearchivosplanos.databinding.FragmentHomeBinding
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.lang.Exception

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val recyclerView = binding.recyclerView.findViewById<RecyclerView>(R.id.recyclerView)
        val adapter = CustomAdapter()

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        try {
            val intentoAbrir =
                InputStreamReader(requireActivity().openFileInput("pacienteC.txt"))
            var listaContenido = intentoAbrir.readLines()
            for(i in 0..listaContenido.size-1){
                var parts = listaContenido[i].split("|")
                cosasUtiles.paciente[i] = parts[0]
                cosasUtiles.servicio[i] = parts[1]
                cosasUtiles.fecha[i] = parts[2]
                cosasUtiles.costo[i] = parts[3]
            }
        }catch (e:Exception){
            AlertDialog.Builder(requireContext())
                .setMessage(e.message).show()
        }
        return root

    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}