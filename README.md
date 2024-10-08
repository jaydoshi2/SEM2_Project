# 🛒 Online Shopping System 🛍️

🎥 **Video Demo**:  
<p align="center">
  <a href="https://www.loom.com/share/f34123e124074ee8bff94da3d808a9b7?sid=3fde3e74-4baa-437a-b2b9-def1fcd772a0" target="_blank">
    <img src="https://github.com/user-attachments/assets/1501e5be-f974-464b-bc4f-b359ca5d1c50" alt="Watch Video Demo" width="400"/>
  </a>
</p>

*Note: For a better viewing experience, you can watch the video at **1.5x speed**.*
---

## 📚 Project Overview

The **Online Shopping System** is a desktop-based application developed using **Java Swing**, designed to provide an interactive shopping experience. This project replicates the functionality of a modern e-commerce platform by implementing core features such as user authentication, product browsing, shopping cart management, billing, and even a **lucky spin wheel** for discounts! 🌟

The main objective of this project is to simulate an online shopping experience via a user-friendly graphical interface, offering users the ability to perform essential shopping tasks. Additionally, this project reinforced core **Java** concepts such as GUI development, file handling, event-driven programming, and conditional discount logic.

## 🌟 Features

### 1. **User Authentication (Login/Signup)**
   - Users can create an account via **Signup** or log in through **Login**. Secure authentication prevents unauthorized access.
   - User data is stored and verified for future sessions.

### 2. **Product Browsing & Shopping Center**
   - After logging in, users can browse various products, similar to an online store.
   - Product details like price, description, and options to add items to the cart are available.

### 3. **Shopping Cart Management**
   - Users can add or remove items from the shopping cart. The cart reflects real-time updates, displaying total items and prices.
   - Users can then proceed to the checkout for payment.

### 4. **Payment Options**
   - Users can choose between **UPI** and **Cash on Delivery (COD)** as payment methods.
   - For UPI payments, a UPI QR code image is generated for easy scanning and payment.

### 5. **Spin the Lucky Wheel! 🎰**
   - Users can spin a lucky wheel after adding items to the cart to try their luck and win discount coupons.
   - Coupons, like "60% Off" or "Flat ₹250 Off", are dynamically applied to the total bill based on the spin's outcome. 🤑

### 6. **Billing System**
   - After payment is confirmed and any discounts applied, users are shown a detailed billing summary.
   - The final bill includes product details, applied discounts, and the final payable amount.

### 7. **Interactive UI Experience**
   - The entire application is built using **Java Swing**, offering a responsive and modern UI. Users can interact with buttons, dialogs, and input fields smoothly, providing a polished shopping experience.

## 📂 Project Structure

The application is organized into various modules for better maintainability:

```bash
OnlineShoppingSystem/
│
├── _1Users_UI.java            # Console-based user login and signup interface
├── _2Shopping_Centre_UI.java  # Console-based product browsing and shopping cart
├── _3Cart_UI.java             # Console-based cart UI for managing products
├── _4Bill_UI.java             # Console-based billing UI displaying the final bill
├── COUPONS.txt                # List of discount coupons for the lucky spin feature
├── Loading.java               # Loading screen and progress bar animations
├── Login_template.java        # Main GUI-based login/signup implementation
├── Shopping_template.java     # GUI for shopping center, cart, payment, and spin logic
├── Spin_the_wheel.java        # Logic for the lucky spin feature
├── progressbar.java           # Progress bar UI element implementation
└── README.md                  # Project documentation
```

## 🛠️ Technologies Used
- **Java Swing**: For creating a responsive desktop user interface.
- **Java I/O**: To handle reading and writing user data, coupons, and product information.
- **Event Listeners & Handlers**: Used for managing user interactions and GUI events.
- **File Handling**: For reading coupon data from text files.
- **Random Number Generation**: For the lucky spin wheel's discount logic.
- **JOptionPane**: For displaying pop-up messages, alerts, and dialogs.

## 💡 What I Learned

Working on this project helped me develop a deeper understanding of:

- **GUI Programming in Java**: I learned how to build intuitive and interactive UIs using Java Swing components like buttons, panels, dialogs, and more.
- **Event-Driven Programming**: Handling user actions such as button clicks, form submissions, and item selection using `ActionListeners` and `ItemListeners`.
- **File Handling**: I implemented file reading and writing operations for storing user data and coupons.
- **Logic Implementation**: Creating features like the lucky spin wheel required implementing random number generation and conditional logic to apply discounts dynamically.
- **OOP Concepts**: This project reinforced key object-oriented programming principles, such as inheritance, polymorphism, and encapsulation.

## ⚙️ Setup Instructions

1. Clone the repository to your local machine.
2. Ensure you have **Java JDK 8** or later installed.
3. Open the project in your favorite Java IDE (e.g., **Eclipse**, **IntelliJ IDEA**).
4. Run the `Login_template.java` to start the application.
5. Follow the flow of the app:
   - **Login/Signup** -> **Browse Products** -> **Add to Cart** -> **Choose Payment** -> **Spin the Wheel (optional)** -> **View Final Bill**.

## 🎉 Conclusion

The **Online Shopping System** was a great learning experience in building desktop applications using Java Swing. It simulates a real-world e-commerce platform with a variety of features, and working on this project helped me hone my skills in Java GUI programming, file handling, and logical problem-solving.

Feel free to try it out and let me know if you have any suggestions for improvement! 💬

