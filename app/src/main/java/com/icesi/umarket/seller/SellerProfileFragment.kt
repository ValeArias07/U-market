package com.icesi.umarket.seller

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import com.icesi.umarket.MainActivity
import com.icesi.umarket.model.Seller
import com.icesi.umarket.util.Util
import com.icesi.umarket.databinding.FragmentSellerProfileBinding
import com.icesi.umarket.model.Market
import com.icesi.umarket.util.Constants

class SellerProfileFragment : Fragment() {

    /// View
    private lateinit var _binding: FragmentSellerProfileBinding
    private val binding get() = _binding!!

    /// Objects
    private lateinit var market: Market
    private lateinit var seller: Seller

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSellerProfileBinding.inflate(inflater, container, false)
        getMarketInfo()

        binding.logoutSellerBtn.setOnClickListener {
            Firebase.auth.signOut()
            startActivity(Intent(requireActivity(), MainActivity::class.java))
            requireActivity().finish()
        }

        binding.settingsSellerBtn.setOnClickListener {
            startActivity(
                Intent(requireActivity(), SellerEditProfileActivity::class.java).apply {
                    putExtra(Constants.userObj, Gson().toJson(seller))
                }
            )
        }

        return binding.root
    }

    private fun getMarketInfo(){
        Util.loadImage(market.imageID, binding.profilePhotoSeller, Constants.marketProfileImg)
        binding.nameMarket.text = market.marketName
        binding.emailSeller.text = seller.email
        binding.phoneSeller.text = market.phoneNumber
    }

    fun setUser(user: Seller) {
        seller = user
        Firebase.firestore.collection("markets").document(user.marketID).get()
            .addOnSuccessListener {
                market = it.toObject(Market::class.java)!!
            }
    }

    companion object {
        @JvmStatic
        fun newInstance() = SellerProfileFragment()
    }
}