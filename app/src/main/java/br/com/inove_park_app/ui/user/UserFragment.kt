package br.com.inove_park_app.ui.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import br.com.inove_park_app.R
import br.com.inove_park_app.data.Login
import br.com.inove_park_app.data.User
import br.com.inove_park_app.extension.USER_PREFERENCES
import br.com.inove_park_app.extension.putStringPreferences
import br.com.inove_park_app.extension.showShortToast
import br.com.inove_park_app.util.LayoutUtil
import br.com.inove_park_app.util.ValidatorFieldBuilder
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_user.*

class UserFragment : Fragment() {

    private lateinit var viewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        return inflater.inflate(R.layout.fragment_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buttonSave.setOnClickListener {
            val success = validateField()
            if (success) {
                showShortToast(LayoutUtil.getString(R.string.success_register))
                saveUser()
            }
        }
    }

    private fun saveUser() {
        val name = editTextName.text.toString()
        val phone = editTextPhone.text.toString()
        val birthday = editTextBirthday.getData()
        val user = User(Login(), name, phone, birthday)
        val json = Gson().toJson(user)
        context?.putStringPreferences(USER_PREFERENCES, json)
    }

    private fun validateField(): Boolean {
        return ValidatorFieldBuilder()
            .requiredField(editTextName)
            .requiredField(editTextPhone)
            .requiredField(editTextBirthday)
            .build()
    }
}