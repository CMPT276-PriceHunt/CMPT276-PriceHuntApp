<details>
  <summary>Feature Tracking</summary>

  
# Feature Tracking:
![Screenshot 2024-10-30 230816](https://github.com/user-attachments/assets/8adb0a2d-0ae8-45ca-b23c-6248f4318eff)
![Screenshot 2024-10-30 222723](https://github.com/user-attachments/assets/91f0fcf3-a0ce-48bf-b7d0-49fdffa7dfd2)
![Screenshot 2024-10-30 222822](https://github.com/user-attachments/assets/43e66cc8-f2f2-4463-8943-f45828cb319c)
![Screenshot 2024-10-30 222945](https://github.com/user-attachments/assets/8d98f3a4-6a50-410e-b0f4-72f831084be0)
</details>

</details>

<details>
  <summary>Retrospective</summary>
  
# Retrospective:

### Things that went well:
- One issue we faced was having our navigation bar sectioned off from the rest of the pages, in this iteration it is now properly linked and leads to the correct pages
- There is now a feature to have multiple wishlists
- Added a button to go back after viewing wishlists.
- Can now enter the description of the item, and its price to the wishlist.
- Cannot submit to wishlist unless all fields are filled.
- Can delete created wishlists.
- There is a way to edit your personal information and save it.
- Added page to view user's personal information.
- Added signup page.
- Users can not log in without first signing up.

### Things that did not go well:
- The navigation bar now uses activities instead of fragments but that took a while to solve
- Not enough time to work on this project during midterm season, so we were not able to implement as many features as we want
- Utilizing git better, as we have many branches that have different commit histories, making it messy and harder to work with.


### Things to improve on:
- Including a consistent UI language
- Having pre-existing clothing items that can be added to the wishlists
- Have the image buttons on the home page actually lead to a page of those items
- The page for viewing information is not fully finalized yet, as the layout still needs work.
- Need to add restrictions when it comes to signing up and editing personal information.
- Need to add button for clearing profile information.

</details>
<details>

<details>
  <Summary>Test Cases</Summary>
  
# Test Cases:
- Allows users to enter "" into username and password, which they can use to log in.
- Sometimes have to double-click on an icon in the navigation bar. Not too sure about the reason why yet.
- No restriction on editing personal information, so users can enter "" into all the fields, and it'll save. (This is the only current way to clear all saved information.)

</details>
  <summary>Running Instruction</summary>
  
# Running Instruction:
- You can try logging into the app. It should not let you because you have not signed up yet.
- Click on the purple text "Sign Up Now" to go to the signup page.
- Sign up by putting in a username and password. There are no current restrictions to username and password.
- After signing up, log into your account by inputting your username and password.
- You should now be at the Home Screen. Home Screen itself does not have any functionalities.
- At the bottom, you can click the icons on the navigation bar to go to wishlist or profile.

- ### App Installation with apk
- Start from a new android studio window
- On the three vertical dots near the top right, select "Profile or Debug APK"
- Navigate and select the apk-release.apk
- Click the run on the middle of the top of the screen to run the application :)

### Wishlist:
- Make sure that your phone emulator already has photos. Adding can be done by going to the camera in the emulator and taking a photo.
- Now back to the app, you can create multiple wishlists now.
- First, click on "view wishlists".
- Click on the "+" button in the bottom right, and name your wishlist and create.
- Click on "back" in the bottom left, and now you can switch between wishlists.
- Enter the item's name, it's price, add a photo, and click "submit".
- Click on "view wishlists" and click on the arrow to the right of your wishlist.
- You should see the wishlist's items and their price.

### Profile:
- Upon opening your profile, the content should be empty.
- Click on "Edit Profile"
- Enter all of the details. Currently, there are no restrictions at all except for phone number.
- After entering details click "save".
- All of your details should now show in the middle of the screen.

</details>

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

