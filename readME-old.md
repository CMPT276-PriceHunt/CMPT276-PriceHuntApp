Feature Tracking:
![Screenshot 2024-10-09 220827](https://github.com/user-attachments/assets/394d2819-a52d-4440-b09c-8e32a82c60d8)
![Screenshot 2024-10-09 221237](https://github.com/user-attachments/assets/1421941d-c7ee-4964-b64a-2d9a1a461910)
![Screenshot 2024-10-09 221335](https://github.com/user-attachments/assets/62e8c895-2a4f-4a59-9f3f-2f75cde9b083)
![Screenshot 2024-10-09 221055](https://github.com/user-attachments/assets/52ee1ea5-e2d5-4c18-8a23-c4dbd037046b)

Retrospective:
Things that went well:<br/> - We were able to create the very outer shell of the app,<br/>
                       - Login Page is working well with users able to input username and password (although not useful yet)<br/>
                       - Login page is very minimalistic right now, but can easily be changed for iteration 2<br/>
                       - Wishlist, able to add items with description and picture<br/>
                       - I'd say our team has been following the AGILE development method quite well so far, we're not doing our project per iteration,<br/> 
                       - rather were working on every single part of the app slowly, and building our way up.<br/>
<br/>
<br/>
<br/>
Things that did not go well:<br/> - The wishlist isn't working to how we want it right now,<br/>
                             - Since we don't have any items yet to add to a wishlist, we have to add pictures ourselves by<br/> 
                             - Taking a picture in the emulator, then we can add a photo<br/>
                             - The wishlist is still very bare bones, and needs a lot of development<br/>
                             - Also we forgot to add a button for going back to the previous page after viewing wishlist<br/>
                             - Found out that Figma UI could not be imported to Android Studio 1-to-1. This caused the UI to <br/>
                             - be remade from scratch with barely enough time. Then once it was complete, it was not able <br/>
                             - to navigate to the already created pages. Causing another remake. <br/>
                             - There were some issues in git where it would not merge cleanly, <br/>
                             - it required the branches to be merged locally on one, then pushed to main.<br/>
<br/>
<br/>
<br/>
Things to improve on:<br/> - Utilizing git properly, I don't think we used it properly as we created separate branches,<br/>
                      - without first pulling from the main branch, this is because when we first started, we didn't have anything in the main branch<br/>
                      - this made merging branches a lot more tedious.<br/>
                      - Improvement of the UI in general. Some examples include a consistent design throughout, <br/>
                      - proper linking between pages, and more interactables on the home page. <br/>
                      - Having proper dummy data to test the wishlist implementation. <br/>
                      - Having clothing data already on app instead of having to add it on our own. <br/>
                      - Login page is not fully working yet either, need to make it so that users can sign up first and add their users and password to be actual data.<br/>
<br/>
<br/>
<br/>
Test Cases:<br/>
- Profile and Login work as they should because there is no actual functionality yet.
- Only able to enter numbers into phone number for profile, working as intended
- Able to enter both alphabets and numerics into everything else
- However, when you do not add any items into wishlist, profile button goes to the very top.
- When adding more than one item, users have to scroll to see each item
- Homepage is very barebones, does not have any functionalities.
<br/>
<br/>
<br/>
Running Instructions:
- Ensure the latest version of Android Studio is installed
- Download the zip files 
- Extract the files
- ( or clone using the command prompt: git clone --single-branch --branch main https://github.com/CMPT276-PriceHunt/CMPT276-PriceHuntApp.git , only clones the main branch which contains our final iteration 1 )
- Open Android Studio
- Select Open a Project, then find the extracted folder
- On the top left, go to Build, then Make Project
- On the top middle, select Add Configuration
- Select Edit Configurations
- Add new configuration
- Select Android App
- Name config if needed
- Set the module to LoginApp.app.main
- Click OK
- Run the app by clicking the Run icon beside the config name on the top bar
- Close the app by swiping up the bar on the bottom
- Open the camera app (swipe up on the home screen, and click the camera app || if camera app not there, you can search it up as well in the search bar)
- Take a photo so that it is possible to add an item to the wishlist
- **Currently the app follows a linear structure of login -> profile -> wishlist -> home page**
- Might need to delete line 50 and 51 then add this within dependencies of /app/build.gradle.kts{
        val nav_version = "2.8.2"

        // Jetpack Compose integration
        implementation("androidx.navigation:navigation-compose:$nav_version")

        // Views/Fragments integration
        implementation("androidx.navigation:navigation-fragment:$nav_version")
        implementation("androidx.navigation:navigation-ui:$nav_version")

        // Feature module support for Fragments
        implementation("androidx.navigation:navigation-dynamic-features-fragment:$nav_version")

        // Testing Navigation
        androidTestImplementation("androidx.navigation:navigation-testing:$nav_version")
}



<details>
  <summary>Directory Layout</summary>
  
# Directory Layout:
    .
    ├── idea                                         # Tools for IDE
    ├── app                                          # Compiled files (alternatively `dist`)
    │   ├── src                                      # Documentation files (alternatively `doc`)
    │   │   ├── main
    │   │   │   ├── java
    │   │   │   │   ├── com
    │   │   │   │   │   ├── example
    │   │   │   │   │   │   │   ├── loginapp        # Java and Kotlin source code
    │   │   │   │   ├── res                         # All non-code resources (layouts, drawables)
    ├── gradle                                      # Build automation tool
    ├── gitignore                                    
    ├── README.md                                   
    ├── build.gradle.kits
    ├── gradle.properties
    ├── gradlew
    ├── gradlew.bat
    ├── readME-old.md                                # Old README
    └── settings.gradle.kts
</details>

### App Installation with apk
- Start from a new android studio window
- On the three vertical dots near the top right, select "Profile or Debug APK"
- Navigate and select the apk-release.apk
- Click the run on the middle of the top of the screen to run the application :)
