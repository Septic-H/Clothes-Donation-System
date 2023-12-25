# Clothes Donation System #

## Overview ##
The Clothing Donation Management System is a Java-based console application designed to facilitate the donation and management of clothing items. The system serves as a simple yet effective tool for users to donate clothes, keep track of their contributions, and search for donated items based on various criteria.

## User Guide ##
    Registration:
        Users start by registering with a unique username.

    Donation:
        Users can donate clothing items by providing necessary details.
        The system calculates and displays the estimated worth of the donated item.

    Search:
        Users can search for donated items based on gender, size, and quality.

    Previous Donations:
        Users can view a list of their previous donations, including item details and total estimated worth.

    Exit:
        Users can exit the system when finished.

## Functionality ##
The program containts only one class and that class implements the Clothes Donation System.It allows users to donate clothes and view donated clothes and donation organizations.
- The system stores the data in text files.
- The usersList.txt file stores the list of users.
- The clothesList.txt file stores the list of clothes donated by the users.
- The donationCompaniesList.txt file stores the list of donation organizations.
- The data is loaded from the files to ArrayLists when the system is initialized.
- The data is saved to the files when the system is closed.
- The system also checks if a user already exists in the system.
- If the user exists, it displays the previous clothes donated by the user.
- If the user does not exist, it creates a new user ID for the user.
- The system also allows users to donate clothes.
- The system also allows users to view donated clothes and donation organizations.
