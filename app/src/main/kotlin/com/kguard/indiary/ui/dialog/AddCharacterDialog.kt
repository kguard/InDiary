package com.kguard.indiary.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.kguard.indiary.R
import com.kguard.indiary.databinding.DialogAddCharacterBinding
import com.kguard.indiary.util.Utils
import com.kguard.indiary.presentation.viewmodel.MainViewModel

/**
 * todo character
 */
class AddCharacterDialog:DialogFragment() {
    private lateinit var binding: DialogAddCharacterBinding
    private val mainViewModel: MainViewModel by activityViewModels()
    override fun onStart() {
        super.onStart()
        if(dialog != null && activity !=null && isAdded){
            val fullWidth= Utils.getScreenWidth(requireActivity()) * .9
            dialog?.window?.setLayout(fullWidth.toInt(),ViewGroup.LayoutParams.WRAP_CONTENT)
            //dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=DataBindingUtil.inflate(inflater, R.layout.dialog_add_character,container,false)

//        binding.btAddCharacterConfirm.setOnClickListener {
//            mainViewModel.setCustomCharacter(CustomCharacter(binding.etAddCharacterName.editText?.text.toString(),
//                binding.etAddCharacterContents.editText?.text.toString()))
//            dismiss()
//        }
//        binding.btAddCharacterCancel.setOnClickListener {
//            dismiss()
//        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}