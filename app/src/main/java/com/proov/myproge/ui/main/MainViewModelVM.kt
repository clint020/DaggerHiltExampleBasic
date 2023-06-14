package com.proov.myproge.ui.main
/* Selles klassis luuakse ja haldab ViewModel andmeid, mida teie vaade (activity või fragment) saab jälgida.
 Seda klassi haldab Hilt ja see kasutab süstitavat sõltuvust DateApi-st,
 et teha võrgupäring. LiveData ja MutableLiveData kasutatakse andmete hoidmiseks,
  mida vaade saab jälgida. viewModelScope kasutatakse korutina haldamiseks,
  et tagada, et võrgupäringud peatatakse, kui ViewModel hävitatakse.
* */
// Sisse tuuakse teegid ja klassid, mida kasutatakse ViewModeli loomisel.
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.proov.myproge.data.api.DateApi
import com.proov.myproge.data.api.DateResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

// @HiltViewModel annotatsioon tähistab ViewModeli, mida Hilt haldab ja kuhu Hilt süstib sõltuvused.
@HiltViewModel
class MainViewModelVM @Inject constructor(
    // @Inject annotatsiooniga süstitakse sõltuvus DateApi-st automaatselt ViewModelisse.
    private val api: DateApi
) : ViewModel() {

    // _date on Mutable LiveData, millele saab väärtusi määrata.
    private val _date: MutableLiveData<DateResponse> = MutableLiveData()
    // date on avalik LiveData, mille vaatlejad saavad jälgida.
    val date: LiveData<DateResponse> = _date

    // init plokk käivitatakse automaatselt pärast objekti loomist.
    init {
        // Käivitatakse funktsioon võrgust andmete laadimiseks.
        loadDataFromNetwork()
    }

    // Funktsioon võrgust andmete laadimiseks.
    private fun loadDataFromNetwork() {
        // Käivitatakse Coroutine ViewModeli tööüksuses.
        viewModelScope.launch(Dispatchers.IO) {
            try {
                // Logi teade, et alustatakse päringut API-st.
                Log.d("API_CALL", "Fetching date from the API")
                // Tehakse API päring ning tulemus määratakse _date muutujale.
                val response = api.getDate()
                _date.postValue(response)
            } catch (e: Exception) {
                // Kui tekib viga, logitakse see ning _date muutujale määratakse veateade.
                Log.e("API_ERROR", "Error occurred while fetching date: ${e.localizedMessage}")
                _date.postValue(DateResponse(date = "Error occurred"))
            }
        }
    }
}

