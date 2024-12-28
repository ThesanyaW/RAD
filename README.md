💼 Financial Management System

Overview
The Financial Management System is a Java-based application designed to help users manage their investments, currency exchanges, and client data securely. The system consists of three primary modules: Investment Calculator, Currency Exchange, and Client Data Manager. The application provides projections for potential returns, currency conversion rates, and secure storage of user profiles and transaction records.

Features
📈 Investment Calculator Module
- User Input: Allows users to input initial investment amounts, monthly contributions, and select from three investment types.
- Projections: Provides projections for potential returns over 1 year, 3 years, and 10 years.
- Details Display: Displays total profit, taxes, and fees in a graphical format.
- Currency Formatting: Outputs are formatted in GBP to two decimal places.
- Error Handling: Gracefully handles user input errors.

💱 Currency Exchange Module
- Currency Conversion: Calculates currency conversions between popular currencies, including GBP, USD, EUR, JPY, CAD, and AUD.
- Transaction Limits: Adheres to transaction limits:
  - Minimum: 250 of the base currency.
  - Maximum: 10,000 of the base currency.
- Fees: Applies fees based on transaction value:
  - Up to 500: 4%.
  - Over 500: 3%.
  - Over 2000: 2%.

🔒 Client Data Manager Module
- Secure Storage: Enables secure storage of user profiles, including contact details, transaction records, and saved calculations.
- Encryption: Implements encryption to ensure data privacy and compliance with financial regulations.

Getting Started
Prerequisites
- Java Development Kit (JDK) 8 or higher
- A Java IDE (e.g., IntelliJ IDEA, Eclipse)
- MySQL or another relational database for secure storage

Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/ThesanyaW/RAD.git
