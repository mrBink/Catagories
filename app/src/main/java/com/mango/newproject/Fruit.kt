package com.mango.newproject

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat

class Fruit : AppCompatActivity() {
    private lateinit var toFruits:ImageButton
    private lateinit var numbers:ImageButton
    private lateinit var eBody:ImageButton
    private lateinit var engmonths:ImageButton
    private lateinit var engTime:ImageButton
    private lateinit var toCashth:ImageButton
    private lateinit var engweatherbtn:ImageButton
    private lateinit var transeng:ImageButton
    private lateinit var where:ImageButton
    private lateinit var engmailstart:ImageButton
    private lateinit var startfamilyeng:ImageButton
    private lateinit var elocal_icon2:ImageButton
    private lateinit var ework:ImageButton
    private lateinit var everbstart:ImageButton
    private lateinit var eadjicon:ImageButton
    private lateinit var verbseng2:ImageButton
    private lateinit var enganimalicon:ImageButton
    private lateinit var englishpronoun:ImageButton
    private lateinit var engclothes:ImageButton
    private lateinit var engcoloricon:ImageButton
    private lateinit var enguseicon:ImageButton
    private lateinit var medicineicon:ImageButton
    private lateinit var enounadjicon:ImageButton
    private lateinit var ambleicon:ImageButton
    private lateinit var engnationsBtn:ImageButton
    private lateinit var bathroomiconE:ImageButton
    private lateinit var kiticon:ImageButton
    private lateinit var bedroomicon:ImageButton
    private lateinit var shoppingicon:ImageButton
    private lateinit var engpluralicon:ImageButton


    /*
    private lateinit var engnationsBtn:ImageButton
    private lateinit var holdStoresBTN:ImageButton
    private lateinit var engHoldToolsBTN:ImageButton
    private lateinit var engHoldBathBTN:ImageButton
    private lateinit var engHoldKitchenBTN:ImageButton
    private lateinit var bedroomicon:ImageButton
    private lateinit var engHoldHouseBTN:ImageButton
    private lateinit var engHoldVeggieBTN:ImageButton
    private lateinit var shoppingicon:ImageButton
    private lateinit var engpluralicon:ImageButton
    private lateinit var engHoldWordBTN:ImageButton
    private lateinit var EngVocabNamesBTN:ImageButton
    private lateinit var EngONamesBTN:ImageButton
    private lateinit var EngRNamesBTN:ImageButton
    private lateinit var EngDNamesBTN:ImageButton
    private lateinit var EngSNamesBTN:ImageButton
    private lateinit var EngTNamesBTN:ImageButton
    private lateinit var EngEENamesBTN:ImageButton
    private lateinit var EngRRNamesBTN:ImageButton
    private lateinit var EngMMNamesBTN:ImageButton
    private lateinit var EngYellowSNamesBTN:ImageButton

     */




    //  private lateinit var EngYellowSNamesBTN:ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowInsetsControllerCompat(window, window.decorView).apply {
            hide(WindowInsetsCompat.Type.statusBars())
        }
        setContentView(R.layout.activity_fruit)
        toFruits = findViewById(R.id.toFruits)
        numbers = findViewById(R.id.numbers)
        eBody = findViewById(R.id.eBody)
        engmonths = findViewById(R.id.engmonths)
        engTime = findViewById(R.id.engTime)
        toCashth = findViewById(R.id.toCashth)
        engweatherbtn = findViewById(R.id.engweatherbtn)
        transeng = findViewById(R.id.transeng)
        where = findViewById(R.id.where)
        engmailstart = findViewById(R.id.engmailstart)
        startfamilyeng= findViewById(R.id.startfamilyeng)
        elocal_icon2 = findViewById(R.id.elocal_icon2)
        ework = findViewById(R.id.ework)
        everbstart = findViewById(R.id.everbstart)
        eadjicon =  findViewById(R.id.eadjicon)
        verbseng2 = findViewById(R.id.verbseng2)
        enganimalicon = findViewById(R.id.enganimalicon)
        englishpronoun = findViewById(R.id.englishpronoun)
        engclothes = findViewById(R.id.engclothes)
        engcoloricon = findViewById(R.id.engcoloricon)
        enguseicon = findViewById(R.id.enguseicon)
        medicineicon= findViewById(R.id.medicineicon)
        enounadjicon = findViewById(R.id.enounadjicon)
        ambleicon = findViewById(R.id.ambleicon)
        engnationsBtn = findViewById(R.id.engnationsBtn)
        bathroomiconE = findViewById(R.id.bathroomiconE)
        kiticon = findViewById(R.id.kiticon)
        bedroomicon = findViewById(R.id.bedroomicon)
        shoppingicon = findViewById(R.id.shoppingicon)
        engpluralicon = findViewById(R.id.engpluralicon)
        /*
        holdStoresBTN = findViewById(R.id.holdStoresBTN)
        engHoldToolsBTN = findViewById(R.id.engHoldToolsBTN)

        kiticon = findViewById(R.id.kiticon)
        bedroomicon = findViewById(R.id.bedroomicon)
        engHoldHouseBTN = findViewById(R.id.engHoldHouseBTN)
        engHoldVeggieBTN = findViewById(R.id.engHoldVeggieBTN)
        shoppingicon = findViewById(R.id.shoppingicon)
        engpluralicon = findViewById(R.id.engpluralicon)
        engHoldWordBTN = findViewById(R.id.engHoldWordBTN)
        EngVocabNamesBTN = findViewById(R.id.EngVocabNamesBTN)
        EngONamesBTN = findViewById(R.id.EngONamesBTN)
        EngRNamesBTN = findViewById(R.id.EngRNamesBTN)
        EngDNamesBTN = findViewById(R.id.EngDNamesBTN)
        EngSNamesBTN = findViewById(R.id.EngSNamesBTN)
        EngTNamesBTN = findViewById(R.id.EngTNamesBTN)
        EngEENamesBTN = findViewById(R.id.EngEENamesBTN)
        EngRRNamesBTN = findViewById(R.id.EngRRNamesBTN)
        EngMMNamesBTN = findViewById(R.id.EngMMNamesBTN)
        EngYellowSNamesBTN = findViewById(R.id.EngYellowSNamesBTN)

        //EngYellowSNamesBTN = findViewById(R.id.EngYellowSNamesBTN)
       */
        toFruits.setOnClickListener {
            //println("toFruits button clicked should see action fruit spell eng page")
            val intent = Intent(this, FruitCat::class.java)
            startActivity(intent)
        }

        numbers.setOnClickListener {
            //println("numbers button clicked should see action  num  eng page")
           val intent1 = Intent(this, ArabicNum::class.java)
            startActivity(intent1)
        }

        eBody.setOnClickListener {
            //println("numbers   eBody clicked should see action bodyPartsThai")
           val intent2 = Intent(this, AnatomyE::class.java)
            startActivity(intent2)
        }

         engmonths.setOnClickListener {
           // println("numbers   eBody clicked should see action EnglishMonths")
           val intent3 = Intent(this, EnglishMonths::class.java)
            startActivity(intent3)
        }

        engTime.setOnClickListener {
            //println("numbers   eBody clicked should see action EnglishMonths")
           val intent4 = Intent(this, SpellInEnglish::class.java)
            startActivity(intent4)
        }

        toCashth.setOnClickListener {
            //println("numbers   eBody clicked should see action EnglishMonths")
           val intent5 = Intent(this, MoneyThai::class.java)
            startActivity(intent5)
        }

        engweatherbtn.setOnClickListener {
            //println("engWeatherStartBtn clicked should see action ClimateWordsEng")
           val intent6 = Intent(this, ClimateWordsEng::class.java)
            startActivity(intent6)
        }

        transeng.setOnClickListener {
            //println("transengStartBtn clicked should see action TransportEnglish")
           val intent7 = Intent(this, TransportEnglish::class.java)
            startActivity(intent7)
        }
        where.setOnClickListener {
            //println("transengStartBtn clicked should see action TransportEnglish")
            val intent8 = Intent(this, Compass::class.java)
            startActivity(intent8)
        }

        engmailstart.setOnClickListener {
            //println("engmailstart clicked should see action EnglishMail")
            val intent9 = Intent(this, EnglishMail::class.java)
            startActivity(intent9)
        }
        startfamilyeng.setOnClickListener {
            println("toCash2 clicked should see action startfamilyeng")
            val intent15 = Intent(this, Family::class.java)
            startActivity(intent15)
        }
        toCashth.setOnClickListener {
            //println("toCashth2 clicked should see action Family")
            val intent10 = Intent(this, Money::class.java)
            startActivity(intent10)
        }

        elocal_icon2.setOnClickListener {
            //println("toCashth2 clicked should see action Places")
            val intent11 = Intent(this, PlacesEng::class.java)
            startActivity(intent11)
        }

        ework.setOnClickListener {
            println("ThaiProfession clicked should see action class ThaiProfession")
            val intent18 = Intent(this,EngProfession::class.java)
            startActivity(intent18)
        }

        everbstart.setOnClickListener {
            println("eVerbStart clicked should see action class EngAction")
            val intent19 = Intent(this,EngAction::class.java)
            startActivity(intent19)
        }

        eadjicon.setOnClickListener {
            println("eAdjImage clicked should see action class EngModifier")
            val intent20 = Intent(this,EngModifier::class.java)
            startActivity(intent20)
        }

        verbseng2.setOnClickListener {
            println("MoreEngVerbClass clicked should see action class MoreEngVerbClass")
            val intent21 = Intent(this,MoreEngVerbClass::class.java)
            startActivity(intent21)
        }

        enganimalicon.setOnClickListener {
            println("containCritterPics clicked should see action class AnimalsMasterClass")
            val intent22 = Intent(this,AnimalsMasterClass::class.java)
            startActivity(intent22)
        }
        englishpronoun.setOnClickListener {
            println("pronounPicHolder clicked should see action class AllEnglishPronouns")
            val intent23 = Intent(this,AllEnglishPronouns::class.java)
            startActivity(intent23)
        }
        engclothes.setOnClickListener {
            println("clothPics clicked should see action in ClothingClass")
            val intent24 = Intent(this,ClothingClass::class.java)
            startActivity(intent24)
        }
        engcoloricon.setOnClickListener {
            println("ecolourBTN clicked should see action in ColourClass")
            val intent25 = Intent(this,ColourClass::class.java)
            startActivity(intent25)
        }
        enguseicon.setOnClickListener {
            println("EutilityBTN clicked should see action in EnglishUtility")
            val intent26 = Intent(this,EnglishUtility::class.java)
            startActivity(intent26)
        }

        medicineicon.setOnClickListener {
            println("engMedBtn clicked should see action in MedAids")
            val intent27 = Intent(this,MedAids::class.java)
            startActivity(intent27)
        }

        enounadjicon.setOnClickListener {
            println("engNounsBtn clicked should see action in TwoEngWords")
            val intent28 = Intent(this,TwoEngWords::class.java)
            startActivity(intent28)
        }

        ambleicon.setOnClickListener {
            println("engAmbleiconBtn clicked should see action in EnglishAmble")
            val intent29 = Intent(this,EnglishAmble2::class.java)
            startActivity(intent29)
        }

        engnationsBtn.setOnClickListener {
            println("engnationsBtn clicked should see action in EnglishCountry")
            val intent30 = Intent(this,EnglishCountry::class.java)
            startActivity(intent30)
        }
        bathroomiconE.setOnClickListener {
            println("bathroomiconE clicked should see action in EnglishBathroom")
            val intent31 = Intent(this,EnglishBathroom::class.java)
            startActivity(intent31)
        }
        kiticon.setOnClickListener {
            println("kiticon clicked should see action in EnglishKitchen")
            val intent32 = Intent(this, EnglishKitchen::class.java)
            startActivity(intent32)
        }

        bedroomicon.setOnClickListener {
            println("bedroomicon clicked should see action in EnglishBedroom")
            val intent33 = Intent(this, EnglishBedroom::class.java)
            startActivity(intent33)
        }

        shoppingicon.setOnClickListener {
            println("shoppingicon clicked should see action in EnglishShopping")
            val intent34 = Intent(this, EnglishRetail::class.java)
            startActivity(intent34)
        }
        engpluralicon.setOnClickListener {
            println("engpluralicon clicked should see action in EnglishPlurals")
            val intent35 = Intent(this, EnglishPlurals::class.java)
            startActivity(intent35)
        }
        /*
        holdStoresBTN.setOnClickListener {
            println("holdStoresBTN clicked should see action in EnglishRetail")
            val intent31 = Intent(this,EnglishRetail::class.java)
            startActivity(intent31)
        }
        engHoldToolsBTN.setOnClickListener {
            println("engHoldToolsBTN clicked should see action in EnglishTools")
            val intent32 = Intent(this,EnglishTools::class.java)
            startActivity(intent32)
        }

        bathroomiconE.setOnClickListener {
            println("bathroomiconE clicked should see action in EnglishBathroom")
            val intent33 = Intent(this,EnglishBathroom::class.java)
            startActivity(intent33)
        }
        kiticon.setOnClickListener {
            println("kiticon clicked should see action in EnglishKitchen")
            val intent32 = Intent(this, EnglishKitchen::class.java)
            startActivity(intent32)
        }
        bedroomicon.setOnClickListener {
            println("bedroomicon clicked should see action in EnglishBedroom")
            val intent33 = Intent(this, EnglishBedroom::class.java)
            startActivity(intent33)
        }
        engHoldHouseBTN.setOnClickListener {
            println("engHoldHouseBTN clicked should see action in EnglishHouse")
            val intent36 = Intent(this, EnglishHouse::class.java)
            startActivity(intent36)
        }
        engHoldVeggieBTN.setOnClickListener {
            println("engHoldVeggieBTN clicked should see action in EnglishVeggie")
            val intent37 = Intent(this, EnglishVeggie::class.java)
            startActivity(intent37)
        }
        shoppingicon.setOnClickListener {
            println("shoppingicon clicked should see action in EnglishShopping")
            val intent34 = Intent(this, EnglishShopping::class.java)
            startActivity(intent34)
        }
        engpluralicon.setOnClickListener {
            println("engpluralicon clicked should see action in EnglishPlurals")
            val intent35 = Intent(this, EnglishPlurals::class.java)
            startActivity(intent35)
        }
        engHoldWordBTN.setOnClickListener {
            println("engHoldWordBTN clicked should see action in EnglishWordsOne")
            val intent40 = Intent(this,EnglishWordsOne::class.java)
            startActivity(intent40)
        }
        EngVocabNamesBTN.setOnClickListener {
            println("EngVocabNamesBTN clicked should see action in EnglishWordW")
            val intent41 = Intent(this,EnglishWordW::class.java)
            startActivity(intent41)
        }
        EngONamesBTN.setOnClickListener {
            println("EngONamesBTN clicked should see action in EnglishWordO")
            val intent42 = Intent(this,EnglishWordO::class.java)
            startActivity(intent42)
        }
        EngRNamesBTN.setOnClickListener {
            println("EngRNamesBTN clicked should see action in EnglishWordR")
            val intent43 = Intent(this,EnglishWordR::class.java)
            startActivity(intent43)
        }
        EngDNamesBTN.setOnClickListener {
            println("EngDNamesBTN clicked should see action in EnglishWordD")
            val intent44 = Intent(this,EnglishWordD::class.java)
            startActivity(intent44)
        }
        EngSNamesBTN.setOnClickListener {
            println("EngSNamesBTN clicked should see action in EnglishWordS")
            val intent45 = Intent(this,EnglishWordS::class.java)
            startActivity(intent45)
        }
        EngTNamesBTN.setOnClickListener {
            println("EngTNamesBTN clicked should see action in EnglishBrownT")
            val intent46 = Intent(this,EnglishBrownT::class.java)
            startActivity(intent46)
        }
        EngEENamesBTN.setOnClickListener {
            println("EngEENamesBTN clicked should see action in EnglishPurpleE")
            val intent47 = Intent(this,EnglishPurpleE::class.java)
            startActivity(intent47)
        }
        EngRRNamesBTN.setOnClickListener {
            println("EngRRNamesBTN clicked should see action in EnglishBlueR")
            val intent48 = Intent(this,EnglishBlueR::class.java)
            startActivity(intent48)
        }
        EngMMNamesBTN.setOnClickListener {
            println("EngMMNamesBTN clicked should see action in EnglishREDM")
            val intent49 = Intent(this,EnglishREDM::class.java)
            startActivity(intent49)
        }
        EngYellowSNamesBTN.setOnClickListener {
            println("EngYellowSNamesBTN clicked should see action in EnglishYellowS")
            val intent50 = Intent(this,EnglishYellowS::class.java)
            startActivity(intent50)
        }

       */


    }
} //end of class  EngYellowSNamesBTN