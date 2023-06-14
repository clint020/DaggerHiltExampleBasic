// Paketi deklaratsioon. See ütleb teie programmile, kus see klass asub projekti kaustastruktuuris.
package com.proov.myproge.data.api

// Retrofit2 on võrgustikukõne raamistik, mis on mõeldud HTTP-päringute hõlpsamaks tegemiseks.
// Siin impordime me Retrofiti annotatsioonide abil HTTP GET päringu loomiseks.
import retrofit2.http.GET

// Defineerime liidese nimega "DateApi". Liides on klassi tüüp, mis sisaldab meetodite deklaratsioone, kuid mitte nende rakendamist.
// See on kasulik, kui soovite luua klassi, mis peab järgima teatud funktsionaalsuse skeemi, kuid mille rakendamine võib erinevatel juhtudel erineda.
interface DateApi {

    // Siin on kasutatud Retrofiti annotatsiooni "@GET". See ütleb Retrofitile, et see meetod peaks täitma HTTP GET päringut.
    // "getDate" on URL-i osa, mida GET päring kasutab. Täielik URL luuakse, lisades selle osa teie Retrofiti kliendi baas-URL-ile.
    @GET("getDate")
    // See on kohtkäivitatav funktsioon, mis tähendab, et seda saab käivitada ainult koht-korutiinide kontekstis.
    // Korutinaid kasutatakse asünkroonseks tööks, näiteks võrgukõnedeks, mis võivad võtta aega ja peaksid jooksma eraldi töölõngal, et mitte blokeerida kasutajaliidest.
    suspend fun getDate(): DateResponse
}
