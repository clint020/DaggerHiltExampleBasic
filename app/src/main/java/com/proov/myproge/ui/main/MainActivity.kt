package com.proov.myproge.ui.main

// Tuuakse sisse teie projekti teised klassid ja teegid.
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.proov.myproge.R
import com.proov.myproge.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

// @AndroidEntryPoint annotatsioon annab Daggeri/Hilti raamistikule teada, et see klass võib nõuda sõltuvuste süstimist.
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    // 'lateinit var' kasutatakse muutuja jaoks, mis on hiljem initialiseeritud, kuid enne kasutamist kindlasti määratud.
    // ActivityMainBinding genereeritakse automaatselt teie layout XML-failist.
    private lateinit var binding: ActivityMainBinding
    // viewModels delegaat loob ViewModeli eksemplari, kui seda vajatakse ja hävitab selle kui teie aktiivsus hävitatakse.
    private val viewModel: MainViewModelVM by viewModels()

    // onCreate() on elutsükli meetod, mis kutsutakse välja kui aktiivsus luuakse.
    override fun onCreate(savedInstanceState: Bundle?) {
        // Alati tuleks esmalt kutsuda välja vanema klassi vastav meetod.
        super.onCreate(savedInstanceState)

        // Andmesidumise seadistamine. Andmesidumine võimaldab otse XML vaadetele viidata oma ViewModeli või andmeklassi atribuutide kaudu.
        // DataBindingUtil.setContentView() funktsioon tagastab ActivityMainBinding eksemplari, mis sisaldab kõiki vaateid teie layout'is.
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        // Määrame andmesidumisele elutsükli omaniku. See võimaldab andmesidumisel automaatselt jälgida LiveData muutujaid.
        binding.lifecycleOwner = this
        // Seame ViewModeli. See võimaldab andmesidumisel kasutada ViewModeli muutujaid.
        binding.viewModel = viewModel
    }
}

