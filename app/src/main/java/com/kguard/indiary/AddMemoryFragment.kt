package com.kguard.indiary

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.util.Pair
import androidx.fragment.app.*
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.esafirm.imagepicker.features.ImagePickerConfig
import com.esafirm.imagepicker.features.ImagePickerMode
import com.esafirm.imagepicker.features.registerImagePicker
import com.google.android.material.datepicker.MaterialDatePicker
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.normal.TedPermission
import com.kguard.domain.domain.DomainMemory
import com.kguard.indiary.adapter.PhotoAdapter
import com.kguard.indiary.databinding.FragmentAddMemoryBinding
import com.kguard.indiary.viewmodel.AddMemoryViewModel
import com.kguard.indiary.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AddMemoryFragment : Fragment() {
    private val binding by lazy { FragmentAddMemoryBinding.inflate(layoutInflater) }
    private val viewModel: AddMemoryViewModel by viewModels()
    private val mainViewModel: MainViewModel by activityViewModels()
    private var memory = DomainMemory()
    private var adapter = PhotoAdapter {
        viewModel.removePhotoByPosition(it)
    }.apply { setHasStableIds(true) }
    private val imagePickerLauncher = registerImagePicker { it ->
        val firstImage = it.firstOrNull() ?: return@registerImagePicker
        viewModel.setPhoto(firstImage.uri.toString())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding.rvAddPhoto.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        val dateRangePicker =
            MaterialDatePicker.Builder.dateRangePicker()
                .setTitleText("날짜를 설정해주세요")
                .setSelection(
                    Pair(
                        MaterialDatePicker.thisMonthInUtcMilliseconds(),
                        MaterialDatePicker.todayInUtcMilliseconds()
                    )
                ).build()


        mainViewModel.person.observe(viewLifecycleOwner, Observer {
            binding.tvWithShow.text = it?.name
            if(it == null)
            {
                binding.tvWithShow.text = "With"
            }
        })

        binding.btAddWith.setOnClickListener {
            findNavController().navigate(R.id.action_addMemoryFragment_to_memoryDialog)
        }

        binding.btAddMemoryDate.setOnClickListener {
            dateRangePicker.show(childFragmentManager, "datePicker")
            dateRangePicker.addOnPositiveButtonClickListener {
                memory.date = dateRangePicker.headerText
                binding.tvAddDate.text = memory.date
            }
        }

        binding.btAddMemoryPhoto.setOnClickListener {
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

        viewModel.photos.observe(viewLifecycleOwner) { photos ->
            adapter.updatePhoto(photos)
            if (photos.size >= 5) {
                binding.btAddMemoryPhoto.visibility = View.INVISIBLE
            }
            else if(photos.size < 5)
            {
                binding.btAddMemoryPhoto.visibility = View.VISIBLE
            }
        }

        binding.btAddMemoryComplete.setOnClickListener {
            memory.title = binding.etAddMemoryTitle.editText?.text.toString()
            memory.content = binding.etAddMemoryContent.text.toString()
            mainViewModel.person.observe(viewLifecycleOwner, Observer {
                if (it != null) {
                    memory.person_id = it.person_id
                }
            })
            mainViewModel.person.value=null
            viewModel.photos.value?.forEach {
                memory.imageList.add(it)
            }
            if (memory.imageList.size < 5)
            {
                for(i in 1..(5 - memory.imageList.size))
                    memory.imageList.add(null)
            }
            viewModel.insertMemory(memory)
            findNavController().popBackStack()
        }

    }

}