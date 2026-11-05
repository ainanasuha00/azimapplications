package com.example.data

data class TownPlace(
    val id: String,
    val name: String,
    val emoji: String,
    val description: String,
    val sentence: String
)

data class DirectionWord(
    val id: String,
    val phrase: String,
    val symbol: String,
    val meaning: String,
    val example: String
)

data class ListeningQuestion(
    val question: String,
    val options: List<String>,
    val correctIndex: Int
)

data class WhereAmIClue(
    val id: Int,
    val clue: String,
    val options: List<String>,
    val answer: String,
    val explanation: String
)

data class RescueMission(
    val id: Int,
    val touristSaying: String,
    val startLocation: String,
    val destination: String,
    val prompt: String,
    val routeOptions: List<String>,
    val correctRouteIndex: Int,
    val teacherTip: String
)

object ExplorerRepository {

    val townPlaces = listOf(
        TownPlace(
            id = "school",
            name = "School",
            emoji = "🏫",
            description = "A school is a place where children learn.",
            sentence = "We study English and meet our teachers at school."
        ),
        TownPlace(
            id = "hospital",
            name = "Hospital",
            emoji = "🏥",
            description = "A hospital is a place where doctors and nurses help sick people.",
            sentence = "Doctors care for patients at the hospital."
        ),
        TownPlace(
            id = "library",
            name = "Library",
            emoji = "📚",
            description = "A library is a place where people read and borrow books.",
            sentence = "The library is quiet so you can read good stories."
        ),
        TownPlace(
            id = "park",
            name = "Park",
            emoji = "🌳",
            description = "A park is a place where people play and enjoy nature.",
            sentence = "Children ride their bicycles and play games in the park."
        ),
        TownPlace(
            id = "police",
            name = "Police Station",
            emoji = "🚓",
            description = "A police station is where police officers work and help people.",
            sentence = "Police officers keep our town safe and protected."
        ),
        TownPlace(
            id = "supermarket",
            name = "Supermarket",
            emoji = "🛒",
            description = "A supermarket is a place where people buy food and other things.",
            sentence = "You can buy fresh fruits and snacks at the supermarket."
        ),
        TownPlace(
            id = "post_office",
            name = "Post Office",
            emoji = "✉️",
            description = "A post office is a place where people send letters and parcels.",
            sentence = "I send letters to my pen pal at the post office."
        ),
        TownPlace(
            id = "restaurant",
            name = "Restaurant",
            emoji = "🍽️",
            description = "A restaurant is a place where people buy and eat meals.",
            sentence = "Families enjoy delicious lunch and dinner at the restaurant."
        )
    )

    val directionWords = listOf(
        DirectionWord(
            id = "straight",
            phrase = "Go straight",
            symbol = "↑",
            meaning = "Keep moving forward in the same direction.",
            example = "Go straight along Main Street."
        ),
        DirectionWord(
            id = "turn_left",
            phrase = "Turn left",
            symbol = "←",
            meaning = "Change your direction towards your left side.",
            example = "Turn left at the traffic light."
        ),
        DirectionWord(
            id = "turn_right",
            phrase = "Turn right",
            symbol = "→",
            meaning = "Change your direction towards your right side.",
            example = "Turn right into Oak Avenue."
        ),
        DirectionWord(
            id = "cross_road",
            phrase = "Cross the road",
            symbol = "↔",
            meaning = "Move safely across from one side to the other.",
            example = "Use the pedestrian zebra crossing to cross the road."
        ),
        DirectionWord(
            id = "next_to",
            phrase = "Next to",
            symbol = "▣ ▣",
            meaning = "Directly beside another building or place.",
            example = "The bakery is next to the post office."
        ),
        DirectionWord(
            id = "opposite",
            phrase = "Opposite",
            symbol = "▣ ⇆ ▣",
            meaning = "Facing across the street from another place.",
            example = "The playground is opposite the school."
        )
    )

    const val listeningScript = "Start at the school. Go straight. Turn left at the park. The hospital is on your right."

    val listeningQuestions = listOf(
        ListeningQuestion(
            question = "1. Where does the journey start?",
            options = listOf("School", "Library", "Post Office"),
            correctIndex = 0
        ),
        ListeningQuestion(
            question = "2. Which direction should you turn?",
            options = listOf("Turn right", "Turn left", "Go backwards"),
            correctIndex = 1
        ),
        ListeningQuestion(
            question = "3. What is the final destination?",
            options = listOf("Park", "Hospital", "Restaurant"),
            correctIndex = 1
        )
    )

    val whereAmIClues = listOf(
        WhereAmIClue(
            id = 1,
            clue = "I am a quiet building full of storybooks, encyclopedias, and reading desks. People come here to borrow books.",
            options = listOf("Library", "Restaurant", "Supermarket"),
            answer = "Library",
            explanation = "A library is where books are kept for reading and borrowing."
        ),
        WhereAmIClue(
            id = 2,
            clue = "Nurses and doctors work here with ambulances parked outside. People come here when they feel unwell.",
            options = listOf("Park", "Hospital", "School"),
            answer = "Hospital",
            explanation = "A hospital provides medical treatment for sick people."
        ),
        WhereAmIClue(
            id = 3,
            clue = "You can see green grass, tall trees, and swings. Children run around and families have picnics here.",
            options = listOf("Police Station", "Park", "Post Office"),
            answer = "Park",
            explanation = "A park is an open outdoor public green space."
        ),
        WhereAmIClue(
            id = 4,
            clue = "There are shopping carts, aisles of fresh apples, milk, and cereal boxes with cashiers at the counter.",
            options = listOf("Supermarket", "Library", "School"),
            answer = "Supermarket",
            explanation = "A supermarket sells groceries and household items."
        ),
        WhereAmIClue(
            id = 5,
            clue = "You come here to mail birthday postcards, buy stamps, or collect delivery parcels.",
            options = listOf("Post Office", "Restaurant", "Hospital"),
            answer = "Post Office",
            explanation = "A post office handles postage stamps and mail delivery."
        )
    )

    val rescueMissions = listOf(
        RescueMission(
            id = 1,
            touristSaying = "\"Excuse me! I just arrived at the School and I urgently need to visit the Hospital. How do I get there?\"",
            startLocation = "School",
            destination = "Hospital",
            prompt = "Choose the clearest directions for the tourist:",
            routeOptions = listOf(
                "Go straight, turn left at the park, and the hospital is on your right.",
                "Turn right immediately and walk into the river.",
                "Go backwards to your house and sleep."
            ),
            correctRouteIndex = 0,
            teacherTip = "Clear directions state the landmark (park) and the turn side (left)!"
        ),
        RescueMission(
            id = 2,
            touristSaying = "\"Hello young guide! I am at the Library and want to send a postcard home from the Post Office. Can you guide me?\"",
            startLocation = "Library",
            destination = "Post Office",
            prompt = "What directions should you give?",
            routeOptions = listOf(
                "Turn around and do 10 jumping jacks.",
                "Cross the road, go straight past the supermarket, and the post office is next to the bank.",
                "Walk inside the bookshelf and wait."
            ),
            correctRouteIndex = 1,
            teacherTip = "\"Cross the road\" and \"next to\" are key spatial prepositions!"
        ),
        RescueMission(
            id = 3,
            touristSaying = "\"Pardon me! I am hungry after visiting the Park. Where can I find the Restaurant?\"",
            startLocation = "Park",
            destination = "Restaurant",
            prompt = "Guide the hungry traveler:",
            routeOptions = listOf(
                "Walk straight across the pedestrian crossing; the restaurant is opposite the park gate.",
                "Climb up the tallest tree and look around.",
                "Sit down on the grass forever."
            ),
            correctRouteIndex = 0,
            teacherTip = "\"Opposite\" describes something facing directly across an open space or street."
        )
    )
}
