package com.kguard.indiary

import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.esafirm.imagepicker.features.*
import com.kguard.indiary.data.CustomCharacter
import com.kguard.indiary.databinding.CharacterAddDialogBinding
import com.kguard.indiary.viewmodel.MainViewModel

class AddCharacterDialog:DialogFragment() {
    private lateinit var binding: CharacterAddDialogBinding
    private val mainViewModel: MainViewModel by activityViewModels()
    override fun onStart() {
        super.onStart()
        if(dialog != null && activity !=null && isAdded){
            val fullWidth=Utils.getScreenWidth(requireActivity()) * .9
            dialog?.window?.setLayout(fullWidth.toInt(),ViewGroup.LayoutParams.WRAP_CONTENT)
            //dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=DataBindingUtil.inflate(inflater,R.layout.character_add_dialog,container,false)

        binding.btAddCharacterConfirm.setOnClickListener {
            mainViewModel.setCustomCharacter(CustomCharacter(binding.etAddCharacterName.editText?.text.toString(),
                binding.etAddCharacterContents.editText?.text.toString()))
            dismiss()
        }
        binding.btAddCharacterCancel.setOnClickListener {
            dismiss()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}