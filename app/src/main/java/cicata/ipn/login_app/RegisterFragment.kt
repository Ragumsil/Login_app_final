package cicata.ipn.login_app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import cicata.ipn.login_app.databinding.FragmentRegisterBinding
import android.widget.Toast
import androidx.navigation.ui.AppBarConfiguration
import cicata.ipn.login_app.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class RegisterFragment : Fragment() {
    // definimos un atributo
    //la diferencia del binding con _ tiene que ver con el acceso
    // que se le pueden dar a los atributos privados
    // además de acceder a las variables dentro de las funciones

    private var _binding: FragmentRegisterBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding?.buttonSecond?.setOnClickListener {
            findNavController().navigate(R.id.action_RegisterFragment_to_LoginFragment)

        }

        var firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
        _binding?.buttonCrearCuenta2?.setOnClickListener {
            if (_binding?.editEmail2?.text!!.isNotEmpty() && _binding?.textoPassword2?.text!!.isNotEmpty()) {

                firebaseAuth.createUserWithEmailAndPassword(
                    _binding?.editEmail2?.text.toString(),
                    _binding?.editPassword2?.text.toString()
                ).addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(context , "Registro adecuado", Toast.LENGTH_LONG).show()
                        findNavController().navigate(R.id.action_RegisterFragment_to_LoginFragment)
                    }
                    else {
                        Toast.makeText(context, "No se pudo registrar al usuario", Toast.LENGTH_LONG).show()
                    }

                }
            }
        }
    }



    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()

    }
}