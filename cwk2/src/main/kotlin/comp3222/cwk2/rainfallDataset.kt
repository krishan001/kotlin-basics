package comp3222.cwk2
import java.io.File
import kotlin.math.roundToInt
class RainfallDataset(){
    // define global variables so that they can be accessed from main
    val fileData = mutableListOf<String>()
    val dataset = mutableListOf<Measurement>()
    var station: String = ""
    var record= listOf<String>()
    var lat: String = ""
    var long: String = ""
    var elevation: String = ""
    var firstYear = ""
    var lastYear = ""

    // function to read and process the file
    fun readFile(file : String){
        // add the data from the file to a mutable list
        File(file).forEachLine{
            fileData.add(it)
        }

        // get the details of the file
        if (fileData.size < 4){
            throw badFileException("File does not contain a valid header")
        }

        // get the header information
        station = fileData[0]
        record = fileData[1].split(",")
        lat = record[1].split(" ")[2]
        long = record[1].split(" ")[4]
        elevation = record[2].split(" ")[1]

        // if there is only the header then just print out the header information
        if (fileData.size == 4){
            printHeader(station, lat, long, elevation)
            throw badFileException("Number of records: 0")
        }
        // remove the first 4 lines
        for (i in 0..3){
            fileData.removeAt(0)
        }

        // create a measurement object for each element in the dataset and add it to a mutable list
        fileData.forEach{
            val m = Measurement(it)
            checkValidity(m)
            dataset.add(m)
        }

        // get the first and last years
        firstYear = dataset[0].year
        lastYear = dataset[dataset.size-1].year

    }

    // check the year and levels are in the required ranges
    fun checkValidity(m: Measurement){
         if(m.year.toInt() < 1930){
             throw badFileException("Year is less than 1930")
        }  else if(m.level < 0.0){
            throw badFileException("Rainfall value is invalid")
        }
    }

    // return the wettest year and the level of that year
    fun getWettestYear():String{
        var wettestYear: String = "0000"
        var highestRainfall: Double = 0.0
        var sum:Double=0.0
        var currentYear = dataset[0].year

        var i = 0
        while(i <= dataset.size -1){
            if (dataset[i].year == currentYear){
                sum += dataset[i].level
            }else{
                currentYear = dataset[i].year
                i--
                if(sum > highestRainfall){
                    wettestYear = dataset[i-1].year
                    highestRainfall = sum
                }
                sum = 0.0
            }

            i++
        }
        val yearAndLevel = wettestYear + " (" + highestRainfall + "mm)"
        return yearAndLevel
    }

    // return the driest year and the level of that year
    fun getDriestYear():String{
        var driestYear: String = "0000"
        var lowestRainfall: Double = Double.MAX_VALUE
        var sum:Double=0.0
        var currentYear = dataset[0].year

        var i = 0
        while(i <= dataset.size -1){
            if (dataset[i].year == currentYear){
                sum += dataset[i].level
            }else{
                currentYear = dataset[i].year
                i--
                if(sum < lowestRainfall){
                    driestYear = dataset[i-1].year
                    lowestRainfall = sum
                }
                sum = 0.0
            }

            i++
        }
        val yearAndLevel = driestYear + " (" + lowestRainfall + "mm)"

        return yearAndLevel
    }

    // get the index of the dataset with the highest rainfall
    fun getHighestRainfall():Int{
        var highest: Double = 0.0
        var highestIndex:Int = 0
        for (i in 0 until dataset.size){
            if (dataset[i].level > highest){
                highest = dataset[i].level
                highestIndex = i
            }
        }

        return highestIndex
    }

    // get the index of the dataset with the lowest rainfall
    fun getLowestRainfall():Int{
        var lowest: Double = Double.MAX_VALUE
        var lowestIndex :Int = 0
        for (i in 0 until dataset.size){
            if (dataset[i].level < lowest){
                lowest = dataset[i].level
                lowestIndex = i
            }
        }
        return lowestIndex
    }

    // get the month with the highest rainfall
    fun getHighestMonth():String{
        val index = getHighestRainfall()
        return dataset[index].month
    }
    
    // get the year with the highest rainfall
    fun getHighestYear():String{
        val index = getHighestRainfall()
        return dataset[index].year
    }

    // get the level of the highest rainfall
    fun getHighestLevel():String{
        val index = getHighestRainfall()
        return dataset[index].level.toString()
    }

    // get the month with the lowest rainfall
    fun getLowestMonth():String{
        val index = getLowestRainfall()
        return dataset[index].month
    }

    // get the year with the lowest rainfall
    fun getLowestYear():String{
        val index = getLowestRainfall()
        return dataset[index].year
    }
    
    // get the level of the highest rainfall
    fun getLowestLevel():String{
        val index = getLowestRainfall()
        return dataset[index].level.toString()
    }


    // print out the header information
    fun printHeader(station: String, lat: String, long: String, elevation: String){
        val output = """
        |Station: $station
        |Latitude: $lat
        |Longitude: $long
        |Elevation: $elevation m
        """
        println(output.trimMargin())
    }

    // print out the bar chart
    fun printBarChart(file:String, year: String){
        readFile(file)
        // get the values of rainfall for each month of the given year
        val monthlyValues = dataset.filter({it.year == year})
        // print out the bar chart
        monthlyValues.forEach{
            val hs = getHashes(it.level)
            println("${it.month} (${it.level}): $hs" )
        }
    }

    fun getHashes(level:Double):String{
        val highestLevel: Double = dataset[getHighestRainfall()].level
        // compute the number of hashes
        val numHashes:Int = ((level/highestLevel) * 50).roundToInt()
        var hashString = ""
        // add hashes to string
        repeat(numHashes){
            hashString+="#"
        }
        return hashString
    }
}
