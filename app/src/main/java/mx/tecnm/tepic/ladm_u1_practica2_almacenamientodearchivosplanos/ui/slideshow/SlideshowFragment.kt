package mx.tecnm.tepic.ladm_u1_practica2_almacenamientodearchivosplanos.ui.slideshow

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.eliminar_fragment.*
import mx.tecnm.tepic.ladm_u1_practica2_almacenamientodearchivosplanos.cosasUtiles
import mx.tecnm.tepic.ladm_u1_practica2_almacenamientodearchivosplanos.databinding.FragmentSlideshowBinding
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.lang.Exception

class SlideshowFragment : Fragment() {

    private var _binding: FragmentSlideshowBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val slideshowViewModel =
            ViewModelProvider(this).get(SlideshowViewModel::class.java)

        _binding = FragmentSlideshowBinding.inflate(inflater, container, false)
        val root: View = binding.root
        var local = 1
        binding.buscar.setOnClickListener() {
            try {
                val intentoAbrir =
                    InputStreamReader(requireActivity().openFileInput("pacienteC.txt"))
                var listaContenido = intentoAbrir.readLines()
                if (!cosasUtiles.paciente[0].equals("") || !pacienteText.text.toString()
                        .equals("")
                ) {
                    for (i in 0..listaContenido.size - 1) {
                        if (pacienteText.text.toString().equals(cosasUtiles.paciente[i])) {
                            pacienteText.setText(cosasUtiles.paciente[i])
                            servicioText.setText(cosasUtiles.servicio[i])
                            costoText.setText(cosasUtiles.costo[i])
                            fechaText.setText(cosasUtiles.fecha[i])
                            local = i
                        }
                    }

                }
            } catch (e: Exception) {
                AlertDialog.Builder(requireContext())
                    .setMessage(e.message).show()
            }
        }
        binding.actualizar.setOnClickListener(){
            try{
                val intentoAbrir =
                    InputStreamReader(requireActivity().openFileInput("pacienteC.txt"))
                var listaContenido = intentoAbrir.readLines()
                    cosasUtiles.paciente[local] = binding.pacienteText.text.toString()
                    cosasUtiles.servicio[local] = binding.servicioText.text.toString()
                    cosasUtiles.costo[local] =  binding.costoText.text.toString()
                    cosasUtiles.fecha[local] = binding.fechaText.text.toString()
                val pacientes =
                    OutputStreamWriter(requireActivity().openFileOutput("pacienteC.txt", 0))
                for (i in 0..listaContenido.size - 1) {
                    pacientes.write(cosasUtiles.paciente[i] + "|" + cosasUtiles.servicio[i] + "|" + cosasUtiles.fecha[i] + "|" + cosasUtiles.costo[i] + "\n")
                }
                pacientes.flush()
                pacientes.close()
                pacienteText.setText("")
                servicioText.setText("")
                costoText.setText("")
                fechaText.setText("")
                AlertDialog.Builder(requireContext())
                    .setMessage("SE ACTUALIZO DE MANERA CORRECTA").show()
            }catch (e:Exception){
            }
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}