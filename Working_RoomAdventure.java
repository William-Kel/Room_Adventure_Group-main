import java.util.Scanner; // Import Scanner for reading user input

public class Working_RoomAdventure { // Main class containing game logic

    // class variables
    private static int dead = 0;
    private static Room currentRoom; // The room the player is currently in
    private static String[] inventory = {null, null, null, null, null}; // Player inventory slots
    private static String status; // Message to display after each action
    public static int lives = 3;

    // constants
    final private static String DEFAULT_STATUS =
        "Sorry, I do not understand. Try [verb] [noun]. Valid verbs include 'go', 'look', and 'take'."; // Default error message



    private static void handleGo(String noun) { // Handles moving between rooms
        String[] exitDirections = currentRoom.getExitDirections(); // Get available directions
        Room[] exitDestinations = currentRoom.getExitDestinations(); // Get rooms in those directions
        String current = currentRoom.getName();
        status = "I don't see that room."; // Default if direction not found
        if (current == "Room 4"){
            if (noun.equals("east")){
                currentRoom = exitDestinations[2];
                status = "Changed Room";
            }
        }
        for (int i = 0; i < exitDirections.length; i++) { // Loop through directions
            if (noun.equals(exitDirections[i])) { // If user direction matches
                currentRoom = exitDestinations[i]; // Change current room
                status = "Changed Room"; // Update status
            }
        }
    }

    private static void handleLook(String noun) { // Handles inspecting items
        String[] items = currentRoom.getItems(); // Visible items in current room
        String[] itemDescriptions = currentRoom.getItemDescriptions(); // Descriptions for each item
        status = "I don't see that item."; // Default if item not found
        for (int i = 0; i < items.length; i++) { // Loop through items
            if (noun.equals(items[i])) { // If user-noun matches an item
                status = itemDescriptions[i]; // Set status to item description
            }
        }
    }

    private static void handleTake(String noun) { // Handles picking up items
        String[] grabbables = currentRoom.getGrabbables(); // Items that can be taken
        status = "I can't grab that."; // Default if not grabbable
        for (String item : grabbables) { // Loop through grabbable items
            if (noun.equals(item)) { // If user-noun matches grabbable
                for (int j = 0; j < inventory.length; j++) { // Find empty inventory slot
                    if (inventory[j] == null) { // If slot is empty
                        inventory[j] = noun; // Add item to inventory
                        status = "Added it to the inventory"; // Update status
                        break; // Exit inventory loop
                    }
                }
            }
        }
    }

    private static void handleThrow (String noun){ //Handels throwing items
        String [] throwables = currentRoom.getThrowables(); // Items that can be thrown
        status = "I shouldn't throw that."; //Default if not throwable
        for (String projectile : throwables){ //loop through throwable items
            if (noun.equals(projectile)){ //If the user's noun matches a throwable
                for (int t = 0; t < inventory.length; t++){//Sort through the inventory
                    if (inventory[t].equals(projectile)){ // If slot has a throwable
                        inventory[t] = null; //subtract item from inventory
                        if (projectile.equals("dart")){
                            status = "You thow the dart and hit the dart board" + "\nA door opens on the [east] side of the room";
                        }
                        break; // Exit inventory loop
                    }

                }
            }

        }
    }
    public static void death() {
        Room[] exitDestinations = currentRoom.getExitDestinations(); // Get rooms in those directions
        String current = currentRoom.getName();
        if (lives == 0){
            setupGame();
        }
        else{
          lives -=1;
            for (int i = 0; i < exitDestinations.length; i++) { // Loop through directions
                if (current.equals("Room 1")) { // If user direction matches
                    currentRoom = exitDestinations[2]; // Change current room
                    status = "You have died. You have: " + lives+" Lives."; // Update status
                }
                if (current.equals("Room 2")) { // If user direction matches
                    currentRoom = exitDestinations[2]; // Change current room
                    status = "You have died. You have: " + lives+" Lives."; // Update status
                }
                if (current.equals("Room 3")) { // If user direction matches
                    currentRoom = exitDestinations[3]; // Change current room
                    status = "You have died. You have: " + lives+" Lives."; // Update status
                }
                if (current.equals("Room 4")) { // If user direction matches
                    currentRoom = exitDestinations[3]; // Change current room
                    status = "You have died. You have: " + lives+" Lives."; // Update status
                }
                if (current.equals("Kitchen")) { // If user direction matches
                    currentRoom = exitDestinations[1]; // Change current room
                    status = "You have died. You have: " + lives+" Lives."; // Update status
                }
                if (current.equals("Hidden Room")) { // If user direction matches
                    currentRoom = exitDestinations[1]; // Change current room
                    status = "You have died. You have: " + lives+" Lives."; // Update status
                }
            }  
        }
        
    }

    private static void handleEat(String noun){ // handles eat
        if ("food?".equals(noun)) {
            for(int f = 0; f < inventory.length; f++) {
                if ("food?".equals(inventory[f])) {
                    inventory[f] = null;
                    status = "";
                    death();
                    break;
                }
            }
        }
        else {
            System.out.println("You can't eat that!");
        }
    }

    private static void setupGame() { // Initializes game world
        Room room1 = new Room("Room 1"); // Create Room 1
        Room room2 = new Room("Room 2"); // Create Room 2
        Room room3 = new Room("Room 3"); // Create Room 3
        Room room4 = new Room("Room 4"); // Create Room 4
        Room kitchen = new Room("Kitchen"); //Create Kitchen
        Room HiddenRoom = new Room("Hidden Room");// Create the hidden room
        Room Graveyard = new Room("Graveyard"); // Creates the grave yard
////////////////////room 1
        String[] room1ExitDirections = {"east", "south"}; // Room 1 exits
        String room1name = "Room 1";// room 4 name
        Room[] room1ExitDestinations = {room2, room3, Graveyard}; // Destination rooms for Room 1
        String[] room1Items = {"chair", "desk"}; // Items in Room 1
        String[] room1ItemDescriptions = { // Descriptions for Room 1 items
            "It is a chair",
            "It's a desk, there is a [key] on it."
        };
        String[] room1Grabbables = {"key"}; // Items you can take in Room 1
        String[] room1Throwables = {}; // Items that can be thrown in Room 1
        room1.setExitDirections(room1ExitDirections); // Set exits
        room1.setExitDestinations(room1ExitDestinations); // Set exit destinations
        room1.setItems(room1Items); // Set visible items
        room1.setItemDescriptions(room1ItemDescriptions); // Set item descriptions
        room1.setGrabbables(room1Grabbables); // Set grabbable items
        room1.setThrowables(room1Throwables); // Set throwable items
        room1.setName(room1name);
////////////////////room 2
        String[] room2ExitDirections = {"west", "south"}; // Room 2 exits
        String room2name = "Room 2";// room 4 name
        Room[] room2ExitDestinations = {room1, room4, Graveyard}; // Destination rooms for Room 2
        String[] room2Items = {"fireplace", "rug"}; // Items in Room 2
        String[] room2ItemDescriptions = { // Descriptions for Room 2 items
            "It's on fire",
            "There is a lump of [coal] on the rug."
        };
        String[] room2Grabbables = {"coal"}; // Items you can take in Room 2
        String[] room2Throwables = {}; // Items that can be thrown in Room 2
        room2.setExitDirections(room2ExitDirections); // Set exits
        room2.setExitDestinations(room2ExitDestinations); // Set exit destinations
        room2.setItems(room2Items); // Set visible items
        room2.setItemDescriptions(room2ItemDescriptions); // Set item descriptions
        room2.setGrabbables(room2Grabbables); // Set grabbable items
        room2.setThrowables(room2Throwables); // Set throwable items
        room2.setName(room2name);
////////////////////room 3
        String room3name = "Room 3";// room 4 name
        String[] room3ExitDirections = {"north", "east", "west"}; // Room 3 exits
        Room[] room3ExitDestinations = {room1, room4, kitchen, Graveyard}; // Destination rooms for Room 3
        String[] room3Items = {"window", "box"}; // Items in Room 3
        String[] room3ItemDescriptions = { // Descriptions for Room 3 items
            "It's noon outside",
            "There is a sharp [dart] in the box."
        };
        String[] room3Grabbables = {"dart"}; // Items you can take in Room 3
        String[] room3Throwables = {}; // Items that can be thrown in Room 3
        room3.setExitDirections(room3ExitDirections); // Set exits
        room3.setExitDestinations(room3ExitDestinations); // Set exit destinations
        room3.setItems(room3Items); // Set visible items
        room3.setItemDescriptions(room3ItemDescriptions); // Set item descriptions
        room3.setGrabbables(room3Grabbables); // Set grabbable items
        room3.setThrowables(room3Throwables); // Set throwable items
        room3.setName(room3name);
////////////////////room 4
        String room4name = "Room 4";// room 4 name
        String[] room4ExitDirections = {"north", "west"}; // Room 3 exits
        Room[] room4ExitDestinations = {room2, room3, HiddenRoom, Graveyard}; // Destination rooms for Room 4
        String[] room4Items = {"chair"}; // Items in Room 4
        String[] room4ItemDescriptions = { // Descriptions for Room 4 items
            "It seems quite comfortable."
        };
        String[] room4Grabbables = {}; // Items you can take in Room 4
        String[] room4Throwables = {"dart"}; // Items that can be thrown in Room 4
        room4.setExitDirections(room4ExitDirections); // Set exits
        room4.setExitDestinations(room4ExitDestinations); // Set exit destinations
        room4.setItems(room4Items); // Set visible items
        room4.setItemDescriptions(room4ItemDescriptions); // Set item descriptions
        room4.setGrabbables(room4Grabbables); // Set grabbable items
        room4.setThrowables(room4Throwables); // Set throwable items
        room4.setName(room4name);
////////////////////Kitchen
        String[] kitchenExitDirections = {"east"};
        String kitchenname = "Kitchen";// room 4 name
        Room[] kitchenExitDestinations = {room3, Graveyard};
        String[] kitchenItems = {"stove"};
        String[] kitchenItemDescriptions = {
            "It seems very dusty. Looks like there is some [food?] on the stove."
        };
        String[] kitchenGrabbables = {"food?"};
        String[] kitchenThrowables = {}; // Items that can be thrown in Room 4
        kitchen.setExitDirections(kitchenExitDirections);
        kitchen.setExitDestinations(kitchenExitDestinations);
        kitchen.setItems(kitchenItems);
        kitchen.setItemDescriptions(kitchenItemDescriptions);
        kitchen.setGrabbables(kitchenGrabbables);
        kitchen.setThrowables(kitchenThrowables); // Set throwable items
        kitchen.setName(kitchenname);
////////////////////Hidden Room
        String[] HiddenRoomExitDirections = {"west"}; // Hidden Room exits
        String HiddenRoomname = "Hidden Room";// room 4 name
        Room[] HiddenRoomExitDestinations = {room4, Graveyard}; // Destination rooms for Hidden Room
        String[] HiddenRoomItems = {"table", "bread"}; // Items in Hidden Room
        String[] HiddenRoomItemDescriptions = { // Descriptions for Hidden Room items
            "It is a very nice table, with a loaf of bread on it.",
            "This loaf looks suspicous. Its a loaf of suspicious bread."
        };
        String[] HiddenRoomGrabbables = {}; // Items you can take in Hidden Room
        String[] HiddenRoomThrowables = {}; // Items that can be thrown in Hidden Room
        HiddenRoom.setExitDirections(HiddenRoomExitDirections); // Set exits
        HiddenRoom.setExitDestinations(HiddenRoomExitDestinations); // Set exit destinations
        HiddenRoom.setItems(HiddenRoomItems); // Set visible items
        HiddenRoom.setItemDescriptions(HiddenRoomItemDescriptions); // Set item descriptions
        HiddenRoom.setGrabbables(HiddenRoomGrabbables); // Set grabbable items
        HiddenRoom.setThrowables(HiddenRoomThrowables); // Set throwable items
        HiddenRoom.setName(HiddenRoomname);
////////////////////Graveyard
        String[] GraveyardExitDirections = {"Hell"}; // Room 3 exits
        String Graveyardname = "Graveyard";// room 4 name
        Room[] GraveyardExitDestinations = {room1, Graveyard}; // Destination rooms for Room 3
        String[] GraveyardItems = {"Puppy"}; // Items in Room 3
        String[] GraveyardItemDescriptions = { // Descriptions for Room 3 items
            "A 3 headed black puppy.",
        };
        String[] GraveyardGrabbables = {"rock"}; // Items you can take in Room 3
        String[] GraveyardThrowables = {}; // Items that can be thrown in Room 3
        Graveyard.setExitDirections(GraveyardExitDirections); // Set exits
        Graveyard.setExitDestinations(GraveyardExitDestinations); // Set exit destinations
        Graveyard.setItems(GraveyardItems); // Set visible items
        Graveyard.setItemDescriptions(GraveyardItemDescriptions); // Set item descriptions
        Graveyard.setGrabbables(GraveyardGrabbables); // Set grabbable items
        Graveyard.setThrowables(GraveyardThrowables); // Set throwable items        
        Graveyard.setName(Graveyardname);
//////////////////////////////////////////////////////////////////
        currentRoom = room1; // Start game in Room 1
        
    }
    
    @SuppressWarnings("java:S2189")
    public static void main(String[] args) { // Entry point of the program
        setupGame(); // Initialize rooms, items, and starting room

        while (true) { // Game loop, runs until program is terminated
            System.out.print(currentRoom.toString()); // Display current room description
            System.out.print("Inventory: "); // Prompt for inventory display

            for (int i = 0; i < inventory.length; i++) { // Loop through inventory slots
                System.out.print(inventory[i] + ", "); // Print each inventory item
            }

            System.out.println("\nWhat would you like to do? "); // Prompt user for next action
            System.out.println("");//formated space

            Scanner s = new Scanner(System.in); // Create Scanner to read input
            String input = s.nextLine(); // Read entire line of input
            String[] words = input.split(" "); // Split input into words

            if (words.length != 2) { // Check for proper two-word command
                status = DEFAULT_STATUS; // Set status to error message
                continue; // Skip to next loop iteration
            }

            String verb = words[0]; // First word is the action verb
            String noun = words[1]; // Second word is the target noun

            switch (verb) { // Decide which action to take
                case "go": // If verb is 'go'
                    handleGo(noun); // Move to another room
                    break;
                case "look": // If verb is 'look'
                    handleLook(noun); // Describe an item
                    break;
                case "take": // If verb is 'take'
                    handleTake(noun); // Pick up an item
                    break;
                case "throw": // If verb is 'throw'
                    handleThrow(noun);
                    break;
                case "eat":
                    handleEat(noun);
                    break;
                default: // If verb is unrecognized
                    status = DEFAULT_STATUS; // Set status to error message
            }

            System.out.println(status); // Print the status message
        }
    }
}

class Room { // Represents a game room
    private String name; // Room name
    private String[] exitDirections; // Directions you can go
    private Room[] exitDestinations; // Rooms reached by each direction
    private String[] items; // Items visible in the room
    private String[] itemDescriptions; // Descriptions for those items
    private String[] grabbables; // Items you can take
    private String[] throwables; // Items you can throw

    public Room(String name) { // Constructor
        this.name = name; // Set the room's name
    }

    public void setName(String name) { // Setter for name
        this.name = name;
    }

    public String getName() { // Getter for name
        return name;
    }

    public void setExitDirections(String[] exitDirections) { // Setter for exits
        this.exitDirections = exitDirections;
    }

    public String[] getExitDirections() { // Getter for exits
        return exitDirections;
    }

    public void setExitDestinations(Room[] exitDestinations) { // Setter for exit destinations
        this.exitDestinations = exitDestinations;
    }

    public Room[] getExitDestinations() { // Getter for exit destinations
        return exitDestinations;
    }

    public void setItems(String[] items) { // Setter for items
        this.items = items;
    }

    public String[] getItems() { // Getter for items
        return items;
    }

    public void setItemDescriptions(String[] itemDescriptions) { // Setter for descriptions
        this.itemDescriptions = itemDescriptions;
    }

    public String[] getItemDescriptions() { // Getter for descriptions
        return itemDescriptions;
    }

    public void setGrabbables(String[] grabbables) { // Setter for grabbable items
        this.grabbables = grabbables;
    }

    public String[] getGrabbables() { // Getter for grabbable items
        return grabbables;
    }

    public void setThrowables(String[] throwables){// Setter for throwable items
        this.throwables = throwables;
    }

    public String[] getThrowables(){ // Getter for throwable items
        return throwables;
    }

    @Override
    public String toString() { // Custom print for the room
        String result = "\nLocation: " + name; // Show room name
        result += "\nYou See: "; // List items
        for (String item : items) { // Loop items
            result += item + ", "; // Append each item
        }
        result += "\nExits: "; // List exits
        for (String direction : exitDirections) { // Loop exits
            result += direction + ", "; // Append each direction
        }
        return result + "\n"; // Return full description
    }
}
