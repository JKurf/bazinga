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
Began the process of converting graphics from Java2D to JavaSDL
Created Interface GraphicsClass
Renamed (old) GraphicsClass to GraphicsClass_Java2D that implements GraphicsClass
Added GraphicsClass_SDL that implements GraphicsClass