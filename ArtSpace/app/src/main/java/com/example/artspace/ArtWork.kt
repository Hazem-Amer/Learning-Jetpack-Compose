package com.example.artspace

data class ArtWork(
    var id: Int = 0,
    val artWorkImage: Int = R.drawable.ic_launcher_background,
    val artWorkTitle: String = "Here should be the title of the art work",
    val artWorkArtist: String = "Name",
    val artWorkYear: String = "Year"
){
    init {
        id++
    }
    companion object{
        fun getArtWorkList(): List<ArtWork>{
            return listOf(
                ArtWork(
                    artWorkImage = R.drawable.lemon_tree, // Replace with your actual image resource
                    artWorkTitle = "Starry Night",
                    artWorkArtist = "Vincent van Gogh",
                    artWorkYear = "1889"
                ),
                ArtWork(
                    artWorkImage = R.drawable.lemon_squeeze, // Replace with your actual image resource
                    artWorkTitle = "The Scream",
                    artWorkArtist = "Edvard Munch",
                    artWorkYear = "1893"
                ),
                ArtWork(
                    artWorkImage = R.drawable.lemon_drink, // Replace with your actual image resource
                    artWorkTitle = "Guernica",
                    artWorkArtist = "Pablo Picasso",
                    artWorkYear = "1937"
                ),
                ArtWork(
                    artWorkImage = R.drawable.lemon_restart, // Replace with your actual image resource
                    artWorkTitle = "Water Lilies",
                    artWorkArtist = "Claude Monet",
                    artWorkYear = "1914"
                )
            )
        }
    }
}