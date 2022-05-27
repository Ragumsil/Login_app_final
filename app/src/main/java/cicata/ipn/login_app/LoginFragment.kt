package cicata.ipn.login_app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import cicata.ipn.login_app.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonCrearCuenta.setOnClickListener {
            binding.buttonCrearCuenta.setOnClickListener {
                findNavController().navigate(R.id.action_LoginFragment_to_RegisterFragment)}
        }

        //Se agrega el listener para el action de mostrar el mapa
        //binding.buttonIniciarSesion.setOnClickListener {
         //TODO: agregar validación del nombre de usuario y contraseña ()
          //  findNavController().navigate(R.id.action_LoginFragment_to_MapsFragment2)}

        var firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
        _binding?.buttonIniciarSesion?.setOnClickListener {
            if (_binding?.correoLogin?.text!!.isNotEmpty() && _binding?.passwordLogin?.text!!.isNotEmpty()) {

                firebaseAuth.signInWithEmailAndPassword(
                    _binding?.correoLogin?.text.toString(),
                    _binding?.passwordLogin?.text.toString()
                ).addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(context, "Usuario registrado", Toast.LENGTH_LONG).show()
                        findNavController().navigate(R.id.action_LoginFragment_to_MapsFragment2)
                    } else {
                        Toast.makeText(
                            context,
                            "No se pudo encontrar al usuario",
                            Toast.LENGTH_LONG
                        ).show()
                    }

                }
            }

        }

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}