package comp3222.cwk2

import java.io.File


fun main(args: Array<String>){
    val rd = RainfallDataset()
    rd.readFile(args[0])
}

class RainfallDataset(){
    val fileData = mutableListOf<String>()
    val dataset = mutableListOf<Measurement>()

    fun readFile(file : String){
        File(file).forEachLine{
            fileData.add(it)
        }
        val station = fileData[0]
        val record = fileData[1].split(",")
        val lat = record[1].split(" ")[2]
        val long = record[1].split(" ")[4]
        val elevation = record[2].split(" ")[1]
        for (i in 0..3){
            fileData.removeAt(0)
        }
        fileData.forEach{
            val m = Measurement(it)
            dataset.add(m)
        }


        
        printStats(station,lat,long,elevation, fileData.size)


    }

    fun getHighestRainfall(ds : MutableListOf<Measurement>){
        val highest = 0
        for (i in 0 until ds.size){
            println(i)
        }

    }

    fun printStats(station: String, lat: String, long: String, elevation: String, numRecords: Int){
        val output = """
        |Station: $station
        |Latitude: $lat
        |Longitude: $long
        |Elevation: $elevation m
        |Number of records: $numRecords"""
        println(output.trimMargin())
    }
}


class Measurement(record: String){
    val measurements = (record.split(" ")).toMutableList()
    val r = mutableListOf<String>()
    init{
        measurements.forEach{
            if (it != ""){
                r.add(it)
            }
        }
    }
    val year = r[0]
    
    val month = when(r[1]){
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
    val level = r[5]



    
}
