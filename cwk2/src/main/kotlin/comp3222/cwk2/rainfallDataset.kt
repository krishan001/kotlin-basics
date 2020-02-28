package comp3222.cwk2
import java.io.File

class RainfallDataset(){
    val fileData = mutableListOf<String>()
    val dataset = mutableListOf<Measurement>()

    fun readFile(file : String){
        // add the data from the file to a mutable list
        File(file).forEachLine{
            fileData.add(it)
        }

        // get the details of the file
        val station = fileData[0]
        val record = fileData[1].split(",")
        val lat = record[1].split(" ")[2]
        val long = record[1].split(" ")[4]
        val elevation = record[2].split(" ")[1]

        // remove the first 4 lines
        for (i in 0..3){
            fileData.removeAt(0)
        }

        // create a measurement object for each element in the dataset and add it to a mutable list
        fileData.forEach{
            val m = Measurement(it)
            dataset.add(m)
        }

        // get the first and last years
        val firstYear = dataset[0].year
        val lastYear = dataset[dataset.size-1].year

        // Get highest Rainfall
        val highestIndex:Int = getHighestRainfall()
        // Get lowest Rainfall
        val lowestIndex:Int = getLowestRainfall()
        // Wettest year
        val wettestYear: String = getWettestYear()
        // Driest Year
        val driestYear: String = getDriestYear()

        printStats(station,lat,long,elevation, fileData.size, dataset[highestIndex], dataset[lowestIndex], firstYear, lastYear, wettestYear, driestYear)

    }

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

    fun printStats(station: String, lat: String, long: String, elevation: String, numRecords: Int, highest: Measurement, lowest:Measurement,
    firstYear: String, lastYear: String, wettestYear: String, driestYear: String){
        val output = """
        |Station: $station
        |Latitude: $lat
        |Longitude: $long
        |Elevation: $elevation m
        |Number of records: $numRecords
        |Years Spanned: $firstYear to $lastYear
        |Wettest Year: $wettestYear
        |Driest Year: $driestYear
        |Wettest Month: ${highest.month} ${highest.year} (${highest.level} mm)
        |Driest Month: ${lowest.month} ${lowest.year} (${lowest.level} mm)


        """
        println(output.trimMargin())
    }
}
