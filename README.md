# Genshin Chronicles

**Version 3.1**

A command line game developed using Java.

Venture forth into the world of Genshin, command teammates into battle, defeat monsters, loot dungeons, and save the world. Utilizes object-oriented programming for characters.

Inspired by various rpg games such as Dicey Dungeons, Genshin Impact, and Xenoblade Chronicles.

## Overview

This is the starting area on a fresh save file. You cannot leave until you help the lost girl defeat an ogre where you learn about the combat system in the game.

<img src="/demo/beginning.gif" width="50%" height="50%"/>

---

Once you save the girl, you are free to explore around the world as you please. Note that there is a certain boundary you cannot cross to prevent overusage of memory.

If you ever get lost or want to know where you are, you can use a map which the girl gives you for saving her.

<img src="/demo/map.gif" width="50%" height="50%"/>

---

On your travels, you may come across dungeons located randomly across the world. If you are prepared, enter them and defeat three waves of enemies to earn a glorious
award at the end for your troubles.

<img src="/demo/dungeon.gif" width="50%" height="50%"/>

---

Whenever you are in need of a rest or happen to have loads of money, you can stop by villages on your journey where they provide an inn for restoring your party's health,
and a shop for purchasing loot boxes with a chance of earning rare equipment, as well as hiring mercenaraies who ask for quite a hefty price.

<img src="/demo/village.gif" width="50%" height="50%"/>

---

Each village also has a line of quests optional for you to complete. You may notice items lying around the overworld. Gather enough of a certain type of them for villagers
and they may reward you handsomely.

<img src="/demo/quests.gif" width="50%" height="50%"/>

---

Lastly, one thing to note is that you can save your progress in this game! Simply input the specific key anywhere in the overworld and you can return to playing as long as
you keep the saveData.txt file that comes with when you install the game.

<img src="/demo/save.gif" width="50%" height="50%"/>

---

I won't share any specific story details in here, but if you are curious then be sure to check out the game!

Godspeed traveller. May I wish you luck on your journey.

## Installation

1. [Download (requires Java version 8 or higher)](https://github.com/BitPingu/genshin-chronicles/releases/download/v3.0/GenshinChronicles-3.1.zip)
2. Extract the files
3. In a terminal, cd to the project directory.
- For example, if it is in your Downloads folder:

  ```sh 
  cd .\Downloads\GenshinChronicles-3.1\ 
  ```
4. Run the jar file
  ```sh 
  java "-Dfile.encoding=UTF8" -jar .\GenshinChronicles.jar
  ```

## Development

For my Grade 12 Computer Science final assignment, I've had an idea of making a game that was even more ambitious than my last project, [Littlemon Rouge](https://github.com/BitPingu/littlemon-rouge). 
I've always wanted to recreate those stylish open world games like Minecraft or Breath of the Wild, with added rpg elements from games like Dicey Dungeons (recommended by my friend) and Xenoblade 
Chronicles, personally my favourite game of all time. ( definitely check it out if you have a Wii or Switch ;) )

I knew this was a task that I couldn't do alone, so I've had the privilege of working with my friend Joey where we learned how to use Git for the first time, and had to finish making
the game within a week which was a daunting challenge for us especially considering our skill level at the time. We put as much effort as we could into making the game, and although we 
had to give up some ideas we've planned on implementing such as a controller, non-text based graphics using JavaFX, and more party members, it's honestly a miracle that the game was in
a working state or nonetheless completed since it at least had every core feature it needed to be qualified as an open world rpg. There was some minor leftover bugs we didn't notice
during the time of submission aside from formatting issues like purchasing a party member without deducting your money, which I've fixed in this specific release.

Me and Joey were in charge of specific roles throughout the game while occasionally helping each other out and implementing certain features together. I worked on the open world design
which utilizes ArrayLists to handle random generation in chunks by getting the player's position and adding specific rows or colums to the grid based world in a 2D matrix. When the player 
walks around, it triggers chances for spawning dungeons, villages, items, and enemies. Since I've made the villages, I also worked on the shops, inventory, and quest system which saves to
each village so that the quests do not reset everytime you enter one. I also was in charge of making a storyline suitable to our game given the limited time we've had. Joey worked on the 
battle system which utilizes random number generation for attacking and specific algorithms for each unique special attack. He also worked on the levelling system, distribution of stats, 
money and exp, enemy AI, and general game balancing. We both heavily worked on the save system which was quite challenging given the amount of data that is needed to be saved per playthrough.

This specific project holds a special place in my heart since I had developed it with a close friend of mine, and it marked my sendoff from high school which helped me to reaffirm my interest 
in pursuing Computer Science. I hope you enjoy our game!

## Acknowledgements

- My friend [Joey](https://github.com/JoeyA03) who co-created the game with me
- My high school Computer Science teacher Mr. Janicas
