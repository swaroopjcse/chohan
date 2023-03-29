# ChoHan

Follow the steps to create the ChoHan game app.

## Basic code

There are a lot of files in an Android project. A lot of them are system generated. 
For our purposes, these three files are important:
- MainActivity.java: this is where you will write the Java code for the app
- Die.java: represents the six-faced dice; already implemented for you
- activity_main.xml: user interface of the app; already implemented for you.

### onCreate method

Screens in an Android app are internally called *Activities*. 
Here we have only one activity: `MainActivity`.

When the activity starts, its `onCreate` method is invoked.
Read through the `onCreate` method. Don't dig deep - but make sure the lines in there make some sense.

### getAllViewElements

This method grabs all the relevant *View elements* from the user interface (UI). Hm, what does that mean?
Open the activity_main.xml file (In the Android view, res > layout).

Click on the individual elements like the box representing a die. Check out the *attributes* in the right column.
Note the `id` attribute. We use this in the Java code to get hold of a UI element.

### setNewDice

This method creates new instances of the dice to use. It means you have fresh dice to play the game!

### updateUI

This method displays the given number of coins on the screen.

### setPlayButtonClickListener

Implements the behaviour of clicking the PLAY button: 
- roll the dice, 
- calculate the new coins based on the wager, 
- update the UI to reflect the changes.

### Let's give it a spin!

Let's launch our app by clicking on 'Run' menu at the top and then the 'Run' option. 
You can also do this by clicking the green 'Play' button at the top of this window.
(You should have an emulator installed already and it launch the app on the emulator. 
Call someone for help if that doesn't happen.)

Try playing the game. What happens?

## Let's put in some magic

The app shows the UI but the *logic* has not been implemented - yet!

Let's check the flow 
- you launch the app (that's `onCreate`)
- inside that method, you get the view elements: looks good
- next you set the dice: looks good
- next you update the UI: looks good
- finally, you set the button click listener: looks good!

Then, what is happening? Investigate for a few moments - seek help if needed.

### calculateCoins needs fixing

You will find that `calculateCoins` method is empty! Let's implement it now.

What do you need to do when you come here, that is when the button is clicked?

- get the current number of coins
- get the wager amount
- get whether the player bet for the sum to be odd or even
- calculate the resulting coins based on this information.

Let's do this step by step

### Getting the number of coins

Where do you have this information? In the `txtCoins` TextView.

So we grab the method `getIntFromTextView` to get it out of it.

Add the following line inside the `calculateCoins` method:

        int coins = getIntFromTextView(txtCoins);

### Get the wager amount

Similarly, get the wager amount by adding this line next

        int wager = getIntFromTextView(editWager);

### Get the bet type

Now, get whether the player bet for the sum to be even by adding this line next:

        boolean betForEven = switchOddEven.isChecked();

### Get the sum of the dice faces

Now add this line

        int diceSum = d1.value() + d2.value();

### Calculate the resulting coins

First, let's check whether the player has won.

If he bet for the sum to be even and the sum was indeed even, he wins.
Also, if he bet for the sum to be odd and the sum was indeed odd, he wins.
Otherwise, he loses.

Add this line to implement this in code:

        boolean win = (betForEven && isEven(diceSum)) || (!betForEven && !isEven(diceSum));

Now compute the coins based on whether the player won or lost. Add the following code:

        if (win) {
            coins += wager;
        } else {
            coins -= wager;
        }

Return the resulting coins:

        return coins;

### Try it now

Try running your app now. It should work as expected.

## Additional activities

1. Let's raise the stakes: if the player wins, she gets twice the amount she bet; if she loses, she loses twice the amount she bet. How will you implement this change?
2. It will be nice to get a message on whether the player won or lost. Look up Toasts in Android and add the functionality.
3. Can you identify some bugs in the game? Bug is an unexpected behavior. Note them down and discuss with your partner how you can fix those.
4. What happens if you rotate the phone in the middle of the game play? Is this an expected behaviour? To fix this, we will have to create multiple files and write a lot of additional code. Creating a working app is easy - creating a 'good'
 app is very difficult.