package com.kguard.indiary

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.kguard.indiary.databinding.FragmentDetailBinding


class DetailFragment : Fragment() {
    private val binding by lazy { FragmentDetailBinding.inflate(layoutInflater) }
    private val args by navArgs<DetailFragmentArgs>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.DetailInclude.tvToolbarName.text=getString(R.string.DetailPage)
        binding.viewPager2.adapter = DetailPagerAdapter(requireActivity())
        TabLayoutMediator(binding.tabLayout,binding.viewPager2){
            tab,position ->
            when(position)
            {
                0-> tab.text=getString(R.string.Character)
                1-> tab.text=getString(R.string.Memory)
            }
        }.attach()
    }

    private inner class DetailPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int {
            return 2
        }

        override fun createFragment(position: Int): Fragment {
            return when(position)
            {
                0->DetailPersonFragment(args.personId)
                else->DetailMemoryFragment()
            }
        }

    }


}