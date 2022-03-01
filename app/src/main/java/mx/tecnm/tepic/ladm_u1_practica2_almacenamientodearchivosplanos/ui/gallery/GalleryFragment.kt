package mx.tecnm.tepic.ladm_u1_practica2_almacenamientodearchivosplanos.ui.gallery

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import mx.tecnm.tepic.ladm_u1_practica2_almacenamientodearchivosplanos.cosasUtiles.costo
import mx.tecnm.tepic.ladm_u1_practica2_almacenamientodearchivosplanos.cosasUtiles.fecha
import mx.tecnm.tepic.ladm_u1_practica2_almacenamientodearchivosplanos.cosasUtiles.paciente
import mx.tecnm.tepic.ladm_u1_practica2_almacenamientodearchivosplanos.cosasUtiles.servicio
import mx.tecnm.tepic.ladm_u1_practica2_almacenamientodearchivosplanos.databinding.FragmentGalleryBinding
import java.io.File
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.lang.Exception

class GalleryFragment : Fragment() {

    private var _binding: FragmentGalleryBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val galleryViewModel =
            ViewModelProvider(this).get(GalleryViewModel::class.java)

        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.guardar.setOnClickListener(){
            try {
                val intentoAbrir = InputStreamReader(requireActivity().openFileInput("pacienteC.txt"))
            }catch (e:Exception){
                val pacientes = OutputStreamWriter(requireActivity().openFileOutput("pacienteC.txt",0))
                pacientes.flush()
                pacientes.close()
            }
            try{
                val intentoAbrir = InputStreamReader(requireActivity().openFileInput("pacienteC.txt"))
                var listaContenido = intentoAbrir.readLines()
                if (listaContenido.size==15){
                    AlertDialog.Builder(requireContext())
                        .setMessage("SE HA PASADO DEL LIMITE DE CITAS").show()
                    binding.pacienteText.setText("")
                    binding.servicioText.setText("")
                    binding.fechaText.setText("")
                    binding.costoText.setText("")
                }else {
                    for (i in 0..listaContenido.size - 1) {
                        var parts = listaContenido[i].split("|")
                        paciente[i] = parts[0]
                        servicio[i] = parts[1]
                        fecha[i] = parts[2]
                        costo[i] = parts[3]
                    }
                    val pacientes =
                        OutputStreamWriter(requireActivity().openFileOutput("pacienteC.txt", 0))
                    for (i in 0..listaContenido.size - 1) {
                        pacientes.write(paciente[i] + "|" + servicio[i] + "|" + fecha[i] + "|" + costo[i] + "\n")
                    }
                    pacientes.write(
                        binding.pacienteText.text.toString() + "|" + binding.servicioText.text.toString() + "|" + binding.fechaText.text.toString()
                                + "|" + binding.costoText.text.toString() + "\n"
                    )
                    paciente[listaContenido.size] = binding.pacienteText.text.toString()
                    servicio[listaContenido.size] = binding.servicioText.text.toString()
                    fecha[listaContenido.size] = binding.fechaText.text.toString()
                    costo[listaContenido.size] = binding.costoText.text.toString()
                    pacientes.flush()
                    pacientes.close()
                    AlertDialog.Builder(requireContext())
                        .setMessage("CITA AGENDADA").show()
                    binding.pacienteText.setText("")
                    binding.servicioText.setText("")
                    binding.fechaText.setText("")
                    binding.costoText.setText("")
                }
            }catch (e:Exception){
                AlertDialog.Builder(requireContext())
                    .setMessage(e.message).show()
            }

        }
        return root
    }
    private fun guardarArchivo(){


    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}