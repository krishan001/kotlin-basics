package comp3222.cwk2



fun main(args: Array<String>){
    val rd = RainfallDataset()
    if (args.size == 1){
        rd.readFile(args[0])
    }else{
        rd.printBarChart(args[0],args[1])
    }


}




