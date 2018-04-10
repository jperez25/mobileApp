package com.press.jsnake.war

/**
 * Created by jsnak on 4/2/2018.
 */
class SetImageHelper {

    fun getCardName(x: Any): String {
        when(x){
            //2-A
            //52 rinse and repeat
            1->return "twoCloves"
            2->return ""
            3->return ""
            4->return ""
            5->return ""
            6->return ""
            7->return ""
            8->return ""
            9->return ""
            10->return ""
            11->return ""
            12->return ""
            13->return  "aceCloves"

            else -> {
                return "false"
            }

        }

        return "ic_launcher_background"
    }
}