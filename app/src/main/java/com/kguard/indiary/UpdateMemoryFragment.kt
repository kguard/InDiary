package com.kguard.indiary

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.util.Pair
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.esafirm.imagepicker.features.ImagePickerConfig
import com.esafirm.imagepicker.features.ImagePickerMode
import com.esafirm.imagepicker.features.registerImagePicker
import com.google.android.material.datepicker.MaterialDatePicker
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.normal.TedPermission
import com.kguard.domain.domain.DomainMemory
import com.kguard.indiary.adapter.PhotoAdapter
import com.kguard.indiary.databinding.FragmentUpdateMemoryBinding
import com.kguard.indiary.viewmodel.MainViewModel
import com.kguard.indiary.viewmodel.UpdateMemoryViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpdateMemoryFragment() : Fragment() {
    private val args by navArgs<UpdateMemoryFragmentArgs>()
    private lateinit var binding: FragmentUpdateMemoryBinding
    private val viewModel: UpdateMemoryViewModel by viewModels()
    private val mainViewModel: MainViewModel by activityViewModels()
    private var adapter = PhotoAdapter {
        viewModel.removePhotoByPosition(it)
    }.apply { setHasStableIds(true) }

    private val imagePickerLauncher = registerImagePicker {
        val firstImage = it.firstOrNull() ?: return@registerImagePicker
        viewModel.setPhoto(firstImage.uri.toString())
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_update_memory, container, false)
        viewModel.getMemory(args.memoryId)
        viewModel.memory.observe(viewLifecycleOwner) { it ->
            binding.etUpdateMemoryTitle.editText?.setText(it.title)
            binding.tvUpdateDate.text = it.date
            binding.etUpdateMemoryContent.setText(it.content)
            binding.rvUpdatePhoto.adapter = adapter
            it.imageList.forEach {
                if (it != null) {
                    viewModel.setPhoto(it)
                }
            }
            it.person_id?.let { it1 -> viewModel.getPerson(it1) }
            viewModel.person.observe(viewLifecycleOwner) {
                binding.tvWithShow2.text = it.name
                mainViewModel.setPerson(it)
            }
        }

        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val memory = DomainMemory(memory_id = args.memoryId)
        val dateRangePicker =
            MaterialDatePicker.Builder.dateRangePicker()
                .setTitleText("날짜를 설정해주세요")
                .setSelection(
                    Pair(
                        MaterialDatePicker.thisMonthInUtcMilliseconds(),
                        MaterialDatePicker.todayInUtcMilliseconds()
                    )
                ).build()

        viewModel.photos.observe(viewLifecycleOwner) { photos ->
            adapter.updatePhoto(photos)
            if (photos.size >= 5) {
                binding.btUpdateMemoryPhoto.visibility = View.INVISIBLE
            } else if (photos.size < 5) {
                binding.btUpdateMemoryPhoto.visibility = View.VISIBLE
            }
        }

        binding.btUpdateWith.setOnClickListener {
            findNavController().navigate(R.id.action_updateMemoryFragment_to_memoryWithDialog)
        }
        mainViewModel.person.observe(viewLifecycleOwner){
            if (it != null) {
                binding.tvWithShow2.text = it.name
            }
        }


        binding.btUpdateMemoryDate.setOnClickListener {
            dateRangePicker.show(childFragmentManager, "datePicker")
            dateRangePicker.addOnPositiveButtonClickListener {
                memory.date = dateRangePicker.headerText
                binding.tvUpdateDate.text = memory.date
            }
        }

        binding.btUpdateMemoryPhoto.setOnClickListener {
            val permissionChecker: PermissionListener = object : PermissionListener {
                override fun onPermissionGranted() {
                    val config = ImagePickerConfig(
                        mode = ImagePickerMode.SINGLE,
                        theme = R.style.ImagePickerTheme
                    )
                    imagePickerLauncher.launch(config)
                }

                override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {
                    Toast.makeText(context, "권한을 주세요", Toast.LENGTH_SHORT).show()
                }

            }
            TedPermission.create()
                .setPermissionListener(permissionChecker)
                .setDeniedMessage("권한 설정 해주세요")
                .setPermissions(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                .check()

        }

        binding.btUpdateMemoryComplete.setOnClickListener {
            if (binding.etUpdateMemoryTitle.editText?.text == null) {
                binding.etUpdateMemoryTitle.error = getString(R.string.TitleEmptyError)
            } else {
                binding.etUpdateMemoryTitle.isErrorEnabled=false
                memory.title = binding.etUpdateMemoryTitle.editText?.text.toString()
                memory.content = binding.etUpdateMemoryContent.text.toString()
                memory.date = binding.tvUpdateDate.text.toString()
                mainViewModel.person.observe(viewLifecycleOwner) {
                    if (it != null) {
                        memory.person_id = it.person_id
                    }
                }
                viewModel.photos.value?.forEach {
                    memory.imageList.add(it)
                }
                if (memory.imageList.size < 5) {
                    for (i in 1..(5 - memory.imageList.size))
                        memory.imageList.add(null)
                }
                viewModel.updateMemory(memory)
                findNavController().popBackStack()
            }
        }
        mainViewModel.person.value = null
    }


}