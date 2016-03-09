# bazinga
OldMeme.mp4

***27 FEB 2016***
Spent way too many hours figuring out how to work IntelliJ in correspondence with GitHub  
Once that was finished work began on figuring out graphics  
World Class was created that allows for the storage of map data in the WorldFiles directory  
    -Each Map's data file begins with the number of rows and then the number of columns  
    -Those two integers are followed by a 2D array of integers that represent TileID values  
A GraphicsClass was created in order to display the Map BMP to the screen  
A TileMap was created, with a null tile and some wooden tiles  
Entity Class was created to keep track of entity positions, name, and ID  

***28 FEB 2016***
Figured out the weird rectangle issue  
Printed TestMap to the screen via the GraphicsClass  
Found a way to run image with unlocked frame-rate without flickering  
Error popping up where the bufferGraphics will close but the actual graphics will not  

***29 FEB 2016***
Player Class Added  
Began the process of converting graphics from Java2D to ~~JavaSDL~~ lwjgl  
~~Created Interface GraphicsClass  
Renamed (old) GraphicsClass to GraphicsClass_Java2D that implements GraphicsClass  
Added GraphicsClass_SDL that implements GraphicsClass~~  
Rewrote GraphicsClass to support lwjgl, made changes to game/gameclient to reflect this  
~~Fixed line endings on this readme~~  
Fixed line endings on this readme  

***1 March 2016***
Further fleshed out the GraphicsClass  
Implemented bitmap font rendering  
Unified rendering practices by distinguishing world coordinates from screen coordinates  

***2 March 2016***
Created custom font(still in progress)  
Implemented key cooldowns to get usable input  
Began work on collision detection by adding a clip array to the world class and a method to render the clip as an overlay  

***3 March 2016***
Converted world files to JSON files  
Wrote tool to generate JSON world files from old format  

***4 March 2016***
Added MenuItem class  
-MenuItem has string contents to display  
-MenuItem has an array of MenuItem children to also dipslay  
-Rendered recursively, highlighting the current one  
Implemented system to pass messages up to the game class from deeper classes  

***8 March 2016***
Added Animations  
Created function to load .json file holding all animation data for a single entity  
Added BattleState  
Implemented simple BattleStack that allows both the player and enemy to attack  
TODO: Decide how stats affect damage  
