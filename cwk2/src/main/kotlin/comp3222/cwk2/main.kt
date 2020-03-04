package comp3222.cwk2



fun main(args: Array<String>){
    val rd = RainfallDataset()
    try{
        if (args.size == 1){
            // read the file in
            rd.readFile(args[0])

            // format the output 
            val output = """
            |Number of records: ${rd.fileData.size}
            |Years Spanned: ${rd.firstYear} to ${rd.lastYear}
            |Wettest Year: ${rd.getWettestYear()}
            |Driest Year: ${rd.getDriestYear()}
            |Wettest Month: ${rd.getHighestMonth()} ${rd.getHighestYear()} (${rd.getHighestLevel()} mm)
            |Driest Month: ${rd.getLowestMonth()} ${rd.getLowestYear()} (${rd.getLowestLevel()} mm)
            """
            // print the output
            println(output.trimMargin())
        }else if(args.size == 2){
            // if the year is given then print the barchart
            rd.printBarChart(args[0],args[1])
        }else{
            println("You have entered an invalid number of arguments")
        }
    } catch(e:badFileException){
        println(e.message)
    }


}




