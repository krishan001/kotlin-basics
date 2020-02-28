
package comp3222.cwk2

class Measurement(record: String){
    val measurements = (record.split(" ")).toMutableList().apply{removeAll(listOf(""))}

    val year = measurements[0]
    
    val month = when(measurements[1]){
        "1"-> "January"
        "2"-> "Febuary"
        "3"-> "March"
        "4"-> "April"
        "5"-> "May"
        "6"-> "June"
        "7"-> "July"
        "8"-> "August"
        "9"-> "September"
        "10" -> "October"
        "11" -> "November"
        "12" -> "December"
        else -> throw Exception("Bad Month")

    }
    val level = measurements[5].toDouble()

}