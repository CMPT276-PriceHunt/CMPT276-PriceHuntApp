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
- There is a way to edit your personal information
- Signing up and using the username and password is now required to enter the app

### Things that did not go well:
- The navigation bar now uses activities instead of fragments but that took a while to solve
- Not enough time to work on this project during midterm season, so we were not able to implement as many features as we want


### Things to improve on:
- Including a consistent UI language
- Having pre-existing clothing items that can be added to the wishlists
- Have the image buttons on the home page actually lead to a page of those items
- 

</details>
<details>
  <summary>Test Cases</summary>
  
# Running Instruction:
- You can try logging into the app. It should not let you because you have not signed up yet.
- Click on the purple text "Sign Up Now" to go to the signup page.
- Sign up by putting in a username and password. There are no current restrictions to username and password.
- After signing up, log into your account by inputting your username and password.
- You should now be at the Home Screen. Home Screen itself does not have any functionalities.
- At the bottom, you can click the icons on the navigation bar to go to wishlist or profile.

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
    ├── idea                     # Compiled files (alternatively `dist`)
    ├── app                      # Compiled files (alternatively `dist`)
    │   ├── benchmarks  # Documentation files (alternatively `doc`)
    ├── gradle                   # Source files (alternatively `lib` or `app`)
    ├── gitignore                # Automated tests (alternatively `spec` or `tests`)
    ├── README.md                # Tools and utilities
    ├── build.gradle.kits
    ├── gradle.properties
    ├── gradlew
    ├── gradlew.bat
    ├── readME-old.md            # Old README
    └── settings.gradle.kts
</details>
