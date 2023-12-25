# Clothes Donation System

## Overview
The Clothing Donation Management System is a Java-based console application designed to facilitate the donation and management of clothing items. The system serves as a simple yet effective tool for users to donate clothes, keep track of their contributions, and search for donated items based on various criteria.

## Key Features
**User Registration:**
Users can register and create an account by providing their username.

**Clothing Donation:**
Users can donate clothing items by specifying details such as category (upper or lower), size, quality, and gender. The system generates a random price for each donated item within predefined ranges.

**Data Persistence:**
All clothing donations are stored in a text file (clothesList.txt).

**Search Functionality:**
Users can search for donated items based on gender, size, and quality.The search results display relevant details about the items.

**Previous Donations Display:**
Users can view a list of their previous donations, including item details and the total estimated worth of their contributions.

**Random Price Generation:**
The system employs a random price generation mechanism for each donated item based on the mentioned quality.

**Error Handling:**
The system includes error handling at various stages to prevent unexpected issues.

## Installation Steps ##
**1. Clone the Repository:**

    git clone https://github.com/Septic-H/Clothes-Donation-System.git
    
**2. Navigate to Project Directory:**

    cd Clothes-Donation-System

**3. Compile the Java Files:**

    javac ClothesDonationSystem.java
    
**4. Run the application:**

    java ClothesDonationSystem

## User Guide ##
**Registration:**
- Users start by registering with a unique username.

**Donation:**
- Users can donate clothing items by providing necessary details.
- The system calculates and displays the estimated worth of the donated item.

**Search:**
- Users can search for donated items based on gender, size, and quality.

**Previous Donations:**
- Users can view a list of their previous donations, including item details and total estimated worth.

**Exit:**
- Users can exit the system when finished.

## Functionality ##
The program containts only one class and that class implements the Clothes Donation System.
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
