package mx.tecnm.tepic.ladm_u1_practica2_almacenamientodearchivosplanos.ui.eliminar

import android.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.eliminar_fragment.*
import mx.tecnm.tepic.ladm_u1_practica2_almacenamientodearchivosplanos.R
import mx.tecnm.tepic.ladm_u1_practica2_almacenamientodearchivosplanos.cosasUtiles
import mx.tecnm.tepic.ladm_u1_practica2_almacenamientodearchivosplanos.cosasUtiles.costo
import mx.tecnm.tepic.ladm_u1_practica2_almacenamientodearchivosplanos.cosasUtiles.fecha
import mx.tecnm.tepic.ladm_u1_practica2_almacenamientodearchivosplanos.cosasUtiles.paciente
import mx.tecnm.tepic.ladm_u1_practica2_almacenamientodearchivosplanos.cosasUtiles.servicio
import mx.tecnm.tepic.ladm_u1_practica2_almacenamientodearchivosplanos.databinding.EliminarFragmentBinding
import mx.tecnm.tepic.ladm_u1_practica2_almacenamientodearchivosplanos.databinding.FragmentHomeBinding
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.lang.Exception

class EliminarFragment : Fragment() {
    private var _binding: EliminarFragmentBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = EliminarFragment()
    }

    private lateinit var viewModel: EliminarViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = EliminarFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return inflater.inflate(R.layout.eliminar_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        var local = 1
        buscar1.setOnClickListener() {
            try{
                val intentoAbrir =
                    InputStreamReader(requireActivity().openFileInput("pacienteC.txt"))
                var listaContenido = intentoAbrir.readLines()
                if (!paciente[0].equals("") || !pacienteText.text.toString().equals("")) {
                    for (i in 0..listaContenido.size-1){
                        if (pacienteText.text.toString().equals(paciente[i])){
                            pacienteText.setText(paciente[i])
                            servicioText.setText(servicio[i])
                            costoText.setText(costo[i])
                            fechaText.setText(fecha[i])
                            local = i
                        }
                    }

                }
            }catch (e:Exception){
                AlertDialog.Builder(requireContext())
                    .setMessage(e.message).show()
            }
            eliminar.setOnClickListener(){
                try{
                    val intentoAbrir =
                        InputStreamReader(requireActivity().openFileInput("pacienteC.txt"))
                    var listaContenido = intentoAbrir.readLines()
                    for (i in local..13) {
                        paciente[i] = paciente[i+1]
                        servicio[i] = servicio[i+1]
                        costo[i] = costo[i+1]
                        fecha[i] = fecha[i+1]
                    }
                    paciente[14] = ""
                    servicio[14] = ""
                    costo[14] = ""
                    fecha[14] = ""
                    val pacientes =
                        OutputStreamWriter(requireActivity().openFileOutput("pacienteC.txt", 0))
                    for (i in 0..listaContenido.size - 2) {
                        pacientes.write(paciente[i] + "|" + servicio[i] + "|" + fecha[i] + "|" + costo[i] + "\n")
                    }
                    pacientes.flush()
                    pacientes.close()
                    pacienteText.setText("")
                    servicioText.setText("")
                    costoText.setText("")
                    fechaText.setText("")
                    AlertDialog.Builder(requireContext())
                        .setMessage("SE ELIMINO DE MANERA CORRECTA").show()
                }catch (e:Exception){
                }
            }


        }



       super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(EliminarViewModel::class.java)
        // TODO: Use the ViewModel
    }

}