package com.mango.newproject

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat

class FruitThai : AppCompatActivity() {
    private lateinit var toFruitt: ImageButton
    private lateinit var thaiDigit: ImageButton
    private lateinit var tBody: ImageButton
    private lateinit var t_calendar: ImageButton
    private lateinit var thaitime:ImageButton
    private lateinit var toCashth:ImageButton
    private lateinit var thweatherbtn:ImageButton
    private lateinit var transthai:ImageButton
    private lateinit var compassThStartBtn:ImageButton
    private lateinit var startThaiMail:ImageButton
    private lateinit var startThaiFamily: ImageButton
    private lateinit var venueBtn2:ImageButton
    private lateinit var tworking:ImageButton
    private lateinit var tVerbStarting:ImageButton
    private lateinit var eadjicons:ImageButton
    private lateinit var moreThaiVerb:ImageButton
    private lateinit var thAnimalicon:ImageButton
    private lateinit var thaiPronoun:ImageButton
    private lateinit var thaiClothes:ImageButton
    private lateinit var thaiColorIcon:ImageButton
    private lateinit var thaiUseicon:ImageButton
    private lateinit var anotherMedicon:ImageButton
    private lateinit var eNounAdjicon:ImageButton
    private lateinit var ambleiconcopy:ImageButton
    private lateinit var engnationsBtn:ImageButton
    private lateinit var bathroomiconT:ImageButton
    private lateinit var kiticon:ImageButton
    private lateinit var bedroomicon:ImageButton
    private lateinit var shoppingicon:ImageButton
    private lateinit var thpluralicon:ImageButton

   /*

    private lateinit var thHoldToolBTN:ImageButton
    private lateinit var bedroomicon:ImageButton
    private lateinit var thHoldHouseBtn:ImageButton
    private lateinit var thHoldVeggieBtn:ImageButton
    private lateinit var thHoldshoppingBtn:ImageButton
    private lateinit var thpluralicon:ImageButton
    private lateinit var thHoldWordBtn:ImageButton
    private lateinit var ThVocabNamesBTN:ImageButton
    private lateinit var ThONamesBTN:ImageButton
    private lateinit var ThRNamesBTN:ImageButton
    private lateinit var ThDNamesBTN:ImageButton
    private lateinit var ThSNamesBTN:ImageButton
    private lateinit var ThGNamesBTN:ImageButton
    private lateinit var ThAMNamesBTN:ImageButton
    private lateinit var ThHNamesBTN:ImageButton
    private lateinit var ThNNNamesBTN:ImageButton
    private lateinit var ThYellowDNamesBTN:ImageButton
    //private ThYellowDNamesBTN   private lateinit var ThYellowDNamesBTN:ImageButton

    */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowInsetsControllerCompat(window, window.decorView).apply {
            hide(WindowInsetsCompat.Type.statusBars())
        }
        setContentView(R.layout.activity_fruit_thai)
        toFruitt = findViewById(R.id.toFruitt)
        toFruitt.setOnClickListener {
            val intent7 = Intent(this, ThaiCats::class.java)
            startActivity(intent7)
        }

        thaiDigit = findViewById(R.id.thaiDigit)
        thaiDigit.setOnClickListener {
            // println("thaiDigit button clicked should see action spell ThaiNumbers  page")
            val intent6 = Intent(this, ThaiNumbers::class.java)
            startActivity(intent6)
        }
        tBody = findViewById(R.id.tBody)
        tBody.setOnClickListener {
            println("numbers button clicked should see action  num  tBody")
            val intent5 = Intent(this, AnatomyT::class.java)
            startActivity(intent5)
        }

        t_calendar = findViewById(R.id.t_calendar)
        t_calendar.setOnClickListener {
            println("numbers button clicked should see action  num  ThaiMonths")
            val intent9 = Intent(this, ThaiMonths::class.java)
            startActivity(intent9)
        }


        thaitime = findViewById(R.id.thaitime)
        thaitime.setOnClickListener {
            println("numbers button clicked should see action  num  ThaiMonths")
            val intent8 = Intent(this, SpellInThai::class.java)
            startActivity(intent8)
        }


        toCashth = findViewById(R.id.toCashth)
        toCashth.setOnClickListener {
            println("numbers button clicked should see action  num  ThaiMonths")
            val intent10 = Intent(this, MoneyThai::class.java)
            startActivity(intent10)
        }

        thweatherbtn = findViewById(R.id.thweatherbtn)
        thweatherbtn.setOnClickListener {
            println("numbers button clicked should see action ClimateWordsTH")
            val intent11 = Intent(this, ClimateWordsTH::class.java)
            startActivity(intent11)
        }

        transthai = findViewById(R.id.transthai)
        transthai.setOnClickListener {
            println("numbers button clicked should see action ClimateWordsTH")
            val intent12 = Intent(this, TransportThai::class.java)
            startActivity(intent12)
        }


        compassThStartBtn = findViewById(R.id.compassThStartBtn)
        compassThStartBtn.setOnClickListener {
            println("compassThStartBtn clicked should see action CompassThai")
            val intent13 = Intent(this, CompassThai::class.java)
            startActivity(intent13)
        }


        startThaiMail = findViewById(R.id.startThaiMail)
        startThaiMail.setOnClickListener {
            println("thmailstart clicked should see action ThaiMail")
            val intent14 = Intent(this, ThaiMail::class.java)
            startActivity(intent14)
        }

        startThaiFamily = findViewById(R.id.startThaiFamily)
        startThaiFamily.setOnClickListener {
            println("toCash2 clicked should see action FamilyTh")
            val intent15 = Intent(this, FamilyTh::class.java)
            startActivity(intent15)
        }

           venueBtn2 = findViewById(R.id.venueBtn2)
           venueBtn2.setOnClickListener {
               println("PlacesThai clicked should see action PlacesThai")
               val intent16 = Intent(this,PlacesThai::class.java)
               startActivity(intent16)
            }

        tworking = findViewById(R.id.tworking)
        tworking.setOnClickListener {
               println("PlacesThai clicked should see action ThaiProfessions")
               val intent17 = Intent(this,ThaiProfession::class.java)
               startActivity(intent17)
            }

        tVerbStarting = findViewById(R.id.tVerbStarting)
        tVerbStarting.setOnClickListener {
            println("tVerbStart clicked should see action class ThaiAction")
            val intent19 = Intent(this,ThaiAction::class.java)
            startActivity(intent19)
        }

        eadjicons = findViewById(R.id.eadjicons)
        eadjicons.setOnClickListener {
           println("ThaiModifier clicked should see action class ThaiModifier")
           val intent20 = Intent(this,ThaiModifier::class.java)
           startActivity(intent20)
       }

        moreThaiVerb = findViewById(R.id.moreThaiVerb)
        moreThaiVerb.setOnClickListener {
            println("thVerbExtraPic clicked should see action class MoreThaiVerbClass")
            val intent21 = Intent(this,MoreThaiVerbClass::class.java)
            startActivity(intent21)
        }

        thAnimalicon = findViewById(R.id.thAnimalicon)
        thAnimalicon.setOnClickListener {
            println("containThCritterPics clicked should see action class AnimalsMasterThai")
            val intent22 = Intent(this,AnimalsMasterThai::class.java)
            startActivity(intent22)
        }

        thaiPronoun = findViewById(R.id.thaiPronoun)
        thaiPronoun.setOnClickListener {
            println("thPronounPicHolder clicked should see action class AllThaiPronouns")
            val intent23 = Intent(this,AllThaiPronouns::class.java)
            startActivity(intent23)
        }
        thaiClothes = findViewById(R.id.thaiClothes)
        thaiClothes.setOnClickListener {
            println("thaiClothHolder clicked should see action class ThaiClothing")
            val intent24 = Intent(this,ThaiClothing::class.java)
            startActivity(intent24)
        }
        thaiColorIcon = findViewById(R.id.thaiColorIcon)
        thaiColorIcon.setOnClickListener {
            println("thcolourBTN clicked should see action class ThaiColourClass")
            val intent25 = Intent(this,ThaiColourClass::class.java)
            startActivity(intent25)
        }
        thaiUseicon = findViewById(R.id.thaiUseicon)
        thaiUseicon.setOnClickListener {
            println("thUtilityBTN clicked should see action class ThaiUtility")
            val intent26 = Intent(this,ThaiUtility::class.java)
            startActivity(intent26)
        }

        anotherMedicon = findViewById(R.id.anotherMedicon)
        anotherMedicon.setOnClickListener {
            println("thaiMedBtn clicked should see action class ThaiMedAids")
            val intent27 = Intent(this,ThaiMedAids::class.java)
            startActivity(intent27)
        }

        eNounAdjicon = findViewById(R.id.eNounAdjicon)
        eNounAdjicon.setOnClickListener {
            println("thNounsBtn clicked should see action class TwoThaiWords")
            val intent28 = Intent(this,TwoThaiWords::class.java)
            startActivity(intent28)
        }

        ambleiconcopy = findViewById(R.id.ambleiconcopy)
        ambleiconcopy.setOnClickListener {
            println("thAmbleiconBtn clicked should see action class ThaiAmble")
            val intent29 = Intent(this,ThaiAmble::class.java)
            startActivity(intent29)
        }


        engnationsBtn = findViewById(R.id.engnationsBtn)
        engnationsBtn.setOnClickListener {
            println("thAmbleiconBtn clicked should see action class ThaiCountry")
            val intent30 = Intent(this,ThaiCountry::class.java)
            startActivity(intent30)
        }

        bathroomiconT = findViewById(R.id.bathroomiconT)
        bathroomiconT.setOnClickListener {
            println("bathroomiconT clicked should see action class ThaiBathroom")
            val intent31 = Intent(this,ThaiBathroom::class.java)
            startActivity(intent31)
        }

        kiticon = findViewById(R.id.kiticon)
        kiticon.setOnClickListener {
            println("kiticon clicked should see action class ThaiKitchen")
            val intent32 = Intent(this,ThaiKitchen::class.java)
            startActivity(intent32)
        }

       bedroomicon = findViewById(R.id.bedroomicon)
       bedroomicon.setOnClickListener {
           println("thHoldBedroomBtn clicked should see action class ThaiBedroom")
           val intent33 = Intent(this,ThaiBedroom::class.java)
           startActivity(intent33)
       }

       shoppingicon = findViewById(R.id.shoppingicon)
       shoppingicon.setOnClickListener {
           println("shoppingicon clicked should see action class ThaiRetail")
           val intent34 = Intent(this,ThaiRetail::class.java)
           startActivity(intent34)
       }
       thpluralicon = findViewById(R.id.thpluralicon)
       thpluralicon.setOnClickListener {
           println("thHoldPluralsBtn clicked should see action class ThaiPlurals")
           val intent35 = Intent(this,ThaiPlurals::class.java)
           startActivity(intent35)
       }
        /*
        shoppingicon = findViewById(R.id.shoppingicon)
        shoppingicon.setOnClickListener {
            println("shoppingicon clicked should see action class ThaiRetail")
            val intent34 = Intent(this,ThaiRetail::class.java)
            startActivity(intent34)
        }
        thHoldToolBTN = findViewById(R.id.thHoldToolBTN)
        thHoldToolBTN.setOnClickListener {
            println("thHoldToolBTN clicked should see action class ThaiTool")
            val intent32 = Intent(this,ThaiTool::class.java)
            startActivity(intent32)
        }
        bathroomiconT = findViewById(R.id.bathroomiconT)
        bathroomiconT.setOnClickListener {
            println("bathroomiconT clicked should see action class ThaiBathroom")
            val intent33 = Intent(this,ThaiBathroom::class.java)
            startActivity(intent33)
        }
        kiticon = findViewById(R.id.kiticon)
        kiticon.setOnClickListener {
            println("kiticon clicked should see action class ThaiKitchen")
            val intent32 = Intent(this,ThaiKitchen::class.java)
            startActivity(intent32)
        }
        bedroomicon = findViewById(R.id.bedroomicon)
        bedroomicon.setOnClickListener {
            println("thHoldBedroomBtn clicked should see action class ThaiBedroom")
            val intent32 = Intent(this,ThaiBedroom::class.java)
            startActivity(intent32)
        }
        thHoldHouseBtn = findViewById(R.id.thHoldHouseBtn)
        thHoldHouseBtn.setOnClickListener {
            println("thHoldHouseBtn clicked should see action class ThaiHouse")
            val intent36 = Intent(this,ThaiHouse::class.java)
            startActivity(intent36)
        }
        thHoldVeggieBtn = findViewById(R.id.thHoldVeggieBtn)
        thHoldVeggieBtn.setOnClickListener {
            println("thHoldVeggieBtn clicked should see action class ThaiVeggie")
            val intent37 = Intent(this,ThaiVeggie::class.java)
            startActivity(intent37)
        }
        thHoldshoppingBtn = findViewById(R.id.thHoldshoppingBtn)
        thHoldshoppingBtn.setOnClickListener {
            println("thHoldshoppingBtn clicked should see action class ThaiShopping")
            val intent38 = Intent(this,ThaiShopping::class.java)
            startActivity(intent38)
        }
        thpluralicon = findViewById(R.id.thpluralicon)
        thpluralicon.setOnClickListener {
            println("thHoldPluralsBtn clicked should see action class ThaiPlurals")
            val intent36 = Intent(this,ThaiPlurals::class.java)
            startActivity(intent36)
        }
        thHoldWordBtn = findViewById(R.id.thHoldWordBtn)
        thHoldWordBtn.setOnClickListener {
            println("thHoldWordBtn clicked should see action class ThaiWordsOne")
            val intent40 = Intent(this,ThaiWordsOne::class.java)
            startActivity(intent40)
        }
        ThVocabNamesBTN = findViewById(R.id.ThVocabNamesBTN)
        ThVocabNamesBTN.setOnClickListener {
            println("ThVocabNamesBTN clicked should see action class ThaiWordW")
            val intent41 = Intent(this,ThaiWordW::class.java)
            startActivity(intent41)
        }
        ThONamesBTN = findViewById(R.id.ThONamesBTN)
        ThONamesBTN.setOnClickListener {
            println("ThONamesBTN clicked should see action class ThaiWordO")
            val intent42 = Intent(this,ThaiWordO::class.java)
            startActivity(intent42)
        }
        ThRNamesBTN = findViewById(R.id.ThRNamesBTN)
        ThRNamesBTN.setOnClickListener {
            println("ThONamesBTN clicked should see action class ThaiWordR")
            val intent43 = Intent(this,ThaiWordR::class.java)
            startActivity(intent43)
        }
        ThDNamesBTN = findViewById(R.id.ThDNamesBTN)
        ThDNamesBTN.setOnClickListener {
            println("ThDNamesBTN clicked should see action class ThaiWordD")
            val intent44 = Intent(this,ThaiWordD::class.java)
            startActivity(intent44)
        }

        ThSNamesBTN = findViewById(R.id.ThSNamesBTN)
        ThSNamesBTN.setOnClickListener {
            println("ThSNamesBTN clicked should see action class ThaiWordS")
            val intent45 = Intent(this,ThaiWordS::class.java)
            startActivity(intent45)
        }
        ThGNamesBTN = findViewById(R.id.ThGNamesBTN)
        ThGNamesBTN.setOnClickListener {
            println("ThGNamesBTN clicked should see action class ThaiRedT")
            val intent46 = Intent(this,ThaiRedT::class.java)
            startActivity(intent46)
        }
        ThAMNamesBTN = findViewById(R.id.ThAMNamesBTN)
        ThAMNamesBTN.setOnClickListener {
            println("ThAMNamesBTN clicked should see action class ThaiPurpleAM")
            val intent47 = Intent(this,ThaiPurpleAM::class.java)
            startActivity(intent47)
        }
        ThHNamesBTN = findViewById(R.id.ThHNamesBTN)
        ThHNamesBTN.setOnClickListener {
            println("ThHNamesBTN clicked should see action class ThaiBlueH")
            val intent48 = Intent(this,ThaiBlueH::class.java)
            startActivity(intent48)
        }
        ThNNNamesBTN = findViewById(R.id.ThNNNamesBTN)
        ThNNNamesBTN.setOnClickListener {
            println("ThHNamesBTN clicked should see action class ThaiBlueH")
            val intent49 = Intent(this,ThaiBrownN::class.java)
            startActivity(intent49)
        }
        ThYellowDNamesBTN = findViewById(R.id.ThYellowDNamesBTN)
        ThYellowDNamesBTN.setOnClickListener {
            println("ThYellowDNamesBTN clicked should see action class ThaiYellowD")
            val intent50 = Intent(this,ThaiYellowD::class.java)
            startActivity(intent50)
        }

*/
        /////////////////////////////////////////////////////////////////////////
    }//end of constructor  ThYellowDNamesBTN
    } //end of class