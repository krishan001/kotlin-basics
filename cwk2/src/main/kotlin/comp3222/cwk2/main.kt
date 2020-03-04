package comp3222.cwk2



fun main(args: Array<String>){
    val rd = RainfallDataset()
    try{
        if (args.size == 1){
            rd.readFile(args[0])
            val output = """
            |Station: ${rd.station}
            |Latitude: ${rd.lat}
            |Longitude: ${rd.long}
            |Elevation: ${rd.elevation} m
            |Number of records: ${rd.fileData.size}
            |Years Spanned: ${rd.firstYear} to ${rd.lastYear}
            |Wettest Year: ${rd.getWettestYear()}
            |Driest Year: ${rd.getDriestYear()}
            |Wettest Month: ${rd.getHighestMonth()} ${rd.getHighestYear()} (${rd.getHighestLevel()} mm)
            |Driest Month: ${rd.getLowestMonth()} ${rd.getLowestYear()} (${rd.getLowestLevel()} mm)
            """
            println(output.trimMargin())
        }else if(args.size == 2){
            rd.printBarChart(args[0],args[1])
        }else{
            println("You have entered an invalid number of arguments")
        }
    } catch(e:badFileException){
        println(e.message)
    }


}




